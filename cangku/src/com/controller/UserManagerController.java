package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.UserVo;
import com.alibaba.fastjson.JSONArray;
import com.dao.UserManagerDao;
import com.service.UserManagerService;

public class UserManagerController extends HttpServlet {
//	UserManagerDao service=new UserManagerDao();
	UserManagerService service = new UserManagerService();
	//@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		List<UserVo> list=service.getUsers();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../um/usersManagement.jsp").forward(request, response);
	}
}
