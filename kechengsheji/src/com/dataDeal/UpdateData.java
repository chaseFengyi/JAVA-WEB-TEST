package com.dataDeal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpdateData {	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String className = "com.dataDeal.Tools";
	Class tc = null;
	Tools t = null;
	//sql���JPanel bookPanel = new JPanel();//�鼮�޸����
	/*JLabel  publishId = new JLabel("��������");//��������
	JLabel booksTypeId = new JLabel("ͼ�����ͱ��");//ͼ�����ͱ��
	JLabel storeRoomId = new JLabel("����");//����
	JLabel booksId = new JLabel("ͼ����");//ͼ����
	JLabel booksname = new JLabel("����"); //����
	JLabel authors = new JLabel("����");//����
	JLabel prices = new JLabel("�۸�");//�۸�
	JLabel pages = new JLabel("ҳ��");//ҳ��
	JLabel haveing = new JLabel("�ִ���");//�ִ���
	JLabel totals = new JLabel("�������");//�������
	JLabel times = new JLabel("���ʱ��");//���ʱ��
*/	
	//�޸�ͼ���
	public boolean update(int publishId,String booksTypeId, int storeRoomId, int booksId, 
			String booksname, String authors, double prices, int pages,int haveing,
			int totals, Date times){
		boolean flag = false;//�жϲ����Ƿ�ɹ�ִ��
		String sql = null;
		try{
			//ͨ��������ƣ�װ��Tools��
			tc = Class.forName(className);
			//����Tools���ʵ������
			t = (Tools)tc.newInstance();
			//ͨ�������Ķ��󣬵��������getConnection����
			//�������� 
			conn = t.getConnection();
			sql = "update ͼ�� set ��������=?,ͼ�����ͱ��=?,����=?," +
					"����=?,����=?,�۸�=?,ҳ��=?,�ִ���=?,�������=?,���ʱ��=?" +
					" where ͼ����=?";
			//�������
			ps = conn.prepareStatement(sql);
			ps.setInt(1, publishId);
			ps.setString(2,booksTypeId );
			ps.setInt(3, storeRoomId);
			ps.setString(4, booksname);
			ps.setString(5, authors);
			ps.setDouble(6, prices);
			ps.setInt(7, pages);
			ps.setInt(8, haveing);
			ps.setInt(9, totals);
			ps.setDate(10, new java.sql.Date(times.getTime()));
			ps.setInt(11, booksId);
			//ִ�����
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
	//����֤���,browId;����name;�Ա�,sex;��������,birthday;���֤��,idCard;
	//ͼ����Ĵ���,borwTimes;�Ƿ��ʧ,lost;�ɽ����,allowNum;�ѽ����haveNum
	//δ��������,money;����,password;����ʱ��,browTime;
	//Ӧ��ʱ��,giveTime
	//�޸�ѧ����
	public boolean update(String browId, String name, String sex, 
			Date birthday, String idCard, int borwTimes, 
			String lost, int allowNum,int haveNum, double money,
			String password){
		boolean flag = false;//�жϲ����Ƿ�ɹ�ִ��
		
		String sql = null;
		try{
			//ͨ��������ƣ�װ��Tools��
			tc = Class.forName(className);
			//����Tools���ʵ������
			t = (Tools)tc.newInstance();
			//ͨ�������Ķ��󣬵��������getConnection����
			//�������� 
			conn = t.getConnection();
			sql = "update ���� set ����=?,�Ա�=?,��������=?,���֤��=?,ͼ����Ĵ���=?," +
					"�Ƿ��ʧ=?,�ɽ����=?,�ѽ����=?,δ��������=?,����=? " +
					"where ����֤���=?";
			//�������
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2,sex );
			ps.setDate(3, new java.sql.Date(birthday.getTime()));
			ps.setString(4, idCard);
			ps.setInt(5, borwTimes);
			ps.setString(6, lost);
			ps.setDouble(7, allowNum);
			ps.setInt(8, haveNum);
			ps.setDouble(9, money);
			ps.setString(10, password);
			ps.setString(11, browId);
			//ִ�����
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
	
	//�޸������Ϣ
	public boolean update(int storeId, String name){
		boolean flag = false;//�жϲ����Ƿ�ɹ�ִ��
		
		String sql = null;
		try{
			//ͨ��������ƣ�װ��Tools��
			tc = Class.forName(className);
			//����Tools���ʵ������
			t = (Tools)tc.newInstance();
			//ͨ�������Ķ��󣬵��������getConnection����
			//�������� 
			conn = t.getConnection();
			sql = "update ��� set �����=? where ����=?";
			//�������
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, storeId);
			//ִ�����
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
//			e.printStackTrace();
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}
	
	//�޸Ľ����
	public boolean update(String browId, String name, Date brow, Date back){
		boolean flag = false;//�жϲ����Ƿ�ɹ�ִ��
		String sql;
		try{
			//ͨ��������ƣ�װ��Tools��
			tc = Class.forName(className);
			//����Tools���ʵ������
			t = (Tools)tc.newInstance();
			//ͨ�������Ķ��󣬵��������getConnection����
			//�������� 
			conn = t.getConnection();
			//�������
			sql =  "update ���� set ����=?,����ʱ��=?,Ӧ��ʱ��=? " +
				"where ����֤���=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setDate(2, new java.sql.Date(brow.getTime()));
			ps.setDate(3, new java.sql.Date(back.getTime()));
			ps.setString(4, browId);
			//ִ�����
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
	
	/*public static void main(String[] args) {
		new UpdateData().update(4, "��������");
	}*/
}
