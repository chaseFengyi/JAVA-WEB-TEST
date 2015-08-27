package com.service;

import java.util.List;

import com.Vo.Resource;
import com.dao.QueryResourceDao;

public class QueryResourceService {
	QueryResourceDao dao = new QueryResourceDao();
	
	public List<Resource> queryResourcesInfo(String resourceName){
		return dao.queryResourceInfo(resourceName);
	}
}
