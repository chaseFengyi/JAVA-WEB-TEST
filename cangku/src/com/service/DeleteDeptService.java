package com.service;

import com.dao.DeleteDeptDao;

public class DeleteDeptService {
	DeleteDeptDao dao = new DeleteDeptDao();
	
	public void deleteDept(int id){
		dao.deleteDept(id);
	}
}
