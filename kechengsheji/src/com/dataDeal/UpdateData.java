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
	//sql语句JPanel bookPanel = new JPanel();//书籍修改面板
	/*JLabel  publishId = new JLabel("出版社编号");//出版社编号
	JLabel booksTypeId = new JLabel("图书类型编号");//图书类型编号
	JLabel storeRoomId = new JLabel("书库号");//书库号
	JLabel booksId = new JLabel("图书编号");//图书编号
	JLabel booksname = new JLabel("书名"); //书名
	JLabel authors = new JLabel("作者");//作者
	JLabel prices = new JLabel("价格");//价格
	JLabel pages = new JLabel("页码");//页码
	JLabel haveing = new JLabel("现存量");//现存量
	JLabel totals = new JLabel("库存总量");//库存总量
	JLabel times = new JLabel("入库时间");//入库时间
*/	
	//修改图书表
	public boolean update(int publishId,String booksTypeId, int storeRoomId, int booksId, 
			String booksname, String authors, double prices, int pages,int haveing,
			int totals, Date times){
		boolean flag = false;//判断操作是否成功执行
		String sql = null;
		try{
			//通过反射机制，装载Tools类
			tc = Class.forName(className);
			//建立Tools类的实例对象
			t = (Tools)tc.newInstance();
			//通过建立的对象，调用里面的getConnection方法
			//建立连接 
			conn = t.getConnection();
			sql = "update 图书 set 出版社编号=?,图书类型编号=?,书库号=?," +
					"书名=?,作者=?,价格=?,页码=?,现存量=?,库存总量=?,入库时间=?" +
					" where 图书编号=?";
			//创建语句
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
			//执行语句
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
	//借书证编号,browId;姓名name;性别,sex;出生日期,birthday;身份证号,idCard;
	//图书借阅次数,borwTimes;是否挂失,lost;可借册数,allowNum;已借册数haveNum
	//未交罚款金额,money;密码,password;借书时间,browTime;
	//应还时间,giveTime
	//修改学生表
	public boolean update(String browId, String name, String sex, 
			Date birthday, String idCard, int borwTimes, 
			String lost, int allowNum,int haveNum, double money,
			String password){
		boolean flag = false;//判断操作是否成功执行
		
		String sql = null;
		try{
			//通过反射机制，装载Tools类
			tc = Class.forName(className);
			//建立Tools类的实例对象
			t = (Tools)tc.newInstance();
			//通过建立的对象，调用里面的getConnection方法
			//建立连接 
			conn = t.getConnection();
			sql = "update 读者 set 姓名=?,性别=?,出生日期=?,身份证号=?,图书借阅次数=?," +
					"是否挂失=?,可借册数=?,已借册数=?,未交罚款金额=?,密码=? " +
					"where 借书证编号=?";
			//创建语句
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
			//执行语句
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
	
	//修改书库信息
	public boolean update(int storeId, String name){
		boolean flag = false;//判断操作是否成功执行
		
		String sql = null;
		try{
			//通过反射机制，装载Tools类
			tc = Class.forName(className);
			//建立Tools类的实例对象
			t = (Tools)tc.newInstance();
			//通过建立的对象，调用里面的getConnection方法
			//建立连接 
			conn = t.getConnection();
			sql = "update 书库 set 书库名=? where 书库号=?";
			//创建语句
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, storeId);
			//执行语句
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
//			e.printStackTrace();
		}finally{
			t.free(conn, ps, rs);
		}
		
		return flag;
	}
	
	//修改借书表
	public boolean update(String browId, String name, Date brow, Date back){
		boolean flag = false;//判断操作是否成功执行
		String sql;
		try{
			//通过反射机制，装载Tools类
			tc = Class.forName(className);
			//建立Tools类的实例对象
			t = (Tools)tc.newInstance();
			//通过建立的对象，调用里面的getConnection方法
			//建立连接 
			conn = t.getConnection();
			//创建语句
			sql =  "update 借书 set 书名=?,借书时间=?,应还时间=? " +
				"where 借书证编号=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setDate(2, new java.sql.Date(brow.getTime()));
			ps.setDate(3, new java.sql.Date(back.getTime()));
			ps.setString(4, browId);
			//执行语句
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
	
	/*public static void main(String[] args) {
		new UpdateData().update(4, "计算机书库");
	}*/
}
