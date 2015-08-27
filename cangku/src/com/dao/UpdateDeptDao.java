package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Vo.DeptVo;
import com.tools.JdbcTools;

public class UpdateDeptDao {
	public void updateDept(DeptVo vo){
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		
		String sql = "update department set departmentName=?,departmentManager=?,description=?,status=? where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getDeptname());
			ps.setString(2, vo.getDeptmaster());
			ps.setString(3, vo.getDesc());
			ps.setString(4, vo.getStatus());
			ps.setInt(5, vo.getId());
			
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
