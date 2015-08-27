package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Vo.Resource;
import com.dao.ResourceDao;

public class ResourceService {
	private ResourceDao resourceDao = new ResourceDao();
	
	public List<Resource> getAllResource()
	{
		try {
			return resourceDao.getAllResource();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Resource getResourceById(String id) {
		// TODO Auto-generated method stub
		try
		{
			return resourceDao.getResourceById(id);
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateResource(Resource resource) {
		// TODO Auto-generated method stub
		try
		{
			resourceDao.updateResource(resource);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		try
		{
			resourceDao.deleteById(id);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
