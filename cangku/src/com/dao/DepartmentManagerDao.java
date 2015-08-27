package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Vo.DeptVo;
import com.Vo.UserVo;
import com.tools.JdbcTools;

public class DepartmentManagerDao {
	public List<DeptVo> getDeptInfo(){
		List<DeptVo> list=new ArrayList<DeptVo>();
		Connection con=JdbcTools.getconnection();
		Statement sta=null;
		ResultSet res=null;
		try {
			String sql="SELECT * FROM department"; 
			sta=(Statement) con.createStatement();
			res=sta.executeQuery(sql);
			while(res.next()){
				DeptVo vo=new DeptVo();
				vo.setId(res.getInt(1));
				vo.setDeptname(res.getString(2));
				vo.setDeptmaster(res.getString(3));
				vo.setDesc(res.getString(4));
				vo.setStatus(res.getString(5));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(res!=null){
					res.close();
				}
				if(sta!=null){
					sta.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
