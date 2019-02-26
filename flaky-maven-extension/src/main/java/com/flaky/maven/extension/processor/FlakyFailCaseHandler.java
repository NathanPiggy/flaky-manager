package com.flaky.maven.extension.processor;

import com.alibaba.fastjson.JSON;
import com.flaky.maven.extension.mojo.FlakyBean;
import com.flaky.maven.extension.util.DataBaseUtil;
import com.flaky.maven.extension.util.JgitUtil;
import net.minidev.json.JSONObject;
import org.apache.maven.cli.event.ExecutionEventLogger;
import org.apache.maven.execution.AbstractExecutionListener;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.ExecutionListener;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.dom4j.Document;
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

    public FlakyFailCaseHandler() {
        super();
    }

    @Override
    public void projectDiscoveryStarted(ExecutionEvent event) {
        super.projectDiscoveryStarted(event);
    }

    @Override
    public void sessionStarted(ExecutionEvent event) {
        super.sessionStarted(event);
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
        super.projectStarted(event);
    }

    @Override
    public void projectSucceeded(ExecutionEvent event) {
        super.projectSucceeded(event);
    }

    @Override
    public void projectFailed(ExecutionEvent event) {
        System.out.println("You Can Manage This Project's Flaky Test Here: http://134.175.194.57:10080/flaky/index?projectId=flaky-lifecycle-manager");
        //System.out.println("System.getProperty(\"user.dir\")="+System.getProperty("user.dir"));//user.dir指定了当前的路径

        SAXReader reader = new SAXReader();
        Connection connection = null;
        Statement statement = null;
        int flakyStatus = 0;
        String projectId = "";
        HashMap<String,String> diffFileMap = JgitUtil.getDiffFileList();
        String lastSha1 = diffFileMap.get("currentSha1");
        Integer detectCount = 1;

        try {
            connection = DataBaseUtil.getDbConnection();
            statement = DataBaseUtil.getDbStatement(connection);

            File file = new File(System.getProperty("user.dir") + "/target/surefire-reports/");//File类型可以是文件也可以是文件夹
            File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
            List fileWithXmlSubfix = new ArrayList<File>();
            for(File fileOrgin : fileList){
               // System.out.println("###################### file1" + fileOrgin.getName());
                if(fileOrgin.getName().indexOf("xml") > 0){
                    Document sureFireXml = reader.read(fileOrgin);
                    List<Element> testcaseTagList = sureFireXml.selectNodes("testcase");
                    for (Element testcaseTag : testcaseTagList) {
                        //If the test case failed, then check if the test case failed in last test
                        if(testcaseTag.getDocument().selectNodes("failure").size() > 0) {
                            String unitTestName = testcaseTag.attributeValue("name");
                            String className = testcaseTag.attributeValue("classname");
                            boolean ifLastTestPassed = DataBaseUtil.ifLastTestPassed(unitTestName,className,statement);
                            //Last test passed,failed this time, mark as flaky test
                            if(ifLastTestPassed){
                                //Save to flaky table
                                FlakyBean flakyBean1 = DataBaseUtil.getFlakyRecord(unitTestName,className,statement);
                                if(flakyBean1 != null){
                                    DataBaseUtil.addFlakyRecord(flakyStatus,new Date(),lastSha1,className,unitTestName,detectCount,projectId,statement);
                                }else{
                                    DataBaseUtil.updateFlakyRecord(flakyBean1.getFlakyId(),flakyStatus,new Date(),lastSha1,detectCount,statement);
                                }
                                //Save to flaky_history table
                                FlakyBean flakyBean2 = DataBaseUtil.getFlakyRecord(unitTestName,className,statement);
                                if(flakyBean2 != null){
                                    List<Element> propertyTagList = sureFireXml.selectNodes("property");
                                    DataBaseUtil.addFlakyHistoryRecord(flakyBean2.getFlakyId(),flakyStatus,new Date(),lastSha1,className,unitTestName,detectCount,projectId,JSON.toJSONString(propertyTagList),statement);
                                }
                            }
                        }
                    }
                    fileWithXmlSubfix.add(fileOrgin);
                }
            }


            File f = new File(System.getProperty("user.dir")+"/pom.xml");
            Document pomXml = reader.read(f);
            Element root = pomXml.getRootElement();
            Element artifcactIdTag;
            for (Iterator i = root.elementIterator("artifactId"); i.hasNext(); ) {
                artifcactIdTag = (Element) i.next();
                projectId =  artifcactIdTag.getStringValue();
                //System.out.print("artifactId:" + projectId);
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

        super.projectFailed(event);

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
        System.out.println("######################came forkFailed");
        super.forkFailed(event);
    }

    @Override
    public void mojoSkipped(ExecutionEvent event) {
        super.mojoSkipped(event);
    }

    @Override
    public void mojoStarted(ExecutionEvent event) {
        //System.out.println("######################came mojoStarted");
        super.mojoStarted(event);
    }

    @Override
    public void mojoSucceeded(ExecutionEvent event) {
        //System.out.println("######################came mojoSucceeded");
        super.mojoSucceeded(event);
    }

    @Override
    public void mojoFailed(ExecutionEvent event) {
       // System.out.println("######################came mojoFailed");

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
        System.out.println("######################came forkedProjectFailed");
        super.forkedProjectFailed(event);
    }

    @Override
    public void initialize() throws InitializationException {
        System.out.println("######################came FlakyFailCaseHandler.initialize");
        delegate = new ExecutionEventLogger();
    }

    public void configure(ExecutionListener executionListener) {
        this.delegate = executionListener;
    }

}
