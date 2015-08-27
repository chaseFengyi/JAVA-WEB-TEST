package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.DeptVo;
import com.service.QueryDeptService;

public class QueryDeptController extends HttpServlet {
	QueryDeptService service = new QueryDeptService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		DeptVo vo = new DeptVo();
		String deptName = request.getParameter("deptName");
		
		List<DeptVo> list = service.queryDeptInfo(deptName);
		request.setAttribute("DeptVo", list);
		request.getRequestDispatcher("../um/departmentManagement.jsp").forward(request, response);
	}

}
