package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.UserVo;
import com.service.UpdateUsersService;
import com.service.UserDetailsService;

public class UpdateUserUIController extends HttpServlet {
	UserDetailsService service = new UserDetailsService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		UserVo vo = service.getUserDetails(id);
		
		request.setAttribute("UserVo", vo);
		request.getRequestDispatcher("../um/updateUsers.jsp").forward(request, response);
	}

}
