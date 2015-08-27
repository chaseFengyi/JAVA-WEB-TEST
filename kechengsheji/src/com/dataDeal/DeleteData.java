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
	
	//删除图书信息
	//出版社编号,publishId,图书类型编号,bookTypeId,书库号,bookStoreId,
	//图书编号,bookId,书名,bookName,作者,author,价格,price,页码,page
	//现存量,having,库存总量,total,入库时间,time
	public boolean deleteBook(String book, int bookId ){
		boolean flag = false;
		try{
			//通过反射机制，使用类装载器，装载该类
			 Class tc = Class.forName(className);
			
			//创建该类的实例，转化为接口
			 t = (Tools)tc.newInstance();
			
			//通过接口，调用该类的方法			
			//连接数据库
			conn = t.getConnection();
			System.out.println(book);
			//执行删除语句
			sql = "delete from 图书 where 图书编号=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, book);
			ps.setInt(1, bookId);
			
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
	
	//删除读者信息
	//读者,reader, 借书证编号,browId
	public boolean deleteReader(String reader, String browId){
		boolean flag = false;
		try{
			//通过反射机制，使用类装载器，装载该类
			 Class tc = Class.forName(className);
			
			//创建该类的实例，转化为接口
			 t = (Tools)tc.newInstance();
			
			//通过接口，调用该类的方法			
			//连接数据库
			conn = t.getConnection();
			
			//执行删除语句
			sql = "delete from 读者 where 借书证编号=?";
			ps = conn.prepareStatement(sql);
//			ps.setString(1, reader);
			ps.setString(1, browId);
			
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

	//删除书库信息
	//书库号 ,storeId
	public boolean deleteStore(String store, int storeId){
		boolean flag = false;
		try{
		//通过反射机制，使用类装载器，装载该类
		 Class tc = Class.forName(className);
		
		//创建该类的实例，转化为接口
		 t = (Tools)tc.newInstance();
		
		//通过接口，调用该类的方法			
		//连接数据库
		conn = t.getConnection();
		System.out.println(storeId);
		//执行删除语句
		sql = "delete from 书库 where 书库号=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, storeId);
		
		ps.executeUpdate();
			
		flag = true;
	} catch (ClassNotFoundException e1) {
		System.out.println("没有找到该装载类");
	}catch (InstantiationException e) {
		System.out.println("转化为接口过程中出错");
	}catch (IllegalAccessException e) {
		System.out.println("转化为接口过程中出错");
	}catch (SQLException e) {
//		System.out.println("连接数据库出错");
		e.printStackTrace();
	}finally{
		t.free(conn, ps, rs);
	}
	
	return flag;
}
	/*public static void main(String[] args) {
		new DeleteData("李四").delete();
	}*/
}
