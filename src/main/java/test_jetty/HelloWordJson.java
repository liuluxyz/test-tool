package test_jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWordJson extends AbstractHandler {
	
	@Override
	public void handle(String arg0, Request arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws IOException, ServletException {
		arg3.setContentType("text/html; charset=utf-8");
		arg3.setStatus(HttpServletResponse.SC_OK);
//		arg3.getWriter().println("Hello World");
		JSONObject res = new JSONObject();
		try {
			res.put("res1", 11);
			res.put("res2", 22);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		arg3.getWriter().println(res);
//		arg3.entity(jsonString).type(MediaType.APPLICATION_JSON).build();
		
		
		arg1.setHandled(true);
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		server.setHandler(new HelloWordJson());
		server.start();
		server.join();
	}
}
