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

	//ע������
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ע������ʧ��");
		}
	}
	
	//��������
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	
	//�ͷ���Դ
	public void free(Connection conn, Statement st, ResultSet rs){
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("��Դû���ͷ�");
			System.exit(0);
		}finally{
			try{
				if(st != null)
					st.close();
			}catch (SQLException e) {
				System.out.println("��Դû���ͷ�");
				System.exit(0);
			}finally{
				try{
					if(conn != null)
						conn.close();
				}catch (SQLException e) {
					System.out.println("��Դû���ͷ�");
					System.exit(0);
				}
			}
		}
	}
}
