package rico.client_server_data_exchange.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet4AndroidClient extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginName = request.getParameter("LoginName");
		String loginPassword = request.getParameter("LoginPassword");
		
		System.out.println(loginName);
		System.out.println(loginPassword);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		
		try{
			out = response.getWriter();
			if(loginName.equals("tom") && loginPassword.equals("123")){
				out.print("success");
			}else{
				out.print("failed");			
			}
		}finally{
			if(out != null){
				out.close();
			}
		}
		
	}

}
