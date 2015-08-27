package com.dataDeal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SelectData {
	public Map<String, Object> select(String sql){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Tools t = null;
		String className = "com.dataDeal.Tools";
		Class tc = null;

		Map<String, Object> data = null;
		try{
			//ͨ��������ƣ�ʹ����װ����װ�ظ���
			tc = Class.forName(className);
			//���������ʵ��
			t = (Tools)tc.newInstance();
			//ͨ�����洴������ʵ���������䷽��
			//�������ݿ�
			conn = t.getConnection();
			//�����������
			ps = conn.prepareStatement(sql);
			//ִ�����
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){/*
				System.out.print(rsmd.getColumnClassName(i)+"\t");
				System.out.print(rsmd.getCatalogName(i)+"\t");
				System.out.println(rsmd.getColumnLabel(i));*/
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			//���������
			int i = 1;
			data = new HashMap<String, Object>();
			while(rs.next()){				
				for(int j=0;j<colName.length;j++){
					data.put(colName[j]+i, rs.getObject(j+1));
				}	
				i = i+1;
			}
			data.put("����", i-1);
			//ȡ�����ݣ�����Map��
		}catch (SQLException e) {
			System.out.println("�������ݿ����");
//			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("û���ҵ���װ����");
		}catch (IllegalAccessException e) {
			System.out.println("ת��Ϊ�ӿڹ����г���");
		}catch (InstantiationException e) {
			System.out.println("ת��Ϊ�ӿڹ����г���");
		}finally{
			t.free(conn, ps, rs);
		}
		return data;
	}
	/*public static void main(String[] args) {
		Map<String, Object> data = new SelectData().select("select * from ͼ��");
		System.out.println(data);
	}*/
}
