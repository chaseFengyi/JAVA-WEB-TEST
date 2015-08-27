package com.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dataDeal.InsertData;
import com.dataDeal.UpdateData;

public class UpdateReaderSurface extends JFrame {	
		JFrame jf = new JFrame("读者信息修改与添加界面");
		Container cp= null;
		
		JPanel bookPanel = null;//书籍修改面板
		JLabel  browId = new JLabel("借书证编号");
		JLabel name = new JLabel("姓名");
		JLabel sex = new JLabel("性别");
		JLabel birthday = new JLabel("出生日期");
		JLabel idCard = new JLabel("身份证号");
		JLabel browTimes = new JLabel("图书借阅次数");
		JLabel lost = new JLabel("是否挂失");
		JLabel canBrow = new JLabel("可借册数");
		JLabel alreadyBrow = new JLabel("已借册数");
		JLabel money = new JLabel("未交罚款金额");
		JLabel password = new JLabel("密码");
		JTextField JbrowId = new JTextField();
		JTextField Jname = new JTextField();
		JTextField Jsex  = new JTextField();
		JTextField Jbirthday = new JTextField();
		JTextField JidCard= new JTextField();
		JTextField JbrowTimes = new JTextField();
		JTextField Jlost = new JTextField();
		JTextField JcanBrow = new JTextField();
		JTextField JalreadyBrow = new JTextField();
		JTextField Jmoney = new JTextField();
		JTextField Jpassword = new JTextField();
		GridLayout lay;
		
		JTable table = null;
		JPanel mainPanel = null;
		
		GetTable get = new GetTable();
		
		//创建主面板
		public JScrollPane create(Object[][] results, String[] columnNames){		
			JScrollPane scrollPane = new JScrollPane();//滚动面板
			table = new JTable(results,columnNames);//创建表格
			//自适应窗体
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//鼠标单击表格中的内容产生事件，将表格中的内容放入文本框中
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);//将表格添加到滚动面板中
			return scrollPane;
		}
		
		public UpdateReaderSurface() {
			super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cp = jf.getContentPane();
			jf.setSize(600, 500);
			jf.setVisible(true);
			cp.setLayout(new BorderLayout());
			cp.setBounds(0, 0, 600, 500);//设置窗体位置和大小
			
			Object[][] results = get.getReaderStates();//获得书籍记录
			String[] columnNames = new String[]{
					"借书证编号","姓名","性别","出生日期","身份证号",
					"图书借阅次数","是否挂失","可借册数","已借册数",
					"未交罚款金额","密码"
			};
			JScrollPane scrollPane = create(results, columnNames);
			//主面板
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(2, 1, 5, 5));
			mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
			mainPanel.add(scrollPane);//将滚动面板加到主面板中
			cp.add(mainPanel,BorderLayout.CENTER);//将面板添加到窗体中
			
			bookPanel = new JPanel();
			lay = new GridLayout(6, 4, 5, 5);
			bookPanel.setLayout(lay);//设置为网格布句
			bookPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
			
			bookPanel.add(browId);
			bookPanel.add(JbrowId);
			
			bookPanel.add(name);	
			bookPanel.add(Jname);
		
			bookPanel.add(sex);	
			bookPanel.add(Jsex);
			
			//设置日期格式
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//创建日期输入框
			JFormattedTextField pubDate = new JFormattedTextField(format.getDateInstance());//创建日期输入框
			pubDate.setValue(new Date());//设置日期为当前系统时间
			bookPanel.add(birthday); 
			bookPanel.add(Jbirthday);//添加到书籍修改面板
			
			bookPanel.add(idCard);	
			bookPanel.add(JidCard);
			
			bookPanel.add(browTimes);		
			bookPanel.add(JbrowTimes);
			
			bookPanel.add(lost);		
			bookPanel.add(Jlost);
			
			bookPanel.add(canBrow);
			bookPanel.add(JcanBrow);

			bookPanel.add(alreadyBrow);
			bookPanel.add(JalreadyBrow);			
			
			bookPanel.add(money);	
			bookPanel.add(Jmoney);
			
			bookPanel.add(password);	
			bookPanel.add(Jpassword);
			
			mainPanel.add(bookPanel);		
			final GridLayout gridLayout = new GridLayout();//网格布局
			gridLayout.setVgap(5);//设置组件之间垂直距离
			gridLayout.setHgap(5);//设置组件之间平行距离
			JPanel bottomPanel = new JPanel();//创建底部面板
			bottomPanel.setBorder(new LineBorder(
					SystemColor.activeCaptionBorder,1,false));//设置边框
			cp.add(bottomPanel,BorderLayout.SOUTH);
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(2);
			flowLayout.setHgap(30);
			flowLayout.setAlignment(FlowLayout.RIGHT);//设置向右对齐
			bottomPanel.setLayout(flowLayout);
			JButton btnUpdate = new JButton("修改");
			btnUpdate.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int JbrowTimess,JcanBrows,JalreadyBrows;
					String JbrowIds,Jnames,Jsexs,JidCards;
					String Jlosts,Jpasswords;
					Double Jmoneys;
					Date Jbirthdays = null;
					
					JbrowIds = JbrowId.getText();
					Jnames = Jname.getText();
					Jsexs = Jsex.getText();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Jbirthdays = format.parse(Jbirthday.getText());
					} catch (ParseException e1) {
						System.out.println("日期输入错误");
//						e1.printStackTrace();
					}
					JidCards = JidCard.getText();
					JbrowTimess = Integer.parseInt(JbrowTimes.getText());
					Jlosts = Jlost.getText();
					JcanBrows = Integer.parseInt(JcanBrow.getText());
					JalreadyBrows = Integer.parseInt(JalreadyBrow.getText());
					Jmoneys = Double.parseDouble(Jmoney.getText());
					Jpasswords = Jpassword.getText();
					
					UpdateData ud = new UpdateData();
					if(ud.update(JbrowIds, Jnames, Jsexs,Jbirthdays, 
							JidCards, JbrowTimess, Jlosts, JcanBrows, JalreadyBrows, 
							Jmoneys, Jpasswords)){
						JOptionPane.showMessageDialog(getContentPane(), "恭喜你，修改成功");
						jf.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(getContentPane(), "对不起，修改失败");
					}
					
					new UpdateReaderSurface();
				}
			});
			JButton  btnAdd = new JButton("添加");
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int JbrowTimess,JcanBrows,JalreadyBrows;
					String JbrowIds,Jnames,Jsexs,JidCards;
					String Jlosts,Jpasswords;
					Double Jmoneys;
					Date Jbirthdays = null;
					
					JbrowIds = JbrowId.getText();
					Jnames = Jname.getText();
					Jsexs = Jsex.getText();
//					Jbirthdays = new java.sql.Date(Long.parseLong(Jbirthday.getText()));
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Jbirthdays = format.parse(Jbirthday.getText());
					} catch (ParseException e1) {
						System.out.println("日期输入有误");
					}
					JidCards = JidCard.getText();
					JbrowTimess = Integer.parseInt(JbrowTimes.getText());
					Jlosts = Jlost.getText();
					JcanBrows = Integer.parseInt(JcanBrow.getText());
					JalreadyBrows = Integer.parseInt(JalreadyBrow.getText());
					Jmoneys = Double.parseDouble(Jmoney.getText());
					Jpasswords = Jpassword.getText();
					
					InsertData insert = new InsertData();
					if(insert.insertReader(JbrowIds, Jnames, Jsexs,Jbirthdays, 
							JidCards, JbrowTimess, Jlosts, JcanBrows, JalreadyBrows, 
							Jmoneys, Jpasswords)){
						JOptionPane.showMessageDialog(getContentPane(), "恭喜你，添加成功");
						jf.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(getContentPane(), "对不起，添加失败");
					}
					
					new UpdateReaderSurface();
				}
			});
			JButton btnclose = new JButton("关闭");//创建关闭按钮
			btnclose.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jf.setVisible(false);
					new Add_Update_Select();
				}
			});
			//添加到底部面板
			bottomPanel.add(btnUpdate);
			bottomPanel.add(btnclose);
			bottomPanel.add(btnAdd);
			
			JLabel logo = new JLabel();//图片标签
			ImageIcon ico = new ImageIcon("E:\\Workspaces\\picture/3.jpg");//图片图标
			logo.setIcon(ico);//设置标签显示图片
			logo.setOpaque(true);//设置图片标签不透明
			logo.setBackground(Color.CYAN);
			logo.setPreferredSize(new Dimension(500, 120));//设置标签大小
			logo.setBorder(new LineBorder(SystemColor.activeCaptionBorder,1,false));
			cp.add(logo,BorderLayout.NORTH);		
		}
		
		class TableListener extends MouseAdapter{

			@Override
			public void mouseClicked(MouseEvent e) {
				int JbrowTimess,JcanBrows,JalreadyBrows;
				String JbrowIds,Jnames,Jsexs,JidCards;
				String Jlosts,Jpasswords;
				Double Jmoneys;
				String Jbirthdays = null;
				
				//获得所选行号
				int selRow = table.getSelectedRow();//获得所选行号
				JbrowIds = table.getValueAt(selRow, 0).toString().trim();
				Jnames = table.getValueAt(selRow, 1).toString().trim();
				Jsexs = table.getValueAt(selRow, 2).toString().trim();
				Jbirthdays = table.getValueAt(selRow, 3).toString().trim();
				JidCards = table.getValueAt(selRow, 4).toString().trim();
				JbrowTimess = Integer.parseInt(table.getValueAt(selRow, 5).toString().trim());
				Jlosts = table.getValueAt(selRow, 6).toString().trim();
				JcanBrows = Integer.parseInt(table.getValueAt(selRow, 7).toString().trim());
				JalreadyBrows = Integer.parseInt(table.getValueAt(selRow, 8).toString().trim());
				Jmoneys = Double.parseDouble(table.getValueAt(selRow, 9).toString().trim());
				Jpasswords = table.getValueAt(selRow, 10).toString().trim();	
				
				JbrowId.setText(JbrowIds);
				Jname.setText(Jnames);
				Jsex.setText(Jsexs);
				Jbirthday.setText(Jbirthdays);
				JidCard.setText(JidCards);
				JbrowTimes.setText(JbrowTimess+"");
				Jlost.setText(Jlosts);
				JcanBrow.setText(JcanBrows+"");
				JalreadyBrow.setText(JalreadyBrows+"");
				Jmoney.setText(Jmoneys+"");
				Jpassword.setText(Jpasswords);
			}		
		}
		
		public static void main(String[] args) {
			new UpdateReaderSurface();
		}
}
