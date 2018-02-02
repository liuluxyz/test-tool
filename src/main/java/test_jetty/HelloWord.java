package test_jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWord extends AbstractHandler {
	
	@Override
	public void handle(String arg0, Request arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws IOException, ServletException {
		arg3.setContentType("text/html; charset=utf-8");
		arg3.setStatus(HttpServletResponse.SC_OK);
		arg3.getWriter().println("Hello World");
		arg1.setHandled(true);
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new HelloWord());
		server.start();
		server.join();
	}
}
