package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Vo.UserVo;
import com.tools.JdbcTools;

public class UserDetailsDao {
	public UserVo getUserById(int id) {
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		UserVo vo = null;

		String sql = "select * from users where userId=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			res = ps.executeQuery();
			res = ps.getResultSet();
			while(res.next()){
				vo = new UserVo();
				vo.setId(res.getInt(1));
				vo.setUsername(res.getString(2));
				vo.setUsertype(res.getString(4));
				vo.setDepartment(res.getString("department"));
				vo.setCreatedate(res.getString(6));
				vo.setEmail(res.getString(7));
				vo.setStatus(res.getString(8));
			}
			
			return vo;
			
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
		return vo;
	}
}
