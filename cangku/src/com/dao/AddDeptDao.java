package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Vo.DeptVo;
import com.tools.JdbcTools;

public class AddDeptDao {
	Connection connection=JdbcTools.getconnection();
	Statement sta=null;
	PreparedStatement ps=null;
	ResultSet res=null;
	
	public int addDept(DeptVo vo){
		String sql="INSERT INTO department(departmentName,departmentManager,description,status) values(?,?,?,?)";
		int i=0;
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, vo.getDeptname());
			ps.setString(2, vo.getDeptmaster());
			ps.setString(3, vo.getDesc());
			ps.setString(4, vo.getStatus());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
