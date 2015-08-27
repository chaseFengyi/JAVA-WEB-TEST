package com.service;

import com.dao.impl.UserDaoImpl;
import com.model.User;
import com.userDao.UserDao;

public class UserService {
	
	private UserDao userDao = new UserDaoImpl();
	
	
	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void add(User user){
		this.userDao.save(user);
	}
}
