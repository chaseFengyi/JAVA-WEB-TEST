package com.service;

import java.util.List;

import com.Vo.UserVo;
import com.dao.UserManagerDao;


public class UserManagerService  {
	UserManagerDao dao=new UserManagerDao();
	public List<UserVo> getUsers(){
		return dao.getUsers();
	}
}
