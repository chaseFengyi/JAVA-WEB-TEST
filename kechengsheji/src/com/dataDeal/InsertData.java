package com.dataDeal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;

import com.jiemian.WhenRegister;

public class InsertData extends JFrame {
	Connection conn = null;
	java.sql.PreparedStatement ps = null;
	ResultSet rs = null;	
	String className = "com.dataDeal.Tools";
	Tools t = null;	
	String sql;
	//����ͼ����Ϣ
	/*int pubId,storeRoonId,bookId,page,have,total;
	String bookTypeId,bookname,author;
	Double price;
	Date time;*/
	public boolean insertBook(int pubId,String bookTypeId,
			int storeRoomId, int bookId,String bookName,
			String author, double price, int page,
			int having, int total, Date time){		
		boolean flag = false;
		
		try {			
			//ͨ��������ƣ�ʹ����װ������װ�ظ�
			 Class tc = Class.forName(className);			
			//���������ʵ����ת��Ϊ�ӿ�
			 t = (Tools)tc.newInstance();			
			//ͨ���ӿڣ����ø���ķ���			
			//�������ݿ�
			conn = t.getConnection();
			//�������
			sql = "insert into ͼ��(��������,ͼ�����ͱ��," +
					"����,ͼ����,����,����,�۸�,ҳ��,�ִ���," +
					"�������,���ʱ��) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);	
			ps.setInt(1, pubId);
			ps.setString(2, bookTypeId);
			ps.setInt(3, storeRoomId);
			ps.setInt(4, bookId);
			ps.setString(5, bookName);
			ps.setString(6, author);
			ps.setDouble(7, price);
			ps.setInt(8, page);
			ps.setInt(9, having);
			ps.setInt(10, total);
			ps.setDate(11, new java.sql.Date(time.getTime()));
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
	
	//����ͼ�������Ϣ
	public boolean insertBookStore(int storeId, int bookId, Date time){		
		boolean flag = false;
		String sql;
		
		try {			
			//ͨ��������ƣ�ʹ����װ������װ�ظ���
			 Class tc = Class.forName(className);			
			//���������ʵ����ת��Ϊ�ӿ�
			 t = (Tools)tc.newInstance();			
			//ͨ���ӿڣ����ø���ķ���			
			//�������ݿ�
			conn = t.getConnection();			
			//�������
			sql = "insert into ͼ�����(����,ͼ����,����) " +
					"values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, storeId);
			ps.setInt(2, bookId);
			ps.setDate(3, new java.sql.Date(time.getTime()));
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
	
	//���������Ϣ
	public boolean insertStore(int storeId, String name){		
		boolean flag = false;
		String sql;
		
		try {			
			//ͨ��������ƣ�ʹ����װ������װ�ظ���
			 Class tc = Class.forName(className);			
			//���������ʵ����ת��Ϊ�ӿ�
			 t = (Tools)tc.newInstance();			
			//ͨ���ӿڣ����ø���ķ���			
			//�������ݿ�
			conn = t.getConnection();			
			//�������
			sql = "insert into ���(����,�����) " +
					"values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, storeId);
			ps.setString(2, name);
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
	
	
	//���������Ϣ
	public boolean insertReader(String browId, String name, String
			sex,Date birthday, String idCard, int browTimes,String lost,
			int canBrow,int alreadyBrow,double money,String password){		
		boolean flag = false;
		String sql;
		try {			
			//ͨ��������ƣ�ʹ����װ������װ�ظ���
			 Class tc = Class.forName(className);			
			//���������ʵ����ת��Ϊ�ӿ�
			 t = (Tools)tc.newInstance();			
			//ͨ���ӿڣ����ø���ķ���			
			//�������ݿ�
			conn = t.getConnection();
			//ִ�в������		
			sql = "insert into ����(����֤���,����,�Ա�,��������,���֤��," +
					"ͼ����Ĵ���,�Ƿ��ʧ,�ɽ����,�ѽ����,δ��������,����) " +
					"values(?,?,?,?,?,?,?,?,?,?,?)";
			//�������
			ps = conn.prepareStatement(sql);	
			ps.setString(1, browId);
			ps.setString(2, name);
			ps.setString(3, sex);
			ps.setDate(4, new java.sql.Date(birthday.getTime()));
			ps.setString(5, idCard);
			ps.setInt(6, browTimes);
			ps.setString(7, lost);
			ps.setInt(8, canBrow);
			ps.setInt(9, alreadyBrow);
			ps.setDouble(10, money);
			ps.setString(11, password);
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
	
	/*public static void main(String[] args) {
		new InsertData().insert();
	}*/
}
