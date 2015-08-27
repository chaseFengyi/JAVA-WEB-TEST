package com.readerinfor;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class ReaderInformation extends JFrame {
	JLabel jnumber = new JLabel("���");
	JLabel jbookName = new JLabel("����");
	JLabel jbookBarCode = new JLabel("ͼ������");
	JLabel jCurrentState = new JLabel("��ͨ״̬");
	JLabel jReturnTime = new JLabel("Ӧ������");
	JLabel jReNew = new JLabel("����");
	JList list;
	
	public void reader(){
		JFrame jf = new JFrame("���߽������");
		Container cp = jf.getContentPane();
		jf.setSize(650,400);
		jf.setVisible(true);
		cp.setBounds(0, 0, 650, 400);
		cp.setLayout(null);
		
		jnumber.setBounds(10, 50, 50, 30);
		jnumber.setOpaque(true);
		jnumber.setFont(new Font("����", Font.BOLD, 15));
		jnumber.setBackground(Color.pink);
		cp.add(jnumber);
		jbookName.setBounds(65, 50, 100, 30);
		jbookName.setOpaque(true);
		jbookName.setFont(new Font("����", Font.BOLD, 15));
		jbookName.setBackground(Color.pink);
		cp.add(jbookName);
		jbookBarCode.setBounds(170, 50, 100, 30);
		jbookBarCode.setOpaque(true);
		jbookBarCode.setFont(new Font("����", Font.BOLD, 15));
		jbookBarCode.setBackground(Color.pink);
		cp.add(jbookBarCode);
		jCurrentState.setBounds(275, 50, 100, 30);
		jCurrentState.setOpaque(true);
		jCurrentState.setFont(new Font("����", Font.BOLD, 15));
		jCurrentState.setBackground(Color.pink);
		cp.add(jCurrentState);
		jReturnTime.setBounds(380, 50, 100, 30);
		jReturnTime.setOpaque(true);
		jReturnTime.setFont(new Font("����", Font.BOLD, 15));
		jReturnTime.setBackground(Color.pink);
		cp.add(jReturnTime);
		jReNew.setBounds(485, 50, 100, 30);
		jReNew.setOpaque(true);
		jReNew.setFont(new Font("����", Font.BOLD, 15));
		jReNew.setBackground(Color.pink);
		cp.add(jReNew);
		
		String[] filename = new String[]{"qq","aa","ss","dd","ff",
				"gg","cc","ll","vv","ww"};
		list = new JList(filename);
		//���ö�����ʾ
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		//��������
		list.setVisibleRowCount(filename.length/4);
		list.setBounds(10, 100, 580, 200);
		cp.add(list);
		
	}
	
	public static void main(String[] args) {
		new ReaderInformation().reader();
	}
}
