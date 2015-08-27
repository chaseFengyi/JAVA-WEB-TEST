package com.mymuji.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mymuji.model.MujiInfoBean;
import com.mymuji.service.MujiInfoService;

public class UserLoginCl extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("accountNum");
		String password = request.getParameter("password");
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		MujiInfoBean info = new MujiInfoBean();
		info.setAccountNum(userName);
		info.setPassword(password);
		//从数据库中查找
		info = MujiInfoService.checkByAccountNumAndPassword(info);
		
		JSONObject.fromObject(info);
		
		out.flush();
		out.close();
	}

}
