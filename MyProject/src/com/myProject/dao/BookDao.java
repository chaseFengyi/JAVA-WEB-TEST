package com.myProject.dao;

import java.util.Map;

import com.myProject.db.MyDb;
import com.myProject.domain.Book;

public class BookDao {
	public Map getAll(){
		
		return MyDb.getAll();
	}
	
	public Book find(String id){
		
		return (Book)MyDb.getAll().get(id);
	}
}
