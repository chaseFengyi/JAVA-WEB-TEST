package com.manager;

import java.util.Map;

import com.dataDeal.SelectData;

public class GetTable {
	//获得图书表
	public Object[][] getFileStates(){
		//通过SelectData类查询图书馆数据库中图书信息，
		//查询语句为
		String sql = "select * from 图书";
		SelectData sd = new SelectData();
		//接收查找到的数据
		Map<String, Object> data = sd.select(sql);
	
		//取数据库中图书相关信息放入表格中
		String[] columnNames = {
				"出版社编号","图书类型编号","书库号"
				,"图书编号","书名","作者","价格",
				"页码","现存量","库存总量","入库时间"
		};
		int count = ((Integer)(data.get("总数"))).intValue();
		String[][] results = new String[count][columnNames.length];
		//遍历Map
		int j = 1;
		for(int i=0; i<count;i++){
			results[i][0] = (data.get("出版社编号"+j)).toString();
			results[i][1] = (data.get("图书类型编号"+j)).toString();
			results[i][2] = (data.get("书库号"+j)).toString();
			results[i][3] = (data.get("图书编号"+j)).toString();
			results[i][4] = (data.get("书名"+j)).toString();
			results[i][5] = (data.get("作者"+j)).toString();
			results[i][6] = (data.get("价格"+j)).toString();
			results[i][7] = (data.get("页码"+j)).toString();
			results[i][8] = (data.get("现存量"+j)).toString();
			results[i][9] = (data.get("库存总量"+j)).toString();
			results[i][10] = (data.get("入库时间"+j)).toString();
			j = j+1;
		}
		
		return results;
	}
	
	//获得读者表
	public Object[][] getReaderStates(){
		//通过SelectData类查询图书馆数据库中图书信息，
		//查询语句为
		String sql = "select * from 读者";
		SelectData sd = new SelectData();
		//接收查找到的数据
		Map<String, Object> data = sd.select(sql);
	
		//取数据库中图书相关信息放入表格中
		String[] columnNames = {
				"借书证编号","姓名","性别","出生日期","身份证号",
				"图书借阅次数","是否挂失","可借册数","已借册数",
				"未交罚款金额","密码"
		};
		int count = ((Integer)(data.get("总数"))).intValue();
		String[][] results = new String[count][columnNames.length];
		//遍历Map
		int j = 1;
		for(int i=0; i<count;i++){
			results[i][0] = (data.get("借书证编号"+j)).toString();
			results[i][1] = (data.get("姓名"+j)).toString();
			results[i][2] = (data.get("性别"+j)).toString();
			results[i][3] = (data.get("出生日期"+j)).toString();
			results[i][4] = (data.get("身份证号"+j)).toString();
			results[i][5] = (data.get("图书借阅次数"+j)).toString();
			results[i][6] = (data.get("是否挂失"+j)).toString();
			results[i][7] = (data.get("可借册数"+j)).toString();
			results[i][8] = (data.get("已借册数"+j)).toString();
			results[i][9] = (data.get("未交罚款金额"+j)).toString();
			results[i][10] = (data.get("密码"+j)).toString();
			j = j+1;
		}
		
		return results;
	}
	
	//获得书库表
	public Object[][] getStoreStates(){
		//通过SelectData类查询图书馆数据库中图书信息，
		//查询语句为
		String sql = "select * from 书库";
		SelectData sd = new SelectData();
		//接收查找到的数据
		Map<String, Object> data = sd.select(sql);
	
		//取数据库中图书相关信息放入表格中
		String[] columnNames = {
				"书库号","书库名"
		};
		int count = ((Integer)(data.get("总数"))).intValue();
		String[][] results = new String[count][columnNames.length];
		//遍历Map
		int j = 1;
		for(int i=0; i<count;i++){
			results[i][0] = (data.get("书库号"+j)).toString();
			results[i][1] = (data.get("书库名"+j)).toString();
			j = j+1;
		}
		
		return results;
	}
}
