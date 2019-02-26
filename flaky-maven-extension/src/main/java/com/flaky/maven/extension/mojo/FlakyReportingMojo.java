package com.flaky.maven.extension.mojo;

import com.alibaba.druid.support.json.JSONUtils;
import com.flaky.maven.extension.util.DataBaseUtil;
import com.flaky.maven.extension.util.FlakySurefireReportParser;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.surefire.report.ReportTestCase;
import org.apache.maven.plugins.surefire.report.ReportTestSuite;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.MavenReportException;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Mojo(name = "report", defaultPhase = LifecyclePhase.VERIFY)
public class FlakyReportingMojo extends AbstractMojo {
	@Component
	private MavenProject project;

	@Parameter(readonly = true, required= true)
	private String reportedTestsFile;

	public FlakyReportingMojo() {
		super();
	}

	@Override
	public void setLog(Log log) {
		super.setLog(log);
	}

	@Override
	public Log getLog() {
		return super.getLog();
	}

	@Override
	public Map getPluginContext() {
		return super.getPluginContext();
	}

	@Override
	public void setPluginContext(Map pluginContext) {
		super.setPluginContext(pluginContext);
	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("######################came  FlakyReportingMojo.execute");
		System.out.println("######################c  project:"+  project);
		System.out.println("######################c  project.getArtifactId():"+  project.getArtifactId());
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DataBaseUtil.getDbConnection();
			statement = DataBaseUtil.getDbStatement(connection);
			List<ReportTestSuite> tests = null;
			tests = this.getParser("surefire-reports").parseXMLReportFiles();
			System.out.println("$$$$tests.size() = "+ tests.size());
			for(ReportTestSuite s : tests) {
				for (ReportTestCase c : s.getTestCases()) {
					System.out.println("$$$$"+ JSONUtils.toJSONString(c));

				}
			}
			//check
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MavenReportException e) {
			e.printStackTrace();
		} finally {
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

	private FlakySurefireReportParser parser;
	public FlakySurefireReportParser getParser(String dir) {
		if (parser == null)
			this.parser = new FlakySurefireReportParser(Collections.singletonList(new File(project.getBasedir().getAbsolutePath() + "/target/" + dir)), Locale.US);
		return parser;
	}
}
