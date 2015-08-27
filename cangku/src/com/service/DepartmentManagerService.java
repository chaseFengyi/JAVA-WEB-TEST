package com.service;

import java.util.List;

import com.Vo.DeptVo;
import com.dao.DepartmentManagerDao;

public class DepartmentManagerService {
	DepartmentManagerDao dao = new DepartmentManagerDao();
	
	public List<DeptVo> getDeptInfo(){
		return dao.getDeptInfo();
	}
}
