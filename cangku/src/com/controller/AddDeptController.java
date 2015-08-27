package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.DeptVo;
import com.service.AddDeptService;

public class AddDeptController extends HttpServlet {
	AddDeptService service = new AddDeptService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		DeptVo vo = new DeptVo();

		if (request.getParameter("deptName") == null || request.getParameter("deptName").equals("")
				|| request.getParameter("manager") == null || request.getParameter("manager").equals("")
				|| request.getParameter("comments") == null || request.getParameter("comments").equals("")) {
			response.sendRedirect("../um/addDepartment.jsp");
		} else {
			vo.setDeptname(request.getParameter("deptName"));
			vo.setDeptmaster(request.getParameter("manager"));
			vo.setDesc(request.getParameter("comments"));
			vo.setStatus(request.getParameter("status"));

			int i = service.addDept(vo);
			if (i != 0) {
				response.sendRedirect("../servlet/DepartmentManagerController");
			} else {
				response.sendRedirect("../um/addDepartment.jsp");
			}
		}
	}

}
