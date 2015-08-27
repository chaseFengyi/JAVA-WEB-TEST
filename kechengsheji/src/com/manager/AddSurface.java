package com.manager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.dataDeal.InsertData;
import com.manager.DeleteSurface.BtnBookListener;
import com.manager.DeleteSurface.BtnDropListener;
import com.manager.DeleteSurface.BtnExitListener;
import com.manager.DeleteSurface.BtnReaderListener;
import com.manager.DeleteSurface.TableListener;
import com.manager.DeleteSurface.btnStoreListener;

public class AddSurface extends JFrame {
	GetTable get = new GetTable();
	
	static String Book = null;
	static String BookStr = null;
	static String Reader = null;
	static String ReaderStr = null;
	static String Store = null;
	static String StoreStr = null;
	
	JFrame jf = new JFrame("增加操作界面");
	Container cp;
	static JScrollPane scrollPane = new JScrollPane();
	JScrollPane scroll = new JScrollPane();
	JTable table = null;
	JTable Jtable = null;
	JPanel mainPanel = new JPanel();
	static JPanel panelBook = new JPanel();
	static JPanel panelReader = new JPanel();
	static JPanel panelStore = new JPanel();
	static JPanel panel = new JPanel();
	
	//图书信息
	static int pubId = 0,storeRoonId = 0,bookId = 0,page = 0,have = 0,total = 0;
	static String bookTypeId = null,bookname = null,author = null;
	static double price = 0.0;
	static Date time = null;
	
	//读者信息
	String browId, name, sex, idCard, lost, password;
	Date birthday, browTime, backTime;
	int browTimes, canBrow, alreadyBrow;
	double money;
	
	//书库信息
	int storeId;
	String storeName;
	
	public JPanel top(){
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());//设为流式布局
		JButton btnBook = new JButton("图书");
		JButton btnReader = new JButton("读者");
		JButton btnStore = new JButton("书库");
		btnPanel.add(btnBook);
		btnPanel.add(btnReader);
		btnPanel.add(btnStore);
		
		btnBook.addActionListener(new BtnBookListener());
		btnReader.addActionListener(new BtnReaderListener());
		btnStore.addActionListener(new BtnStoreListener());
		
		return btnPanel;
	}
	
	public JPanel between(JScrollPane scrollPane){
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(5,10,5,10));
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	public JPanel bottom(){
		JPanel bottomPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.RIGHT);//设置右对齐
		bottomPanel.setLayout(flow);//设为流式布局
		JButton btnDrop = new JButton("增加");
		JButton btnExit = new JButton("退出");
		bottomPanel.add(btnDrop);
		bottomPanel.add(btnExit);
		
		btnDrop.addActionListener(new BtnInsertListener());
		btnExit.addActionListener(new BtnExitListener());
		
		return bottomPanel;
	}
	
	public void add(){
		cp = jf.getContentPane();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(600, 500);
		jf.setVisible(true);
		cp.setLayout(new BorderLayout());
		cp.setBounds(0, 0, 600, 500);//设置窗体位置和大小
		
		JPanel top = top();
		cp.add(top, BorderLayout.NORTH);
		
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(2, 1, 5, 5));
		mainPanel = between(scrollPane);
		main.add(mainPanel);
		if(Book != null) {
			main.add(panelBook);
			BookStr = Book;
			Book = null;
		} else if(Reader != null) {
			main.add(panelReader);
			ReaderStr = Reader;
			Reader = null;
		} else if (Store != null){
			main.add(panelStore);
			StoreStr = Store;
			Store = null;
		} else {
			main.add(panel);
		}
		cp.add(main, BorderLayout.CENTER);
		
		JPanel bottom = bottom();
		cp.add(bottom, BorderLayout.SOUTH);
	}
	
	class BtnBookListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Book = e.getActionCommand();
			jf.setVisible(false);
			Object[][] results = get.getFileStates();//获得图书表
			String[] columnNames = new String[]{
					"出版社编号","图书类型编号","书库号"
					,"图书编号","书名","作者","价格",
					"页码","现存量","库存总量","入库时间"
			};
			scrollPane = new JScrollPane();//滚动面板
			table = new JTable(results,columnNames);//创建表格
			//自适应窗体
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//鼠标单击表格中的内容产生事件，将表格中的内容放入文本框中
			scrollPane.setViewportView(table);//将表格添加到滚动面板中
			
			Object[][] result = new Object[10][11];
			Jtable = new JTable(result, columnNames);
			Jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			Jtable.addMouseListener(new TableListener());
			scroll.setViewportView(Jtable);
			panelBook.setLayout(new BorderLayout());
			panelBook.add(scroll, BorderLayout.CENTER);
			
			new AddSurface().add();
		}
		
	}
	
	class BtnReaderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Reader = e.getActionCommand();
			jf.setVisible(false);
			Object[][] results = get.getReaderStates();//获得图书表
			String[] columnNames = new String[]{
					"借书证编号","姓名","性别","出生日期","身份证号",
					"图书借阅次数","是否挂失","可借册数","已借册数",
					"未交罚款金额","密码","借书时间","应还时间"
			};
			scrollPane = new JScrollPane();//滚动面板
			table = new JTable(results,columnNames);//创建表格
			//自适应窗体
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//鼠标单击表格中的内容产生事件，将表格中的内容放入文本框中
			scrollPane.setViewportView(table);//将表格添加到滚动面板中
			
			Object[][] result = new Object[10][13];
			JTable Jtable = new JTable(result, columnNames);
			JScrollPane scroll = new JScrollPane();
			Jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scroll.setViewportView(Jtable);
			Jtable.addMouseListener(new TableListener());
			panelReader.setLayout(new BorderLayout());
			panelReader.add(scroll, BorderLayout.CENTER);
			new AddSurface().add();
		}
		
	}
	
	class BtnStoreListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Store = e.getActionCommand();
			jf.setVisible(false);
			Object[][] results = get.getStoreStates();//获得图书表
			String[] columnNames = new String[]{
					"书库号","书库名"
			};
			scrollPane = new JScrollPane();//滚动面板
			table = new JTable(results,columnNames);//创建表格
			//自适应窗体
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//鼠标单击表格中的内容产生事件，将表格中的内容放入文本框中
			scrollPane.setViewportView(table);//将表格添加到滚动面板中
			
			Object[][] result = new Object[10][2];
			JTable Jtable = new JTable(result, columnNames);
			JScrollPane scroll = new JScrollPane();
			Jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scroll.setViewportView(Jtable);
			Jtable.addMouseListener(new TableListener());
			panelStore.setLayout(new BorderLayout());
			panelStore.add(scroll, BorderLayout.CENTER);

			new AddSurface().add();
		}
		
	}
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int selRow = table.getSelectedRow()+1;//获得所选行号
			System.out.println("yy"+(BookStr != null));
			System.out.println(selRow);
			if(BookStr != null){
				pubId = Integer.parseInt(Jtable.getValueAt(selRow, 0).toString().trim());
				System.out.println("ss"+pubId);
				bookTypeId = Jtable.getValueAt(selRow, 1).toString().trim();
				storeRoonId = Integer.parseInt(Jtable.getValueAt(selRow, 2).toString().trim());
				bookId = Integer.parseInt(Jtable.getValueAt(selRow, 3).toString().trim());
				bookname = Jtable.getValueAt(selRow, 4).toString().trim();
				author = Jtable.getValueAt(selRow, 5).toString().trim();
				price = Double.parseDouble(Jtable.getValueAt(selRow, 6).toString().trim());
				page = Integer.parseInt(Jtable.getValueAt(selRow, 7).toString().trim());
				have = Integer.parseInt(Jtable.getValueAt(selRow, 8).toString().trim());
				total = Integer.parseInt(Jtable.getValueAt(selRow, 9).toString().trim());
				time = new java.sql.Date(Long.parseLong(Jtable.getValueAt(selRow, 10).
						toString().trim()));	
			} else if (ReaderStr != null) {
				browId = table.getValueAt(selRow, 0).toString().trim();
				name = table.getValueAt(selRow, 1).toString().trim();
				sex = table.getValueAt(selRow, 2).toString().trim();
				birthday = new java.sql.Date(Long.parseLong(table.getValueAt
						(selRow, 3).toString().trim()));
				idCard = table.getValueAt(selRow, 4).toString().trim();
				browTimes = Integer.parseInt(table.getValueAt(selRow, 0).
						toString().trim());
				lost = table.getValueAt(selRow, 5).toString().trim();
				canBrow = Integer.parseInt(table.getValueAt(selRow, 6).
						toString().trim());
				alreadyBrow = Integer.parseInt(table.getValueAt(selRow, 7).
						toString().trim());
				money = Double.parseDouble(table.getValueAt(selRow, 8).
						toString().trim());
				password = table.getValueAt(selRow, 9).toString().trim();
				browTime = new java.sql.Date(Long.parseLong(table.getValueAt
						(selRow, 10).toString().trim()));
				backTime = new java.sql.Date(Long.parseLong(table.getValueAt
						(selRow, 11).toString().trim()));
			}else if (StoreStr != null) {
				storeId = Integer.parseInt(table.getValueAt(selRow, 0).toString()
						.trim());
				storeName = table.getValueAt(selRow, 1).toString().trim();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BtnInsertListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			InsertData insert = new InsertData();
			System.out.println("ss"+Book);
			System.out.println("qq"+backTime);
			String sql;
			if(BookStr != null){
				System.out.println(pubId);
				insert.insertBook(pubId,bookTypeId,storeRoonId,bookId,bookname,
						author,price,page,have,total,time);
				JOptionPane.showMessageDialog(getContentPane(), 
						"恭喜你，插入成功");
			}else if (ReaderStr != null) {
				insert.insertReader(browId,name,sex,birthday,idCard,
						browTimes,lost,canBrow,alreadyBrow,money,password);
				JOptionPane.showMessageDialog(getContentPane(), 
				"恭喜你，插入成功");
			}else if (StoreStr != null) {
				insert.insertStore(storeId, storeName);
				JOptionPane.showMessageDialog(getContentPane(), 
				"恭喜你，插入成功");
			}else {
				JOptionPane.showMessageDialog(getContentPane(), 
				"抱歉，插入失败");
			}
		}
		
	}
	
	class BtnExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}
	public static void main(String[] args) {
		new AddSurface().add();
	}
}
