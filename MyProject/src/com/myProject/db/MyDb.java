package com.myProject.db;

import java.util.LinkedHashMap;
import java.util.Map;

import com.myProject.domain.Book;

public class MyDb {
	private static Map<String, Book> map = new LinkedHashMap<String, Book>();
	
	static {
		map.put("1", new Book("1","javaWeb����","����",20,"һ���������"));
		map.put("2", new Book("2","jdbc����","����",30,"һ��jdbc����"));
		map.put("3", new Book("3","spring����","����",50,"һ����"));
		map.put("4", new Book("4","hibernate����","��١",56,"һ���������"));
		map.put("5", new Book("5","struts����","�ϱ�",40,"һ���������"));
		map.put("6", new Book("6","ajax����","����",50,"һ���������"));
	}
	
	public static Map getAll(){
		return map;
	}
}
