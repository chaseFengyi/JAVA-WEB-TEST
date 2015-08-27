package com.service;

import com.Vo.UserVo;
import com.dao.UserDetailsDao;

public class UserDetailsService {
	
	UserDetailsDao dao = new UserDetailsDao();
	
	public UserVo getUserDetails(int id){
		return dao.getUserById(id);
	}
}
