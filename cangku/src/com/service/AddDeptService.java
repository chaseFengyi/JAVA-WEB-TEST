package com.service;

import com.Vo.DeptVo;
import com.dao.AddDeptDao;

public class AddDeptService {
	AddDeptDao dao = new AddDeptDao();
	
	public int addDept(DeptVo vo){
		return dao.addDept(vo);
	}
}	
