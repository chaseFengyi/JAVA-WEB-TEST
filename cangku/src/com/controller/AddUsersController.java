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

import com.Vo.DeptVo;
import com.Vo.UserVo;
import com.alibaba.fastjson.JSONArray;
import com.service.AddUsersService;

public class AddUsersController extends HttpServlet {
	AddUsersService service = new AddUsersService();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse respnose) throws ServletException, IOException {
		List<DeptVo> list = service.getdepts();
		respnose.setCharacterEncoding("utf-8");
		PrintWriter pw = respnose.getWriter();
		Map map = new HashMap();
		if (list.size() != 0) {
			map.put("list", list);
		} else {
			map.put("list", null);
		}
		pw.write(JSONArray.toJSONString(map));
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse respnose) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		respnose.setCharacterEncoding("utf-8");
		UserVo vo = new UserVo();
		System.out.println(request.getParameter("cancel"));
		System.out.println(request.getParameter("registerSub"));
		/*if (request.getParameter("cancel") != null
				|| !request.getParameter("cancel").equals(null)) {
			System.out.println("cancel");
			respnose.sendRedirect("../servlet/UserManagerController");
		} else {*/
			if (request.getParameter("password") == null
					|| request.getParameter("password") == ""
					|| !request.getParameter("password").equals(
							request.getParameter("confirm"))) {
				respnose.sendRedirect("../um/addUsers.jsp");
			} else {
				vo.setUsername(request.getParameter("userName"));
				vo.setPassword(request.getParameter("password"));
				vo.setUsertype(request.getParameter("utype"));
				vo.setDepartment(request.getParameter("deptName"));
				vo.setEmail(request.getParameter("email"));
				vo.setCreatedate(request.getParameter("createTime"));
				vo.setStatus(request.getParameter("status"));
				int i = service.save(vo);
				if (i != 0) {
					// respnose.sendRedirect("../um/usersManagement.jsp");
					respnose.sendRedirect("../servlet/UserManagerController");
				} else {
					respnose.sendRedirect("../um/addUsers.jsp");
				}
			}
//		}
	}
}
