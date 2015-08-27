package com.mymuji.dao;

public class Test {
	public static void main(String[] args) {
			if(CreateTable.createTable()){
				System.out.println("success");
			}else
				System.out.println("failure");
		System.exit(-1);
	}
}
