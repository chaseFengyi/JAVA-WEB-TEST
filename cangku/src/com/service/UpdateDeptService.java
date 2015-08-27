package com.service;

import com.Vo.DeptVo;
import com.dao.UpdateDeptDao;

public class UpdateDeptService {
	UpdateDeptDao dao = new UpdateDeptDao();
	
	public void updateDept(DeptVo vo){
		dao.updateDept(vo);
	}
}
