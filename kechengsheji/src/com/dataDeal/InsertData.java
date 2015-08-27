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
	//插入图书信息
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
			//通过反射机制，使用类装载器，装载该
			 Class tc = Class.forName(className);			
			//创建该类的实例，转化为接口
			 t = (Tools)tc.newInstance();			
			//通过接口，调用该类的方法			
			//连接数据库
			conn = t.getConnection();
			//创建语句
			sql = "insert into 图书(出版社编号,图书类型编号," +
					"书库号,图书编号,书名,作者,价格,页码,现存量," +
					"库存总量,入库时间) values(?,?,?,?,?,?,?,?,?,?,?)";
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
			System.out.println("没有找到该装载类");
		}catch (InstantiationException e) {
			System.out.println("转化为接口过程中出错");
		}catch (IllegalAccessException e) {
			System.out.println("转化为接口过程中出错");
		}catch (SQLException e) {
//			System.out.println("连接数据库出错");
			e.printStackTrace();
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}
	
	//插入图书入库信息
	public boolean insertBookStore(int storeId, int bookId, Date time){		
		boolean flag = false;
		String sql;
		
		try {			
			//通过反射机制，使用类装载器，装载该类
			 Class tc = Class.forName(className);			
			//创建该类的实例，转化为接口
			 t = (Tools)tc.newInstance();			
			//通过接口，调用该类的方法			
			//连接数据库
			conn = t.getConnection();			
			//创建语句
			sql = "insert into 图书入库(书库号,图书编号,日期) " +
					"values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, storeId);
			ps.setInt(2, bookId);
			ps.setDate(3, new java.sql.Date(time.getTime()));
			ps.executeUpdate();		
			
			flag = true;
			
		} catch (ClassNotFoundException e1) {
			System.out.println("没有找到该装载类");
		}catch (InstantiationException e) {
			System.out.println("转化为接口过程中出错");
		}catch (IllegalAccessException e) {
			System.out.println("转化为接口过程中出错");
		}catch (SQLException e) {
			System.out.println("连接数据库出错");
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}
	
	//插入书库信息
	public boolean insertStore(int storeId, String name){		
		boolean flag = false;
		String sql;
		
		try {			
			//通过反射机制，使用类装载器，装载该类
			 Class tc = Class.forName(className);			
			//创建该类的实例，转化为接口
			 t = (Tools)tc.newInstance();			
			//通过接口，调用该类的方法			
			//连接数据库
			conn = t.getConnection();			
			//创建语句
			sql = "insert into 书库(书库号,书库名) " +
					"values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, storeId);
			ps.setString(2, name);
			ps.executeUpdate();		
			
			flag = true;
			
		} catch (ClassNotFoundException e1) {
			System.out.println("没有找到该装载类");
		}catch (InstantiationException e) {
			System.out.println("转化为接口过程中出错");
		}catch (IllegalAccessException e) {
			System.out.println("转化为接口过程中出错");
		}catch (SQLException e) {
			System.out.println("连接数据库出错");
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}
	
	
	//插入读者信息
	public boolean insertReader(String browId, String name, String
			sex,Date birthday, String idCard, int browTimes,String lost,
			int canBrow,int alreadyBrow,double money,String password){		
		boolean flag = false;
		String sql;
		try {			
			//通过反射机制，使用类装载器，装载该类
			 Class tc = Class.forName(className);			
			//创建该类的实例，转化为接口
			 t = (Tools)tc.newInstance();			
			//通过接口，调用该类的方法			
			//连接数据库
			conn = t.getConnection();
			//执行插入语句		
			sql = "insert into 读者(借书证编号,姓名,性别,出生日期,身份证号," +
					"图书借阅次数,是否挂失,可借册数,已借册数,未交罚款金额,密码) " +
					"values(?,?,?,?,?,?,?,?,?,?,?)";
			//创建语句
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
			System.out.println("没有找到该装载类");
		}catch (InstantiationException e) {
			System.out.println("转化为接口过程中出错");
		}catch (IllegalAccessException e) {
			System.out.println("转化为接口过程中出错");
		}catch (SQLException e) {
			System.out.println("连接数据库出错");
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}
	
	/*public static void main(String[] args) {
		new InsertData().insert();
	}*/
}
