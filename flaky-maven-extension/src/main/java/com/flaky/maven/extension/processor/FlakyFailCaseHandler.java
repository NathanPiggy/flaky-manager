package com.flaky.maven.extension.processor;

import com.alibaba.fastjson.JSON;
import com.flaky.maven.extension.mojo.FlakyBean;
import com.flaky.maven.extension.util.DataBaseUtil;
import com.flaky.maven.extension.util.JgitUtil;
import net.sf.cglib.core.Local;
import org.apache.maven.cli.event.ExecutionEventLogger;
import org.apache.maven.execution.AbstractExecutionListener;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.ExecutionListener;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

public class FlakyFailCaseHandler extends AbstractExecutionListener implements Initializable {
    private ExecutionListener delegate;

    @org.apache.maven.plugins.annotations.Component
    private MavenProject project;

    @Parameter(readonly = true, required= true)
    private String reportedTestsFile;

    public  String tips = "|----------------------------------------------------------------------------|\n" +
            "|  You Can Manage This Project's Flaky Test Here:                            |\n" +
            "|  http://134.175.194.57:10080/flaky/index?projectId=flaky-lifecycle-manager |\n" +
            "|----------------------------------------------------------------------------|";

    public static String surefirePath = System.getProperty("user.dir") + "/target/surefire-reports/";

    public static HashMap<String,Boolean> preLoadMap = new HashMap<String,Boolean>();

    public FlakyFailCaseHandler() {
        super();
    }

    @Override
    public void projectDiscoveryStarted(ExecutionEvent event) {
        //System.out.println("###################### projectDiscoveryStarted");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.projectDiscoveryStarted(event);
    }

    @Override
    public void sessionStarted(ExecutionEvent event) {
        //System.out.println("###################### sessionStarted");
        // Check if local has surefile record
        File file = new File(surefirePath);//File类型可以是文件也可以是文件夹
        File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
        //System.out.println("###################### preLoadMap="+preLoadMap + ";fileList="+fileList);

        if (preLoadMap != null && preLoadMap.size()== 0 && fileList != null && fileList.length > 0 ){
            loadLocalSurefireResultToCache(fileList);
        }
        super.sessionStarted(event);
    }

    public static void loadLocalSurefireResultToCache(File[] fileList){
        SAXReader reader = new SAXReader();
        try {
            for(File fileOrgin : fileList){
                //System.out.println("###################### file1" + fileOrgin.getName());
                if(fileOrgin.getName().indexOf("xml") > 0){
                    Document sureFireXml = null;
                        sureFireXml = reader.read(fileOrgin);
                    List<Element> testcaseTagList = sureFireXml.selectNodes("//testcase");
                    for (Element testcaseTag : testcaseTagList) {
                        String unitTestName = testcaseTag.attributeValue("name");
                        String className = testcaseTag.attributeValue("classname");
                        boolean success = true;
                        if(testcaseTag.getDocument().selectNodes("//failure").size() > 0) {
                            success = false;
                        }
                        preLoadMap.put(className+unitTestName,success);
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sessionEnded(ExecutionEvent event) {
        super.sessionEnded(event);
    }

    @Override
    public void projectSkipped(ExecutionEvent event) {
        super.projectSkipped(event);
    }

    @Override
    public void projectStarted(ExecutionEvent event) {
        //System.out.println("###################### sessionStarted");
        super.projectStarted(event);
    }

    @Override
    public void projectSucceeded(ExecutionEvent event) {
        System.out.println(tips);

        detectFlakyTests(true);
        preLoadMap.clear();
        super.projectSucceeded(event);
    }


    @Override
    public void projectFailed(ExecutionEvent event) {
        System.out.println(tips);

        detectFlakyTests(false);
        preLoadMap.clear();
        super.projectFailed(event);

    }

    public static void detectFlakyTests(boolean testResult){
        //System.out.println("System.getProperty(\"user.dir\")="+System.getProperty("user.dir"));//user.dir指定了当前的路径

        SAXReader reader = new SAXReader();
        Connection connection = null;
        Statement statement = null;
        int flakyStatus = 0;

        String projectId = "";

        JgitUtil.init();
        HashMap<String,String> diffFileMap = JgitUtil.getDiffFileList();
        String lastSha1 = diffFileMap.get("currentSha1");

        Integer detectCount = 1;

        try {
            File f = new File(System.getProperty("user.dir")+"/pom.xml");
            Document pomXml = reader.read(f);
            Element root = pomXml.getRootElement();
            Element artifcactIdTag;
            for (Iterator i = root.elementIterator("artifactId"); i.hasNext(); ) {
                artifcactIdTag = (Element) i.next();
                projectId =  artifcactIdTag.getStringValue();
                System.out.print("artifactId:" + projectId);
            }

            connection = DataBaseUtil.getDbConnection();

            statement = DataBaseUtil.getDbStatement(connection);
            File file = new File(surefirePath);//File类型可以是文件也可以是文件夹
            File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
            List fileWithXmlSubfix = new ArrayList<File>();
           // //System.out.println("###################### fileList.length" + fileList.length);
            if (fileList == null ) return;

            for(File fileOrgin : fileList){
                //System.out.println("###################### file1" + fileOrgin.getName());
                if(fileOrgin.getName().indexOf("xml") > 0){
                    Document sureFireXml = reader.read(fileOrgin);
                    List<Element> testcaseTagList = sureFireXml.selectNodes("//testcase");
                    for (Element testcaseTag : testcaseTagList) {
                        String className = testcaseTag.attributeValue("classname");
                        String unitTestName = testcaseTag.attributeValue("name");
                        //Has code change?
                        boolean noCodeChange = true;
                        if(diffFileMap.get(className.substring(className.lastIndexOf("."))) != null){
                            noCodeChange=false;
                        };

                        boolean currentTestResult = !(testcaseTag.getDocument().selectNodes("//failure").size() > 0);
                        boolean lastTestResult = preLoadMap.get(className+unitTestName) == null ? currentTestResult:preLoadMap.get(className+unitTestName);
                        boolean hasHistoryResult = preLoadMap.get(className+unitTestName)!=null;
                        //noCodeChange + hasHistoryResult + different test result then mark as flaky test
                        if(noCodeChange && hasHistoryResult && currentTestResult != lastTestResult) {

                                //Save to flaky table
                                FlakyBean flakyBean = DataBaseUtil.getFlakyRecord(className,unitTestName,statement);
                            //System.out.println("flakyBean="+flakyBean + ";lastSha1="+lastSha1+";flakyStatus="+flakyStatus+";unitTestName+"+unitTestName+";className+"+className);
                            Integer addId = null;
                            if(flakyBean == null){
                                addId = DataBaseUtil.addFlakyRecord(flakyStatus,new Date(),lastSha1,className,unitTestName,detectCount,projectId,statement);
                                }else{
                                    DataBaseUtil.updateFlakyRecord(flakyBean.getFlakyId(),flakyStatus,new Date(),lastSha1,detectCount,statement);
                                }
                                //Save to flaky_history table
                                FlakyBean flakyBean2 = DataBaseUtil.getFlakyRecord(className,unitTestName,statement);
                                if(addId != null || flakyBean2 != null){
                                    List<Element> propertyTagList = sureFireXml.selectNodes("//property");
                                    DataBaseUtil.addFlakyHistoryRecord(addId != null ? addId : flakyBean2.getFlakyId(),flakyStatus,new Date(),lastSha1,className,unitTestName,detectCount,projectId,JSON.toJSONString(propertyTagList),statement);
                                }
                        }
                    }
                    fileWithXmlSubfix.add(fileOrgin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(statement   !=   null){
                try{
                    statement.close();
                    statement = null;
                }catch(Exception   e){}
            }
            if(connection   !=   null){
                try{
                    connection.close();
                    connection = null;
                }catch(Exception   e){}
            }
        }
    }

    public static void main(String[] args) {
        detectFlakyTests(false);
    }

    @Override
    public void forkStarted(ExecutionEvent event) {
        super.forkStarted(event);
    }

    @Override
    public void forkSucceeded(ExecutionEvent event) {
        super.forkSucceeded(event);
    }

    @Override
    public void forkFailed(ExecutionEvent event) {
        //System.out.println("######################came forkFailed");
        super.forkFailed(event);
    }

    @Override
    public void mojoSkipped(ExecutionEvent event) {
        super.mojoSkipped(event);
    }

    @Override
    public void mojoStarted(ExecutionEvent event) {
        ////System.out.println("######################came mojoStarted");
        super.mojoStarted(event);
    }

    @Override
    public void mojoSucceeded(ExecutionEvent event) {
        //System.out.println("######################came mojoSucceeded");

        super.mojoSucceeded(event);
    }

    @Override
    public void mojoFailed(ExecutionEvent event) {
       // //System.out.println("######################came mojoFailed");

        super.mojoFailed(event);
    }

    @Override
    public void forkedProjectStarted(ExecutionEvent event) {
        super.forkedProjectStarted(event);
    }

    @Override
    public void forkedProjectSucceeded(ExecutionEvent event) {
        super.forkedProjectSucceeded(event);
    }

    @Override
    public void forkedProjectFailed(ExecutionEvent event) {
        //System.out.println("######################came forkedProjectFailed");
        super.forkedProjectFailed(event);
    }

    @Override
    public void initialize() throws InitializationException {
        //System.out.println("######################came FlakyFailCaseHandler.initialize");
        delegate = new ExecutionEventLogger();
    }

    public void configure(ExecutionListener executionListener) {
        this.delegate = executionListener;
    }

}
