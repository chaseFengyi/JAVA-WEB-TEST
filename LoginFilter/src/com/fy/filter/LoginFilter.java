package com.fy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	
	private FilterConfig config ;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		HttpSession session = request.getSession();
		
		String noLoginPaths = config.getInitParameter("noLoginPaths");
		if(noLoginPaths != null){
			String[] strArray = noLoginPaths.split(";");
			for (int i = 0; i < strArray.length; i++) {
				System.out.println(strArray[i]+"00000000000");
				if(strArray[i] == null || "".equals(strArray[i])){
					continue;
				}
				System.out.println("request.getRequestURI="+request.getRequestURI());
				if(request.getRequestURI().indexOf(strArray[i]) != -1){
					System.out.println("nihao!");
					arg2.doFilter(request, response);
					System.out.println("filter"+i+"::1"+"strArray[i]="+strArray[i]);
					return;
				}
			}
		}
		
		if(request.getRequestURI().indexOf("login.jsp") != -1
				|| request.getRequestURI().indexOf("LoginServlet") != -1
				){
			arg2.doFilter(request, response);
			return;
		}
		
		//http://www.imooc.com/video/4479
		
		/*if(request.getRequestURI().indexOf("login.jsp") != -1){
			arg2.doFilter(request, response);
			return;
		}*/
		
		System.out.println("username="+session.getAttribute("username"));
		if(session.getAttribute("username") != null){
			arg2.doFilter(arg0, arg1);
		}else{
			response.sendRedirect("login.jsp");
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		config = arg0;
	}

}
