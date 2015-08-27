package com.service;


import static org.junit.Assert.*;

import org.junit.Test;

import com.model.User;
import com.spring.BeanFactory;
import com.spring.ClassPathXmlApplicationContext;
import com.userDao.UserDao;

public class UserServiceTest {

	@Test
	public void testAdd() throws Exception {
		/*BeanFactory factory = new ClassPathXmlApplicationContext();
		
		UserService service = new UserService();
		UserDao userDao = (UserDao)factory.getBean("u");
		service.setUserDao(userDao);*/
		/*User user = new User();
		service.add(user);*/
		
		BeanFactory factory = new ClassPathXmlApplicationContext();
		
		UserService service = (UserService)factory.getBean("userService");

		User user = new User();
		service.add(user);
	}

}
