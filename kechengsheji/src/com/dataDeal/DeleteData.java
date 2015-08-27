package com.dataDeal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String className = "com.dataDeal.Tools";
	Tools t = null;
	String sql = null;
	
	//ɾ��ͼ����Ϣ
	//��������,publishId,ͼ�����ͱ��,bookTypeId,����,bookStoreId,
	//ͼ����,bookId,����,bookName,����,author,�۸�,price,ҳ��,page
	//�ִ���,having,�������,total,���ʱ��,time
	public boolean deleteBook(String book, int bookId ){
		boolean flag = false;
		try{
			//ͨ��������ƣ�ʹ����װ������װ�ظ���
			 Class tc = Class.forName(className);
			
			//���������ʵ����ת��Ϊ�ӿ�
			 t = (Tools)tc.newInstance();
			
			//ͨ���ӿڣ����ø���ķ���			
			//�������ݿ�
			conn = t.getConnection();
			System.out.println(book);
			//ִ��ɾ�����
			sql = "delete from ͼ�� where ͼ����=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, book);
			ps.setInt(1, bookId);
			
			ps.executeUpdate();
			
			flag = true;
				
		} catch (ClassNotFoundException e1) {
			System.out.println("û���ҵ���װ����");
		}catch (InstantiationException e) {
			System.out.println("ת��Ϊ�ӿڹ����г���");
		}catch (IllegalAccessException e) {
			System.out.println("ת��Ϊ�ӿڹ����г���");
		}catch (SQLException e) {
//			System.out.println("�������ݿ����");
			e.printStackTrace();
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}
	
	//ɾ��������Ϣ
	//����,reader, ����֤���,browId
	public boolean deleteReader(String reader, String browId){
		boolean flag = false;
		try{
			//ͨ��������ƣ�ʹ����װ������װ�ظ���
			 Class tc = Class.forName(className);
			
			//���������ʵ����ת��Ϊ�ӿ�
			 t = (Tools)tc.newInstance();
			
			//ͨ���ӿڣ����ø���ķ���			
			//�������ݿ�
			conn = t.getConnection();
			
			//ִ��ɾ�����
			sql = "delete from ���� where ����֤���=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, reader);
			ps.setString(1, browId);
			
			ps.executeUpdate();
			
			flag = true;
		} catch (ClassNotFoundException e1) {
			System.out.println("û���ҵ���װ����");
		}catch (InstantiationException e) {
			System.out.println("ת��Ϊ�ӿڹ����г���");
		}catch (IllegalAccessException e) {
			System.out.println("ת��Ϊ�ӿڹ����г���");
		}catch (SQLException e) {
			System.out.println("�������ݿ����");
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}

	//ɾ�������Ϣ
	//���� ,storeId
	public boolean deleteStore(String store, int storeId){
		boolean flag = false;
		try{
		//ͨ��������ƣ�ʹ����װ������װ�ظ���
		 Class tc = Class.forName(className);
		
		//���������ʵ����ת��Ϊ�ӿ�
		 t = (Tools)tc.newInstance();
		
		//ͨ���ӿڣ����ø���ķ���			
		//�������ݿ�
		conn = t.getConnection();
		System.out.println(storeId);
		//ִ��ɾ�����
		sql = "delete from ��� where ����=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, storeId);
		
		ps.executeUpdate();
			
		flag = true;
	} catch (ClassNotFoundException e1) {
		System.out.println("û���ҵ���װ����");
	}catch (InstantiationException e) {
		System.out.println("ת��Ϊ�ӿڹ����г���");
	}catch (IllegalAccessException e) {
		System.out.println("ת��Ϊ�ӿڹ����г���");
	}catch (SQLException e) {
//		System.out.println("�������ݿ����");
		e.printStackTrace();
	}finally{
		t.free(conn, ps, rs);
	}
	
	return flag;
}
	/*public static void main(String[] args) {
		new DeleteData("����").delete();
	}*/
}
