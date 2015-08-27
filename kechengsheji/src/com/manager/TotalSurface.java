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
	JFrame jf = new JFrame("����Ա��½����");
	JLabel choice = new JLabel("<html><font size = 6 color = red>����ѡ�������</font></html>");
	JButton add = new JButton("<html><font size = 5 color = blue>���ӻ��޸Ĳ���</font></html>");
	JButton delete = new JButton("<html><font size = 5 color = blue>ɾ������</font></html>");
	JButton select = new JButton("<html><font size = 5 color = blue>��ѯ����</font></html>");
	
//	JMenu secMenu = new JMenu("<html><font size = 5 color = blue>��ѯ����</font></html>");
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
		
		//Ϊ������ͼƬ
		JPanel panel = new JPanel();
		panel.setLayout(null);
		label.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
		panel.add(label);
		
		//����Button����ɫΪ͸�����߿�Ϊ��ɫ
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
		
		//����������������
		cp.add(choice);
		cp.add(add);
		cp.add(delete);
		cp.add(select);
		
		cp.add(panel);
		
		//Ϊÿ����ť�󶨼�����
		//���ӻ��޸Ĳ���������
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Add_Update_Select();
				jf.setVisible(false);
			}
		});
		
		//ɾ������������
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DeleteSurface();
				jf.setVisible(false);
			}
		});
		
		//��ѯ����������
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
