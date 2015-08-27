package com.service;

import com.Vo.Resource;
import com.dao.ResourceDetailsDao;

public class ResourceDetailsService {
	ResourceDetailsDao dao = new ResourceDetailsDao();
	
	public Resource getResourceInfo(int id){
		return dao.getResourceInfo(id);
	}
}
