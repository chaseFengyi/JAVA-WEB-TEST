package com.service;

import java.util.List;

import com.Vo.DeptVo;
import com.dao.QueryDeptDao;

public class QueryDeptService {
	QueryDeptDao dao = new QueryDeptDao();
	
	public List<DeptVo> queryDeptInfo(String deptName){
		return dao.getDeptInfo(deptName);
	}
}
