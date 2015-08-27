package com.manager;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.jiemian.JieMian;

public class Add_Update_Select extends JFrame {
	JMenuBar main;
	JMenu choice1, choice2;
	JMenuItem book1,book2;
	JMenuItem reader1,reader2;
	JMenuItem store1,store2;
	JButton back;
	public Add_Update_Select() {
		setTitle("�����Լ��޸�ҳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		
		main = new JMenuBar();
		setJMenuBar(main);
		choice1 = new JMenu("�޸������");
		book1 = new JMenuItem("ͼ����Ϣ");
		reader1 = new JMenuItem("������Ϣ");
		store1 = new JMenuItem("�����Ϣ");
		back = new JButton("<html><font color = blue size = 8>����</font></html>");
		back.setBounds(400, 30, 200, 100);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new TotalSurface();
			}
		});
		
		choice1.add(book1);
		choice1.add(reader1);
		choice1.add(store1);
		
		choice2 = new JMenu("��ѯ����");
		book2 = new JMenuItem("ͼ����Ϣ");
		reader2 = new JMenuItem("������Ϣ");
		store2 = new JMenuItem("�����Ϣ");
		choice2.add(book2);
		choice2.add(reader2);
		choice2.add(store2);
		
		main.add(choice1);
		main.add(choice2);
		
		add(back);
		
		ImageIcon ico = new ImageIcon("E:\\Workspaces\\picture/4.jpg");
		JLabel label = new JLabel(ico);
		label.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
		JPanel panel = new JPanel();
		panel.add(label);
		add(panel);
		setVisible(true);
		
		book1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new UpdateSurface();
			}
		});
		
		reader1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new UpdateReaderSurface();
			}
		});
		
		store1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new UpdateStoreSurface();
			}
		});
		
		book2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new SelectSurface();
			}
		});
		
		reader2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new SelectReaderSurface();
			}
		});
		
		store2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new SelectStoreSurface();
			}
		});
	}
	
	public static void main(String[] args) {
		new Add_Update_Select();
	}
}
