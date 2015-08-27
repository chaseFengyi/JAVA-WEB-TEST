package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Vo.Resource;
import com.tools.JdbcTools;

public class DeleteDeptDao {
	public void deleteDept(int id){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Resource> resources = new ArrayList<Resource>();
		try
		{
			conn = JdbcTools.getconnection();
			String sql = "delete from department where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
