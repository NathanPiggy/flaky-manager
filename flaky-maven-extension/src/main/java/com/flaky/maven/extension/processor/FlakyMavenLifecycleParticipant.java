package com.flaky.maven.extension.processor;

import org.apache.maven.AbstractMavenLifecycleParticipant;
import org.apache.maven.MavenExecutionException;
import org.apache.maven.execution.MavenSession;
import org.codehaus.plexus.component.annotations.Component;

@Component( role = AbstractMavenLifecycleParticipant.class, hint = "flaky")
public class FlakyMavenLifecycleParticipant extends AbstractMavenLifecycleParticipant
{

    private FlakyFailCaseHandler flakyFailCaseHandler;

    @Override
    public void afterSessionStart( MavenSession session )
        throws MavenExecutionException
    {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
 
 
    @Override
    public void afterProjectsRead( MavenSession session )
        throws MavenExecutionException
    {

        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        flakyFailCaseHandler = new FlakyFailCaseHandler();
        flakyFailCaseHandler.configure(session.getRequest().getExecutionListener());
        session.getRequest().setExecutionListener(flakyFailCaseHandler);
    }

    public FlakyMavenLifecycleParticipant() {
        super();
    }
}