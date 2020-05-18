package cz.fiisch.maven.plugin.askpass;


import java.io.Console;
import java.util.List;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.settings.Server;

@Mojo( name = "askpass", defaultPhase = LifecyclePhase.NONE )
public class AskpassMavenPluginMojo extends AbstractMojo {

	@Parameter( defaultValue = "${session}", readonly = false )
	private MavenSession session;

	@Parameter( defaultValue = "${askpass.password-placeholder}", readonly = true )
	private String passwordPlaceholder;

    public void execute() throws MojoExecutionException, MojoFailureException {
		List<Server> servers = session.getSettings().getServers();
        for (Server s : servers) {
			//System.out.println("server| id: " + s.getId() + " username: " + s.getUsername() + " password: " + s.getPassword());
			if (s.getPassword().equals(passwordPlaceholder == null ? "askpass-maven-plugin" : passwordPlaceholder)) {
				Console console = System.console();
				if (console == null) {
					throw new MojoFailureException("Cannot get java.io.Console instance.");
				}
				char[] passwordArray = console.readPassword("Server " + s.getId() + ", username: " + s.getUsername() + ". Enter password: ");
				//console.printf("Password is: %s%n", new String(passwordArray));
				s.setPassword(new String(passwordArray));
			}
		}
    }
}
