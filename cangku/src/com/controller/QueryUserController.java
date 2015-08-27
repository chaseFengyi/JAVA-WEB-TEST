package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.UserVo;
import com.service.QueryUserService;

public class QueryUserController extends HttpServlet {
	QueryUserService service = new QueryUserService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("userName");
		List<UserVo> list = service.queryUsersInfo(userName);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("../um/usersManagement.jsp").forward(request, response);
	}

}
