package com.dataDeal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Tools {
	String url = "jdbc:mysql://localhost:3306/library";
	String user = "root";
	String password = "123456";
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	/*private Tools() {
	}*/

	//注册驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("注册驱动失败");
		}
	}
	
	//建立连接
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	
	//释放资源
	public void free(Connection conn, Statement st, ResultSet rs){
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("资源没有释放");
			System.exit(0);
		}finally{
			try{
				if(st != null)
					st.close();
			}catch (SQLException e) {
				System.out.println("资源没有释放");
				System.exit(0);
			}finally{
				try{
					if(conn != null)
						conn.close();
				}catch (SQLException e) {
					System.out.println("资源没有释放");
					System.exit(0);
				}
			}
		}
	}
}
