package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Vo.DeptVo;
import com.Vo.UserVo;
import com.tools.JdbcTools;

public class QueryUserDao {
	public List<UserVo> queryUsersInfo(String userName){
		List<UserVo> list=new ArrayList<UserVo>();
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		UserVo vo = null;

		String sql = "select * from users where username=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			
			res = ps.executeQuery();
			res = ps.getResultSet();
			while(res.next()){
				vo = new UserVo();
				vo.setId(res.getInt(1));
				vo.setUsername(res.getString(2));
				vo.setPassword(res.getString(3));
				vo.setUsertype(res.getString(4));
				vo.setDepartment(res.getString(5));
				vo.setCreatedate(res.getString(6));
				vo.setEmail(res.getString(7));
				vo.setStatus(res.getString(8));
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
