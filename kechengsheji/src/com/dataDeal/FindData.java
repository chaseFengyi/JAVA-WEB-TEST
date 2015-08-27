package com.dataDeal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FindData {
	String password = null;
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	Tools t = null;
	String className = "com.dataDeal.Tools";
	Class tc = null;
	
	Map<String, Object> data = new HashMap<String, Object>();
	
	//����ͼ����Ϣ
	//ͼ����,bookId;
	public Map findBook(int bookId){
		try{
			//ͨ��������ƣ�ʹ����װ����װ�ظ���
			tc = Class.forName(className);
			//���������ʵ��
			t = (Tools)tc.newInstance();
			
			//ͨ�����洴������ʵ���������䷽��
			//�������ݿ�
			conn = t.getConnection();
			
			//�����������
			String sql = "select * from ͼ�� where ͼ����=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			
			//ִ�����
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			int j = 1;
			while(rs.next()){
				for(int i=0; i<colName.length; i++){
					data.put(colName[i], rs.getObject(1+i));
				}
				j = j+1;
			}
			data.put("����", j-1);
		}catch (SQLException e) {
			System.out.println("�������ݿ����");
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
	
	
	//���Ҷ�����Ϣ
	public Map findReader(String sql){
		try{
			//ͨ��������ƣ�ʹ����װ����װ�ظ���
			tc = Class.forName(className);
			//���������ʵ��
			t = (Tools)tc.newInstance();
			
			//ͨ�����洴������ʵ���������䷽��
			//�������ݿ�
			conn = t.getConnection();
			
			//�����������
//			String sql = "select * from ���� where ����֤���=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, browId);
			
			//ִ�����
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			
			int j = 1;
			while(rs.next()){
				for(int i=0; i<colName.length; i++){
					data.put(colName[i], rs.getObject(1+i));
				}
				j = j+1;
			}
			data.put("����", j-1);
			System.out.println(data);
		}catch (SQLException e) {
//			System.out.println("�������ݿ����");
			e.printStackTrace();
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
	
	
	//���������Ϣ
	//����,storeId
	public Map findStore(int storeId){
		try{
			//ͨ��������ƣ�ʹ����װ����װ�ظ���
			tc = Class.forName(className);
			//���������ʵ��
			t = (Tools)tc.newInstance();
			
			//ͨ�����洴������ʵ���������䷽��
			//�������ݿ�
			conn = t.getConnection();
			
			//�����������
			String sql = "select * from ��� where ����=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, storeId);
			
			//ִ�����
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] colName = new String[count];
			for(int i=1;i<=count;i++){
				colName[i-1] = rsmd.getColumnLabel(i);
			}
			
			int j = 1;
			while(rs.next()){
				for(int i=0; i<colName.length; i++){
					data.put(colName[i], rs.getObject(1+i));
				}
				j = j+1;
			}
			data.put("����", j-1);
			
		}catch (SQLException e) {
			System.out.println("�������ݿ����");
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
		System.out.println(new FindData().findReader("select ����,����,Ӧ��ʱ�� from ����,����" +
				" where ����.����֤���=����.����֤��� and ����.����֤���='S04123126'"));
	}*/
}
