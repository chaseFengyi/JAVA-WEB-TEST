package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Vo.DeptVo;
import com.tools.JdbcTools;
 
public class QueryDeptDao {
	public List<DeptVo> getDeptInfo(String deptName){
		List<DeptVo> list=new ArrayList<DeptVo>();
		Connection con = JdbcTools.getconnection();
		PreparedStatement ps = null;
		ResultSet res = null;
		DeptVo vo = null;

		String sql = "select * from department where departmentName=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, deptName);
			
			res = ps.executeQuery();
			res = ps.getResultSet();
			while(res.next()){
				vo = new DeptVo();
				vo.setId(res.getInt(1));
				vo.setDeptname(res.getString(2));
				vo.setDeptmaster(res.getString(3));
				vo.setDesc(res.getString(4));
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
