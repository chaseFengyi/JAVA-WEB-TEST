package com.fy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy FirstFilter");
	}

	//���û���������������������URLʱ��web�������ȵ���foFilter������FilterChain�������Ե���chain.doFilter�����������󴫸���һ��������
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("start doFilter FirstFilter");
//		arg2.doFilter(arg0, arg1); 
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		//�ض���
		response.sendRedirect(req.getContextPath() + "/main.jsp");
		//ת��
//		req.getRequestDispatcher("main.jsp").forward(arg0, arg1);
//		req.getRequestDispatcher("main.jsp").include(arg0, arg1);
		System.out.println("end doFilter FirstFilter");
	}

	//�÷������Զ�ȡweb.xml�ļ��й������Ĳ���
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init FirstFilter");
	}

}
