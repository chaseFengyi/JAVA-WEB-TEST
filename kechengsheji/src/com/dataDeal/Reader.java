package com.dataDeal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Reader {
	public void test() throws ClassNotFoundException, SQLException{
		//����mysql���ݿ������
//		Class.forName("com.mysql.jdbc.Driver");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//		System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		
		//��ȡ���ݿ�����
		String url = "jdbc:mysql://localhost:3306/library";
		String user = "root";
		String password = "123456";
		Connection conn = DriverManager.getConnection(
				url,user,password);
		
		//�������
		Statement st = conn.createStatement();
		
		//ִ�����
		ResultSet rs = st.executeQuery("select *from ����");
		
		//������
		while(rs.next()){
			System.out.println(rs.getObject(1)+"\t"+
					rs.getObject(2)+"\t"+
					rs.getObject(3)+"\t ");
		}
		
		//�ͷ���Դ
		rs.close();
		st.close();
		conn.close();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new Reader().test();
	}
}
