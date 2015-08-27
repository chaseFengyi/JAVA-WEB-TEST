package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.DeptVo;
import com.service.UpdateDeptService;

public class UpdateDeptController extends HttpServlet {
	UpdateDeptService service = new UpdateDeptService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		DeptVo vo = new DeptVo();
		vo.setId(id);
		vo.setDeptname(request.getParameter("deptName"));
		vo.setDeptmaster(request.getParameter("manager"));
		vo.setDesc(request.getParameter("comments"));
		vo.setStatus(request.getParameter("status"));
		
		service.updateDept(vo);
		
		response.sendRedirect("../servlet/DepartmentManagerController");
	}

}
