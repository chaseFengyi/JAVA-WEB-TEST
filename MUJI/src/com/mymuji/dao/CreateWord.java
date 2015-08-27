package com.mymuji.dao;

public class CreateWord {
	public static String MUJIDB = "MUJI";
	public static String MUJITable = "mujiTable";
	
	public static String createMujiDB = 
			"create database " + MUJIDB ;
	
	public static String dropMujiDB = 
			"drop database if exists "+MUJIDB;
	
	public static String createMujiTable = 
			"create table "+MUJITable + "(" +
					"mujiID int(6) primary key auto_increment," +
					"accountNum varchar(12) not null," +
					"password varchar(12) not null);";
	
	public static String dropMujiTable = 
			"drop table if exists "+MUJITable;
	
}
