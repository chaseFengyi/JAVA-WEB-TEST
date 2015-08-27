package com.jiemian;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dataDeal.FindData;
import com.manager.TotalSurface;

public class JieMian extends JFrame {
	JFrame jf = new JFrame("界面观看");
	
	WhenRegister wrg = new WhenRegister();
	
	int count = 0;
	
	JLabel choiceLabel = new JLabel("<html><font color = red>请选择登录方式</font></html>");
	JRadioButton student = new JRadioButton("学生",true);
	JRadioButton manager = new JRadioButton("管理员");
	ButtonGroup choice = new ButtonGroup();
	
	JLabel user = new JLabel("图 书 证：");
	JLabel password = new JLabel("密      码：");
	JButton load = new JButton("登陆");
	JButton exit = new JButton("取消");
	JButton regist = new JButton("注册");	
	JTextField userNumber = new JTextField();
	JPasswordField passNumber = new JPasswordField();
	
	ImageIcon ico = new ImageIcon("E:\\Workspaces\\picture\\5.jpg");
	JLabel limage = new JLabel(ico);
	
	public JieMian(){
		Container cp = jf.getContentPane();
		jf.setSize(400, 320);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setBounds(60, 30, 300, 200);	
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		limage.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
		panel1.add(limage);
		
		choice.add(student);
		choice.add(manager);
		choiceLabel.setBounds(90, 40, 100, 20);
		cp.add(choiceLabel);
		student.setBounds(90, 60, 58, 30);
		student.setContentAreaFilled(false);
		student.setBorderPainted(false);
		cp.add(student);
		manager.setBounds(160,60, 65, 30);
		manager.setContentAreaFilled(false);
		manager.setBorderPainted(false);
		cp.add(manager);
		user.setBounds(90, 100, 60, 20);
		cp.add(user);
		userNumber.setBounds(160, 100, 120, 20);
		cp.add(userNumber);
		password.setBounds(90, 140, 60, 20);
		cp.add(password);
		passNumber.setBounds(160, 140, 120, 20);
		cp.add(passNumber);
		regist.setBounds(90, 180, 60, 20);
		cp.add(regist);
		load.setBounds(160, 180, 60, 20);
		cp.add(load);
		exit.setBounds(230, 180, 60, 20);
		cp.add(exit);
		
		regist.addActionListener(new RegisterListener());
		load.addActionListener(new LoadListener());
		exit.addActionListener(new ExitListener());
		
		cp.add(panel1);
		

		student.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		manager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	class RegisterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			wrg.register();
			jf.setVisible(false);
		}
		
	}
	
	class LoadListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {	
			//判断登陆的人是学生登录，还是管理员登陆
			String judge;
			if(student.isSelected())
				judge = student.getText();
			else 
				judge = manager.getText();

			String name;
			String oldName;
			String oldPassword;
			//学生登录方式
			if(judge.equals("学生")){
				//获取用户输入的姓名
				name = userNumber.getText();
				
				Map<String, Object> data = new HashMap<String, Object>();
				String sql = "select * from 读者 where 借书证编号='"+name+"'";
				
				//通过FindData类获取该用户的密码
				FindData fd = new FindData();//创建FindData的实例
				data =  fd.findReader(sql);//获取密码
				oldPassword = data.get("密码").toString();
				count++;
				
				String passString = new String(passNumber.getPassword());
					if(passString.equals(oldPassword)){
//						rif.reader();
						com.readerinfor.ReaderInfo rif = new 
							com.readerinfor.ReaderInfo(name);
						jf.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(getContentPane(), "您没有注册" +
								"或者密码错误,请重新输入");
						passNumber.setText("");
						if(count > 3){
							JOptionPane.showMessageDialog(getContentPane(), "三次错误输入，程序退出");
							System.exit(0);
						}
					}
				}else{//管理员登陆 
					oldName = "fengyi";
					oldPassword = "171389";
					String passString = new String(passNumber.getPassword());
					String nameString = userNumber.getText();
					
					if(oldName.equals(nameString)){
						if(passString.equals(oldPassword)){
							new TotalSurface();
							jf.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(getContentPane(), "密码错误,请重新输入");
							passNumber.setText("");
							if(count > 3){
								JOptionPane.showMessageDialog(getContentPane(), "三次错误输入，程序退出");
								System.exit(0);
							}
						}
					}else{
						JOptionPane.showMessageDialog(getContentPane(), "姓名错误,请重新输入");
						passNumber.setText("");
						if(count > 3){
							JOptionPane.showMessageDialog(getContentPane(), "三次错误输入，程序退出");
							System.exit(0);
						}
					}
				}
			}
		}
		
	
	class ExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}
	
	public static void main(String[] args) {
		JieMian jm = new JieMian();
	}
}
