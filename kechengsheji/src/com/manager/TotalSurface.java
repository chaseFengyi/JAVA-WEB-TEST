package com.manager;

import java.awt.Container;
import java.awt.MenuBar;
import java.awt.MenuItem;
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

public class TotalSurface extends JFrame {
	JFrame jf = new JFrame("管理员登陆界面");
	JLabel choice = new JLabel("<html><font size = 6 color = red>请您选择操作项</font></html>");
	JButton add = new JButton("<html><font size = 5 color = blue>增加或修改操作</font></html>");
	JButton delete = new JButton("<html><font size = 5 color = blue>删除操作</font></html>");
	JButton select = new JButton("<html><font size = 5 color = blue>查询操作</font></html>");
	
//	JMenu secMenu = new JMenu("<html><font size = 5 color = blue>查询操作</font></html>");
//	JMenuBar main = new JMenuBar();
	
	ImageIcon ico = new ImageIcon("E:\\Workspaces\\picture/6.jpg");
	JLabel label = new JLabel(ico);
	
	public TotalSurface(){
		Container cp = jf.getContentPane();
		jf.setResizable(false);
		jf.setSize(470, 600);
		jf.setVisible(true);
		cp.setBounds(0, 0, 470, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//为面板添加图片
		JPanel panel = new JPanel();
		panel.setLayout(null);
		label.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
		panel.add(label);
		
		//设置Button背景色为透明，边框为无色
		add.setContentAreaFilled(false);
		add.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		delete.setBorderPainted(false);
		select.setContentAreaFilled(false);
		select.setBorderPainted(false);
		choice.setBounds(100, 100, 200, 30);
		add.setBounds(100, 170, 200, 30);
		delete.setBounds(100, 250, 200, 30);
		select.setBounds(100, 330, 200, 30);
		
		//将所有组件加入面板
		cp.add(choice);
		cp.add(add);
		cp.add(delete);
		cp.add(select);
		
		cp.add(panel);
		
		//为每个按钮绑定监听器
		//增加或修改操作监听器
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Add_Update_Select();
				jf.setVisible(false);
			}
		});
		
		//删除操作监听器
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DeleteSurface();
				jf.setVisible(false);
			}
		});
		
		//查询操作监听器
		select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SelectSurface();
				jf.setVisible(false);
			}
		});
	
	}
	
	public static void main(String[] args) {
		new TotalSurface();
	}
}
