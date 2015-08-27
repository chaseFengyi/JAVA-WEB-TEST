package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;

public class UserService {
	private UserDao userDao = new UserDao();
	public User findUserByNameAndPass(String userName,String userPass) {
		try {
//			System.out.println(userName+userPass);

			return userDao.findUserByNameAndPass(userName, userPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
