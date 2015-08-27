package com.service;

import com.dao.DeleteUserDao;

public class DeleteUserService {
	DeleteUserDao dao = new DeleteUserDao();
	
	public void deleteUser(int id){
		dao.deleteUser(id);
	}
}
