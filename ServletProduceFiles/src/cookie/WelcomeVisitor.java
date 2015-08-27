package cookie;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeVisitor extends HttpServlet {
	String newUserName = "";
	Cookie[] cookies = null;
	Cookie c = null;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		cookies = request.getCookies();
		String title;
		title = "Welcome, "+getCookieValue();
		newUserName = request.getParameter("Name");
		if(newUserName == null || newUserName.trim().equals("")){
			newUserName = "Anomymous";
		}
		if(newUserName != null){
			response.addCookie(makeCookie());
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>"+title+"</title></head>");
		out.println("<body bgcolor=\"#fff\">");
		out.println("<h1>"+title+"</h1>");
		out.println("<form action=\"ServletProduceFiles/cookie/WelcomeVisitor\" method=\"post\">");
		out.println("<p>input your name:</p>");
		out.println("<input type=\"text\" name=\"Name\">");
		out.println("<input type=\"submit\" value=\"submit form\">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	private Cookie makeCookie() {
		Cookie c = new Cookie("Name",newUserName);
		c.setMaxAge(60*60*24*365);
		System.out.println("name= "+c.getName());
		System.out.println("value= "+c.getValue());
		return c;
	}

	private String getCookieValue() {
		String cookieValue = "Anonymous";
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++){
				c = cookies[i];
				if(c.getName().equals("Name")){
					cookieValue = c.getValue();
					break;
				}
			}
		}
		return cookieValue;
	}

	

}
