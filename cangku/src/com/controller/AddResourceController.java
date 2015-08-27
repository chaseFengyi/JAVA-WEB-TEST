package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.Resource;
import com.service.AddResourceService;

public class AddResourceController extends HttpServlet {
	AddResourceService service = new AddResourceService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Resource resource = new Resource();
		
		if (request.getParameter("resourceName") == null || request.getParameter("resourceName").equals("")
				|| request.getParameter("comments") == null || request.getParameter("comments").equals("")
				|| request.getParameter("status") == null || request.getParameter("status").equals("")
				|| request.getParameter("createTime") == null || request.getParameter("createTime").equals("")) {
			response.sendRedirect("../um/addResource.jsp");
		} else {
			resource.setName(request.getParameter("resourceName"));
			resource.setDescription(request.getParameter("comments"));
			resource.setCreateTime(request.getParameter("createTime"));
			resource.setStatus(request.getParameter("status"));

			int i = service.addResource(resource);
			if (i != 0) {
				response.sendRedirect("../servlet/ResourceManagerController");
			} else {
				response.sendRedirect("../um/addResource.jsp");
			}
		}
	}

}
