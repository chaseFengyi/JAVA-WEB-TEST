package com.service;

import com.Vo.Resource;
import com.dao.AddResourceDao;

public class AddResourceService {
	AddResourceDao dao = new AddResourceDao();
	
	public int addResource(Resource resource){
		return dao.addResource(resource);
	}
}
