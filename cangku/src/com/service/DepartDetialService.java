package com.service;

import com.Vo.DeptVo;
import com.dao.DepartDetailsDao;

public class DepartDetialService {
	DepartDetailsDao dao = new DepartDetailsDao();
	
	public DeptVo getDeptInfo(int id){
		return dao.getDeptInfo(id);
	}
}
