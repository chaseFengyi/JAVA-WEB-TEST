package com.service;

import com.dao.DeleteResourceDao;

public class DeleteResourceService {
	DeleteResourceDao dao = new DeleteResourceDao();
	
	public void deleteResource(int id){
		dao.deleteResource(id);
	}
}
