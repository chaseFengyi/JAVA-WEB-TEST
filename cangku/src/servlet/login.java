package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class login extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user=request.getParameter("userName");
		String pass=request.getParameter("password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/storagemanage", "root","123456");
			
			Statement stat=conn.createStatement();
			String sql="select * from users where username='"+user+"' and password='"+pass+"'";
			
			ResultSet rs = stat.executeQuery(sql);
			
			if(rs.next()){
				response.sendRedirect("../common/mainpage.jsp");
				}
			else response.sendRedirect("../error.jsp");
			
			rs.close();
		    stat.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
