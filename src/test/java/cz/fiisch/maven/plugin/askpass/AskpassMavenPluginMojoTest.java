package cz.fiisch.maven.plugin.askpass;


import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.WithoutMojo;

import org.junit.Rule;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

public class AskpassMavenPluginMojoTest {
    @Rule
    public MojoRule rule = new MojoRule()
    {
        @Override
        protected void before() throws Throwable {
        }

        @Override
        protected void after() {
        }
    };

    @Test
    public void testSomething() throws Exception {
    	assertTrue(true);
    }

    /** Do not need the MojoRule. */
    @WithoutMojo
    @Test
    public void testSomethingWhichDoesNotNeedTheMojoAndProbablyShouldBeExtractedIntoANewClassOfItsOwn()
    {
        assertTrue( true );
    }

}

