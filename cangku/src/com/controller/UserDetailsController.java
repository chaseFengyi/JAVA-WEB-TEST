package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.UserVo;
import com.service.UserDetailsService;

public class UserDetailsController extends HttpServlet {
	
	UserDetailsService service = new UserDetailsService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		int UserID = Integer.parseInt(request.getParameter("id"));
		
		UserVo userVo = service.getUserDetails(UserID);
		request.setAttribute("userVo", userVo);
		request.getRequestDispatcher("../um/usersDetail.jsp").forward(request, response);
	}

}
