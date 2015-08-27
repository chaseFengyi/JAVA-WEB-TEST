package com.manager;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.dataDeal.DeleteData;

public class DeleteSurface extends JFrame {
	GetTable get = new GetTable();
	JFrame jf = new JFrame("删除信息操作");
	Container cp = jf.getContentPane();;
	JPanel mainPanel = new JPanel();
	static JScrollPane scrollPane = new JScrollPane();
	static String bookId = null;
	static String browId = null;
	static String storeId = null;
	static String Book = null;
	static String Reader = null;
	static String Store = null;
	JTable table = null;
	
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
		btnStore.addActionListener(new btnStoreListener());
		
		return btnPanel;
	}
	
	public JPanel between(JScrollPane scrollPane){
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(5,10,5,10));
		mainPanel.add(scrollPane,BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	public JPanel bottom(){
		JPanel bottomPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.RIGHT);//设置右对齐
		bottomPanel.setLayout(flow);//设为流式布局
		JButton btnDrop = new JButton("删除");
		JButton btnExit = new JButton("退出");
		bottomPanel.add(btnDrop);
		bottomPanel.add(btnExit);
		
		btnDrop.addActionListener(new BtnDropListener());
		btnExit.addActionListener(new BtnExitListener());
		
		return bottomPanel;
	}
	
	public DeleteSurface(){
		jf.setSize(600, 300);
		jf.setVisible(true);
		cp.setLayout(new BorderLayout());
		cp.setBounds(0, 0, 600, 300);//设置窗体位置和大小
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel btnPanel = top();
		cp.add(btnPanel, BorderLayout.NORTH);

		JPanel mainPanel = between(scrollPane);
		cp.add(mainPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = bottom();
		cp.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	class TableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int selRow;
			System.out.println(Book);
			System.out.println(Reader);
			System.out.println(Store);
			if(Book != null){
				//获得所选行号
				selRow = table.getSelectedRow();//获得所选行号
				bookId = table.getValueAt(selRow, 3).toString().trim();
				System.out.println(bookId);
			}else if (Reader != null) {
				//获得所选行号
				selRow = table.getSelectedRow();//获得所选行号
				browId = table.getValueAt(selRow, 0).toString().trim();
				System.out.println(browId);
			}else if (Store != null){
				//获得所选行号
				selRow = table.getSelectedRow();//获得所选行号
				storeId = table.getValueAt(selRow, 0).toString().trim();
				System.out.println(storeId);
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
	
	class BtnBookListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
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
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);//将表格添加到滚动面板中
			new DeleteSurface();
			
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
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);//将表格添加到滚动面板中
			new DeleteSurface();
		}
		
	}
	
	class btnStoreListener implements ActionListener{

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
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);//将表格添加到滚动面板中
			new DeleteSurface();
		}
		
	}
	
	class BtnDropListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DeleteData delete = new DeleteData();
			System.out.println("ss"+bookId);
			System.out.println("ss"+browId);
			System.out.println("ss"+storeId);
			if(bookId != null){
				if(delete.deleteBook("图书", Integer.parseInt(bookId)))
					JOptionPane.showMessageDialog(getContentPane(), "恭喜你，删除成功");
					jf.setVisible(false);
					new DeleteSurface();
			}else if (browId != null) {
				if(delete.deleteReader("读者", browId))
					JOptionPane.showMessageDialog(getContentPane(), "恭喜你，删除成功");
						jf.setVisible(false);
					new DeleteSurface();
			}else if(storeId != null){
				if(delete.deleteStore("书库", Integer.parseInt(storeId)))
					JOptionPane.showMessageDialog(getContentPane(), "恭喜你，删除成功");
					jf.setVisible(false);
					new DeleteSurface();
			}else{
				JOptionPane.showMessageDialog(getContentPane(), "对不起，删除失败");
			}
		}
		
	}
	
	class BtnExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jf.setVisible(false);
			new TotalSurface();
		}
		
	}
	
	
	public static void main(String[] args) {
		new DeleteSurface();
	}
}
