package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Vo.Resource;
import com.service.QueryResourceService;

public class QueryResourceController extends HttpServlet {
	QueryResourceService service = new QueryResourceService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String resourceName = request.getParameter("resourceName");
		
		List<Resource> list = service.queryResourcesInfo(resourceName);
		request.setAttribute("resources", list);
		request.getRequestDispatcher("../um/resourceManagement.jsp").forward(request, response);
	}

}
