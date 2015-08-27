package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import com.Vo.DeptVo;
import com.Vo.UserVo;
import com.tools.JdbcTools;
import java.sql.*;
public class AddUsersDao {
	Connection connection=JdbcTools.getconnection();
	Statement sta=null;
	PreparedStatement ps=null;
	ResultSet res=null;
	public List<DeptVo> getdepts(){
		List<DeptVo> list=new ArrayList<DeptVo>();
		try {
			String sql="SELECT * FROM dept";
			sta=(Statement) connection.createStatement();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int save(UserVo obj){
		String sql="INSERT INTO users(username,password,usertype,department,createdate,email,status) values(?,?,?,?,?,?,?)";
		System.out.println(obj.getUsertype());
		int i=0;
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getUsertype());
			ps.setString(4, obj.getDepartment());
			ps.setString(5, obj.getCreatedate());
			ps.setString(6, obj.getEmail());
			ps.setString(7, obj.getStatus());
			i=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
