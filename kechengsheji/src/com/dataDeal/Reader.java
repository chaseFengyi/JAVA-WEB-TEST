package com.dataDeal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Reader {
	public void test() throws ClassNotFoundException, SQLException{
		//链接mysql数据库的驱动
//		Class.forName("com.mysql.jdbc.Driver");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//		System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		
		//获取数据库链接
		String url = "jdbc:mysql://localhost:3306/library";
		String user = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(
				url,user,password);
		
		//创建语句
		Statement st = conn.createStatement();
		
		//执行语句
		ResultSet rs = st.executeQuery("select *from 读者");
		
		//处理结果
		while(rs.next()){
			System.out.println(rs.getObject(1)+"\t"+
					rs.getObject(2)+"\t"+
					rs.getObject(3)+"\t ");
		}
		
		//释放资源
		rs.close();
		st.close();
		conn.close();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new Reader().test();
	}
}
