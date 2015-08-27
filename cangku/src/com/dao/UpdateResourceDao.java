package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Vo.Resource;
import com.tools.JdbcTools;

public class UpdateResourceDao {
	public void updateResourceInfo(Resource resource){
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		
		String sql = "update resource set name=?,description=?,createtime=?,status=? where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, resource.getName());
			ps.setString(2, resource.getDescription());
			ps.setString(3, resource.getCreateTime());
			ps.setString(4, resource.getStatus());
			ps.setInt(5, resource.getId());
			
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
