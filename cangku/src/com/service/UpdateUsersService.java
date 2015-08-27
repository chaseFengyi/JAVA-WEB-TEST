package com.service;

import com.Vo.UserVo;
import com.dao.UpdateUsersDao;

public class UpdateUsersService {
	UpdateUsersDao dao = new UpdateUsersDao();
	
	public void updateUserInfo(UserVo vo){
		dao.updateUserInfo(vo);
	}
}
