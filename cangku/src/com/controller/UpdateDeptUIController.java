package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.DeptVo;
import com.service.DepartDetialService;

public class UpdateDeptUIController extends HttpServlet {
	DepartDetialService service = new DepartDetialService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		DeptVo vo = service.getDeptInfo(id);
		request.setAttribute("DeptVo", vo);
		request.getRequestDispatcher("../um/updateDepartment.jsp").forward(request, response);
	}

}
