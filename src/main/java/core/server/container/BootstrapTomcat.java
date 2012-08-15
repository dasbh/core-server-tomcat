package core.server.container;

import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

public class BootstrapTomcat {

	public static void main(String[] args) throws Exception {

		String appBase = ".";// args[0];
		Integer port = 8080;// Integer.valueOf(args[1]);

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(port);

		tomcat.setBaseDir(".");
		tomcat.getHost().setAppBase(appBase);

		String contextPath = "/myapp";

		// Add AprLifecycleListener
		StandardServer server = (StandardServer) tomcat.getServer();
		AprLifecycleListener listener = new AprLifecycleListener();
		server.addLifecycleListener(listener);

		tomcat.addWebapp(contextPath, appBase);
		tomcat.start();
		tomcat.getServer().await();

	}

}
