package com.service;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.User;

public class UserServiceTest {

	@Test
	public void testAdd() throws Exception {
		/*BeanFactory factory = new ClassPathXmlApplicationContext();
		
		UserService service = new UserService();
		UserDao userDao = (UserDao)factory.getBean("u");
		service.setUserDao(userDao);*/
		/*User user = new User();
		service.add(user);*/
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
		
		UserService service = (UserService)factory.getBean("userService");

		User user = new User();
		service.add(user);
	}

}
