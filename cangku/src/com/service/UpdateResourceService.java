package com.service;

import com.Vo.Resource;
import com.dao.UpdateResourceDao;

public class UpdateResourceService {
	UpdateResourceDao dao = new UpdateResourceDao();
	
	public void updateResource(Resource resource){
		dao.updateResourceInfo(resource);
	}
}

