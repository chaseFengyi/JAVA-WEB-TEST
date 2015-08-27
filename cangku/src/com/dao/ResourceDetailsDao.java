package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Vo.Resource;
import com.tools.JdbcTools;

public class ResourceDetailsDao {
	public Resource getResourceInfo(int id){
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		Resource resource = null;

		String sql = "select * from resource where id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			res = ps.executeQuery();
			res = ps.getResultSet();
			while(res.next()){
				resource = new Resource();
				resource.setId(res.getInt(1));
				resource.setName(res.getString(2));
				resource.setDescription(res.getString(3));
				resource.setCreateTime(res.getString(4));
				resource.setStatus(res.getString(5));
			}
			
			return resource;
			
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
		return resource;
	}
}
