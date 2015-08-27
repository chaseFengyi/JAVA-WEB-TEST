package com.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.ResourceService;

public class DeleteResourceServlet extends HttpServlet {

	private ResourceService resourceService = new ResourceService();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		int id = Integer.parseInt(req.getParameter("id"));
		resourceService.deleteById(id);
		req.getRequestDispatcher("/resource/ListResourceServlet").forward(req, resp);
		
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
