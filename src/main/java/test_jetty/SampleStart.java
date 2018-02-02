package test_jetty;

import org.eclipse.jetty.server.Server;

public class SampleStart {
	  public static void main(String[] args) throws Exception {
		  Server server = new Server(8080);
	        server.start();
	        server.dumpStdErr();
	        server.join();
	    }
}
