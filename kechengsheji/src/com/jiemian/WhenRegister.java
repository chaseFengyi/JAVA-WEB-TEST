package com.jiemian;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class WhenRegister extends JFrame {	
	 public static String name = "";//姓名
	 public static String password = "";//密码/
	 public static String schoolNumber = "";//学号
	 public static String sexStr = "";//性别
	 public static Date birthdayStr;//出生日期
	 public static String IDcardStr;//身份证号
	
	ImageIcon ico = new ImageIcon("E:\\Workspaces\\picture\\7.jpg");
	JLabel label = new JLabel(ico);
	
	final RegisterOk rs = new RegisterOk();
	
	final JTextField userNum = new JTextField();
	final JPasswordField passNum = new JPasswordField();
	final JPasswordField checkPassNum = new JPasswordField();
	
	final JTextField schoolNum  = new JTextField();
	final JTextField birthdayNum  = new JTextField();
	final JTextField IDCardNum  = new JTextField();
	
	JLabel userName = new JLabel("请输入姓名");
	JLabel please = new JLabel("<html><font size = 4 color = red>" +
			"姓名不能为空</font></html>");
	JLabel please1 = new JLabel("<html><font size = 4 color = red>" +
	"学号不能为空</font></html>");
	JLabel passName = new JLabel("请输入密码");
	JLabel checkPassName = new JLabel("确 认 密 码");
	JLabel schoolNumberName = new JLabel("请输入学号");
	JLabel birthday = new JLabel("出 生日 期");
	JLabel IDCard = new JLabel("身份证号码");
	JLabel sex = new JLabel("请选择性别");
	
	JRadioButton man = new JRadioButton("男",true);//默认男被选中
	JRadioButton woman = new JRadioButton("女");
	ButtonGroup sexRadioButtonGruop = new ButtonGroup();//创建一个选按钮组对象
	
	JButton btnOk = new JButton("立即注册");
	JButton btnExit = new JButton("取消");
	JButton btnback = new JButton("返回登陆界面");
	
	public void register(){
		final JFrame jf = new JFrame();
		Container cp = jf.getContentPane();
		jf.setResizable(false);
		jf.setSize(470, 600);
		jf.setVisible(true);
		jf.setBounds(0, 0, 470, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel panel = new JPanel();
		panel.setLayout(null);
		label.setBounds(0, 0, ico.getIconWidth(), ico.getIconHeight());
		panel.add(label);
		
		userName.setBounds(60, 100, 100, 30);
		userNum.setBounds(170, 100, 150, 30);
		please.setBounds(330, 100, 160, 30);
		schoolNumberName.setBounds(60, 150, 100, 30);
		schoolNum.setBounds(170, 150, 150, 30);
		please1.setBounds(330, 150, 160, 30);
		sex.setBounds(60, 200, 100, 30);
		man.setBounds(180, 200, 50, 30);
		woman.setBounds(240, 200, 50, 30);
		birthday.setBounds(60,250,100,30);
		birthdayNum.setBounds(170,250,150,30);
		IDCard.setBounds(60, 300, 100, 30);
		IDCardNum.setBounds(170,300, 150, 30);
		passName.setBounds(60,350,100,30);
		passNum.setBounds(170,350,150,30);
		checkPassName.setBounds(60,400,100,30);
		checkPassNum.setBounds(170,400,150,30);
		btnOk.setBounds(50,460,90,30);
		btnExit.setBounds(160,460,60,30);
		btnback.setBounds(240,460,130, 30);
		//将单选按钮对象添加到按钮组对象中
		sexRadioButtonGruop.add(man);
		sexRadioButtonGruop.add(woman);
		
		man.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		woman.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String passString = new String(passNum.getPassword());
				String checkString = new String(checkPassNum.getPassword());
				
				if(passString.equals(checkString) && passString.length() > 0){
					name = userNum.getText();//姓名
					password = new String(passNum.getPassword());//密码
					schoolNumber = schoolNum.getText();//借书证编号
					IDcardStr = IDCardNum.getText();//身份证号
					if(man.isSelected())
						sexStr = man.getText();
					else
						sexStr = woman.getText();
					
					//将String类型转化为Data类型
					//出生日期
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						birthdayStr = format.parse(birthdayNum.getText());
					} catch (ParseException e1) {
						System.out.println("日期输入有误");
					}
					
					//建立InsertData类的对象，向数据库里面插入数据
					com.dataDeal.InsertData id = new com.dataDeal.InsertData();
					id.insertReader("S"+schoolNumber,name,sexStr,birthdayStr,IDcardStr,0,"否",15,0,0.0,password);
					rs.register();
				}else if(passString.equals("") && passString.equals(checkString)){
					JOptionPane.showMessageDialog(getContentPane(), "密码不能为空，请重新填写");
					passNum.setText("");
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "密码与原密码不同，请重新输入");
					checkPassNum.setText("");
				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JieMian jm = new JieMian();
				jf.setVisible(false);
			}
		});
		
		cp.add(userName);
		cp.add(userNum);
		cp.add(please);
		cp.add(schoolNumberName);
		cp.add(schoolNum);
		cp.add(please1);
		cp.add(sex);
		cp.add(man);
		cp.add(woman);
		cp.add(birthday);
		cp.add(birthdayNum);
		cp.add(IDCard);
		cp.add(IDCardNum);
		cp.add(passName);
		cp.add(passNum);
		cp.add(checkPassName);
		cp.add(checkPassNum);
		cp.add(btnOk);
		cp.add(btnExit);	
		cp.add(btnback);
		cp.add(panel);
	}

	
	public static void main(String[] args) {
		WhenRegister tr = new WhenRegister();
		tr.register();
	}
}
