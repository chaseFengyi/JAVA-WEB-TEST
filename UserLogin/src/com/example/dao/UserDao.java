package com.example.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.example.entity.User;

public class UserDao {
	private QueryRunner runner = new TxQueryRunner();
	/*
	 * 根据用户名和密码查找用户
	 */
	public User findUserByNameAndPass(String userName,String userPass) throws Exception{
		String sql = "select * from userlogin where userName=? and userPass=?";
		String[] params = {userName,userPass};
		User user = (User)runner.query(sql, params, new BeanHandler(User.class)); 
		return user;
	}
}
