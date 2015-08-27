package com.service;

import java.util.List;

import com.Vo.UserVo;
import com.dao.QueryUserDao;

public class QueryUserService {
	QueryUserDao dao = new QueryUserDao();
	
	public List<UserVo> queryUsersInfo(String userName){
		return dao.queryUsersInfo(userName);
	}
}
