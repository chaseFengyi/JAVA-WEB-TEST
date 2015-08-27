package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Vo.UserVo;
import com.tools.JdbcTools;

public class UpdateUsersDao {
	public void updateUserInfo(UserVo vo){
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		
		String sql = "update users set username=?,password=?,usertype=?,department=?,createdate=?,email=?,status=? where userId=?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getUsername());
			ps.setString(2, vo.getPassword());
			ps.setString(3, vo.getUsertype());
			ps.setString(4, vo.getDepartment());
			ps.setString(5, vo.getCreatedate());
			ps.setString(6, vo.getEmail());
			ps.setString(7, vo.getStatus());
			ps.setInt(9, vo.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(res!=null){
					res.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
