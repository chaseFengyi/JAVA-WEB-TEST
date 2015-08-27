package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Vo.Resource;
import com.tools.JdbcTools;

public class AddResourceDao {
	Connection connection=JdbcTools.getconnection();
	Statement sta=null;
	PreparedStatement ps=null;
	ResultSet res=null;
	
	public int addResource(Resource resource){
		String sql="INSERT INTO resource(name,description,createtime,status) values(?,?,?,?)";
		int i=0;
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, resource.getName());
			ps.setString(2, resource.getDescription());
			ps.setString(3, resource.getCreateTime());
			ps.setString(4, resource.getStatus());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
