package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Vo.Resource;
import com.tools.JdbcTools;

public class QueryResourceDao {
	public List<Resource> queryResourceInfo(String resourceName){
		List<Resource> list=new ArrayList<Resource>();
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		Resource vo = null;

		String sql = "select * from resource where name=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, resourceName);
			
			res = ps.executeQuery();
			res = ps.getResultSet();
			while(res.next()){
				vo = new Resource();
				vo.setId(res.getInt(1));
				vo.setName(res.getString(2));
				vo.setDescription(res.getString(3));
				vo.setCreateTime(res.getString(4));
				vo.setStatus(res.getString(5));
				list.add(vo);
			}
			
			return list;
			
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
		return list;
	}
}
 