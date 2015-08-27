package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.Resource;
import com.service.ResourceDetailsService;

public class UpdateResourceUIController extends HttpServlet {
	ResourceDetailsService service = new ResourceDetailsService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Resource resource = service.getResourceInfo(id);
		
		request.setAttribute("resource", resource);
		request.getRequestDispatcher("../um/updateResource.jsp").forward(request, response);

	}

}
