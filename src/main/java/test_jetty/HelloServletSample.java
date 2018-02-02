package test_jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HelloServletSample  {
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/demo");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new HelloServlet("1")), "/servlet1");
        context.addServlet(new ServletHolder(new HelloServlet("2")), "/servlet2");

		server.start();
		server.join();
	}
	
	@SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
        String target = "world";

        public HelloServlet(String target){
            this.target = target;
        }
        @Override
        protected void doGet( HttpServletRequest request,
                            HttpServletResponse response ) throws ServletException,
                IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello " + target + "</h1>");
        }
    }
	
}
