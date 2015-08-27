package com.service;

import java.util.List;

import com.Vo.DeptVo;
import com.Vo.UserVo;
import com.dao.AddUsersDao;

public class AddUsersService {
	AddUsersDao dao=new AddUsersDao();
	public List<DeptVo> getdepts(){
		return dao.getdepts();
	}
	public int save(UserVo obj){
		return dao.save(obj);
	}
}
