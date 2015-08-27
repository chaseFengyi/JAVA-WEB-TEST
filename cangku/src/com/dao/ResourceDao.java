package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Vo.Resource;
import com.tools.JdbcTools;

public class ResourceDao {

	public List<Resource> getAllResource() throws SQLException
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Resource> resources = new ArrayList<Resource>();
		try
		{
			
			conn = JdbcTools.getconnection();
			String sql = "select * from resource";
			 ps = conn.prepareStatement(sql);
			 rs = ps.executeQuery();
			while(rs.next())
			{
				Resource resource = new Resource();
				resource.setId(rs.getInt("id"));
				resource.setName(rs.getString("name"));
				resource.setDescription(rs.getString("description"));
				resource.setCreateTime(rs.getString("createtime"));
				resource.setStatus(rs.getString("status"));
				resources.add(resource);
			}
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
		return resources;
	}

	public Resource getResourceById(String id) throws SQLException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Resource resource = new Resource();
		try
		{
			
			conn = JdbcTools.getconnection();
			String sql = "select * from resource where id=?";
			 ps = conn.prepareStatement(sql);
			 ps.setString(1, id);
			 rs = ps.executeQuery();
			while(rs.next())
			{
				
				resource.setId(rs.getInt("id"));
				resource.setName(rs.getString("name"));
				resource.setDescription(rs.getString("description"));
				resource.setCreateTime(rs.getString("createtime"));
				resource.setStatus(rs.getString("status"));
				
			}
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
		return resource;
	}

	public void updateResource(Resource resource) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			
			conn = JdbcTools.getconnection();
			String sql = "update resource set name=?,description=?,createtime=?,status=? where id=?";
			 ps = conn.prepareStatement(sql);
			 ps.setString(1, resource.getName());
			 ps.setString(2, resource.getDescription());
			 ps.setString(3, resource.getCreateTime());
			 ps.setString(4, resource.getStatus());
			 ps.setInt(5, resource.getId());
			 ps.executeUpdate();
			
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

	public void deleteById(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			
			conn = JdbcTools.getconnection();
			String sql = "delete from resource where id=?";
			 ps = conn.prepareStatement(sql);
			 ps.setInt(1, id);
			 ps.executeUpdate();
			
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
