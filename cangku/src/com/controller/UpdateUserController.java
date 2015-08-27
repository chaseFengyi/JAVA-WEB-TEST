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

public class UpdateUserController extends HttpServlet {
	UpdateUsersService service = new UpdateUsersService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		UserVo vo = new UserVo();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setUsername(request.getParameter("userName"));
		vo.setPassword(request.getParameter("password"));
		vo.setUsertype(request.getParameter("utype"));
		vo.setDepartment(request.getParameter("deptName"));
		vo.setEmail(request.getParameter("email"));
		vo.setCreatedate(request.getParameter("createTime"));
		vo.setStatus(request.getParameter("status"));
		System.out.println("userVo="+vo);
		service.updateUserInfo(vo);
		
		response.sendRedirect("../servlet/UserManagerController");
	}

}
