package com.readerinfor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class ReaderInformation extends JFrame {
	JLabel jnumber = new JLabel("序号");
	JLabel jbookName = new JLabel("书名");
	JLabel jbookBarCode = new JLabel("图书条码");
	JLabel jCurrentState = new JLabel("流通状态");
	JLabel jReturnTime = new JLabel("应还日期");
	JLabel jReNew = new JLabel("续借");
	JList list;
	
	public void reader(){
		JFrame jf = new JFrame("读者借书情况");
		Container cp = jf.getContentPane();
		jf.setSize(650,400);
		jf.setVisible(true);
		cp.setBounds(0, 0, 650, 400);
		cp.setLayout(null);
		
		jnumber.setBounds(10, 50, 50, 30);
		jnumber.setOpaque(true);
		jnumber.setFont(new Font("宋体", Font.BOLD, 15));
		jnumber.setBackground(Color.pink);
		cp.add(jnumber);
		jbookName.setBounds(65, 50, 100, 30);
		jbookName.setOpaque(true);
		jbookName.setFont(new Font("宋体", Font.BOLD, 15));
		jbookName.setBackground(Color.pink);
		cp.add(jbookName);
		jbookBarCode.setBounds(170, 50, 100, 30);
		jbookBarCode.setOpaque(true);
		jbookBarCode.setFont(new Font("宋体", Font.BOLD, 15));
		jbookBarCode.setBackground(Color.pink);
		cp.add(jbookBarCode);
		jCurrentState.setBounds(275, 50, 100, 30);
		jCurrentState.setOpaque(true);
		jCurrentState.setFont(new Font("宋体", Font.BOLD, 15));
		jCurrentState.setBackground(Color.pink);
		cp.add(jCurrentState);
		jReturnTime.setBounds(380, 50, 100, 30);
		jReturnTime.setOpaque(true);
		jReturnTime.setFont(new Font("宋体", Font.BOLD, 15));
		jReturnTime.setBackground(Color.pink);
		cp.add(jReturnTime);
		jReNew.setBounds(485, 50, 100, 30);
		jReNew.setOpaque(true);
		jReNew.setFont(new Font("宋体", Font.BOLD, 15));
		jReNew.setBackground(Color.pink);
		cp.add(jReNew);
		
		String[] filename = new String[]{"qq","aa","ss","dd","ff",
				"gg","cc","ll","vv","ww"};
		list = new JList(filename);
		//设置多行显示
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		//设置行数
		list.setVisibleRowCount(filename.length/4);
		list.setBounds(10, 100, 580, 200);
		cp.add(list);
		
	}
	
	public static void main(String[] args) {
		new ReaderInformation().reader();
	}
}
