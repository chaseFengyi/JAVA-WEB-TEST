package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.Resource;
import com.dao.ResourceDetailsDao;
import com.service.ResourceDetailsService;
import com.service.UpdateResourceService;

public class UpdateResourceController extends HttpServlet {
	UpdateResourceService service = new UpdateResourceService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Resource resource = new Resource();
		resource.setId(Integer.parseInt(request.getParameter("id")));
		resource.setName(request.getParameter("name"));
		resource.setDescription(request.getParameter("description"));
		resource.setCreateTime(request.getParameter("createTime"));
		resource.setStatus(request.getParameter("status"));
		service.updateResource(resource);
		response.sendRedirect("../servlet/ResourceManagerController");
	}

}
