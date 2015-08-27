package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import com.Vo.UserVo;
import com.tools.JdbcTools;


public class UserManagerDao  {
	public List<UserVo> getUsers(){
		List<UserVo> list=new ArrayList<UserVo>();
		Connection con=JdbcTools.getconnection();
		Statement sta=null;
		ResultSet res=null;
		try {
			String sql="SELECT * FROM users"; 
			sta=(Statement) con.createStatement();
			res=sta.executeQuery(sql);
			while(res.next()){
				UserVo vo=new UserVo();
				vo.setId(res.getInt(1));
				vo.setUsername(res.getString(2));
				vo.setPassword(res.getString(3));
				vo.setUsertype(res.getString(4));
				vo.setDepartment(res.getString("department"));
				vo.setCreatedate(res.getString(6));
				vo.setEmail(res.getString(7));
				vo.setStatus(res.getString(8));
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
