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
import java.util.Map;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dataDeal.InsertData;
import com.dataDeal.SelectData;
import com.dataDeal.UpdateData;

public class UpdateSurface extends JFrame {	
	JFrame jf = new JFrame("图书信息修改与添加界面");
	Container cp= null;
	
	JPanel bookPanel = null;//书籍修改面板
	JLabel  publishId = new JLabel("出版社编号");//出版社编号
	JLabel booksTypeId = new JLabel("图书类型编号");//图书类型编号
	JLabel storeRoomId = new JLabel("书库号");//书库号
	JLabel booksId = new JLabel("图书编号");//图书编号
	JLabel booksname = new JLabel("书名"); //书名
	JLabel authors = new JLabel("作者");//作者
	JLabel prices = new JLabel("价格");//价格
	JLabel pages = new JLabel("页码");//页码
	JLabel haveing = new JLabel("现存量");//现存量
	JLabel totals = new JLabel("库存总量");//库存总量
	JLabel times = new JLabel("入库时间");//入库时间
	JTextField JpublishId = new JTextField();
	JTextField JbooksTypeId = new JTextField();
	JTextField JstoreRoomId = new JTextField();
	JTextField JbooksId  = new JTextField();
	JTextField Jbooksname = new JTextField();
	JTextField Jauthors= new JTextField();
	JTextField Jprices = new JTextField();
	JTextField Jpages = new JTextField();
	JTextField Jhaveing = new JTextField();
	JTextField Jtotals = new JTextField();
	JTextField Jtimes = new JTextField();
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
	
	public UpdateSurface() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = jf.getContentPane();
		jf.setSize(600, 500);
		jf.setVisible(true);
		cp.setLayout(new BorderLayout());
		cp.setBounds(0, 0, 600, 500);//设置窗体位置和大小
		
		Object[][] results = get.getFileStates();//获得书籍记录
		String[] columnNames = new String[]{
				"出版社编号","图书类型编号","书库号"
				,"图书编号","书名","作者","价格",
				"页码","现存量","库存总量","入库时间"
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
		
		bookPanel.add(publishId);
		bookPanel.add(JpublishId);
		
		bookPanel.add(booksTypeId);	
		bookPanel.add(JbooksTypeId);
	
		bookPanel.add(storeRoomId);	
		bookPanel.add(JstoreRoomId);
		
		bookPanel.add(booksId);	
		bookPanel.add(JbooksId);
		
		bookPanel.add(booksname);	
		bookPanel.add(Jbooksname);
		
		bookPanel.add(authors);		
		bookPanel.add(Jauthors);
		
		bookPanel.add(prices);		
		bookPanel.add(Jprices);
		
		bookPanel.add(pages);
		bookPanel.add(Jpages);
		
		bookPanel.add(haveing);	
		bookPanel.add(Jhaveing);
		
		bookPanel.add(totals);	
		bookPanel.add(Jtotals);
		
		
		//设置日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//创建日期输入框
		JFormattedTextField pubDate = new JFormattedTextField(format.getDateInstance());//创建日期输入框
		pubDate.setValue(new Date());//设置日期为当前系统时间
		bookPanel.add(times); 
		bookPanel.add(Jtimes);//添加到书籍修改面板
		
		mainPanel.add(bookPanel);		
		final GridLayout gridLayout = new GridLayout();//网格布局
		gridLayout.setVgap(5);//设置组件之间垂直距离
		gridLayout.setHgap(5);//设置组件之间平行距离
		//bookPanel.setLayout(gridLayout);//设置书籍修改面板布局
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
				int publishIds,storeRoomIds,booksIds,pagess,haveings,totalss;
				String booksTypeIds,booksNames,authorss;
				Double pricess;
				Date timess = null;
				
				publishIds = Integer.parseInt(JpublishId.getText());
				booksTypeIds = JbooksTypeId.getText();
				storeRoomIds = Integer.parseInt(JstoreRoomId.getText());
				booksIds = Integer.parseInt(JbooksId.getText());
				booksNames = Jbooksname.getText();
				authorss = Jauthors.getText();
				pricess = Double.parseDouble(Jprices.getText());
				pagess = Integer.parseInt(Jpages.getText());
				haveings = Integer.parseInt(Jhaveing.getText());
				totalss = Integer.parseInt(Jtotals.getText());
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					timess = format.parse(Jtimes.getText());
				} catch (ParseException e1) {
					System.out.println("日期输入有误");
				}
				
				UpdateData ud = new UpdateData();
				if(ud.update(publishIds, booksTypeIds, storeRoomIds,booksIds, 
						booksNames, authorss, pricess, pagess, haveings, 
						totalss, timess)){
					JOptionPane.showMessageDialog(getContentPane(), "恭喜你，修改成功");
					jf.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "对不起，修改失败");
				}
				
				new UpdateSurface();
				}
		});
		JButton  btnAdd = new JButton("添加");
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int publishIds,storeRoomIds,booksIds,pagess,haveings,totalss;
				String booksTypeIds,booksNames,authorss;
				Double pricess;
				Date timess = null;
				
				publishIds = Integer.parseInt(JpublishId.getText());
				booksTypeIds = JbooksTypeId.getText();
				storeRoomIds = Integer.parseInt(JstoreRoomId.getText());
				booksIds = Integer.parseInt(JbooksId.getText());
				booksNames = Jbooksname.getText();
				authorss = Jauthors.getText();
				pricess = Double.parseDouble(Jprices.getText());
				pagess = Integer.parseInt(Jpages.getText());
				haveings = Integer.parseInt(Jhaveing.getText());
				totalss = Integer.parseInt(Jtotals.getText());
//				timess = new java.sql.Date(Long.parseLong(Jtimes.getText()));
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					timess = format.parse(Jtimes.getText());
				} catch (ParseException e1) {
					System.out.println("日期输入有误");
				}
				InsertData ud = new InsertData();
				System.out.println(timess);
				if(ud.insertBook(publishIds,booksTypeIds,storeRoomIds,booksIds,
						booksNames,authorss,pricess,pagess,haveings,totalss,timess)){
					Date time = new java.sql.Date(System.currentTimeMillis());
					if(ud.insertBookStore(storeRoomIds, booksIds, time)){
					JOptionPane.showMessageDialog(getContentPane(), "恭喜你，添加成功");
					jf.setVisible(false);
					}
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "抱歉，添加失败");
				}
				
				new UpdateSurface();
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
			String pubId,bookTypeId,storeRoonId,bookId,bookname,
				author,price,page,have,total,time;
			//获得所选行号
			int selRow = table.getSelectedRow();//获得所选行号
			pubId = table.getValueAt(selRow, 0).toString().trim();
			bookTypeId = table.getValueAt(selRow, 1).toString().trim();
			storeRoonId = table.getValueAt(selRow, 2).toString().trim();
			bookId = table.getValueAt(selRow, 3).toString().trim();
			bookname = table.getValueAt(selRow, 4).toString().trim();
			author = table.getValueAt(selRow, 5).toString().trim();
			price = table.getValueAt(selRow, 6).toString().trim();
			page = table.getValueAt(selRow, 7).toString().trim();
			have = table.getValueAt(selRow, 8).toString().trim();
			total = table.getValueAt(selRow, 9).toString().trim();
			time = table.getValueAt(selRow, 10).toString().trim();	
			
			JpublishId.setText(pubId);
			JbooksTypeId.setText(bookTypeId);
			JstoreRoomId.setText(storeRoonId);
			JbooksId.setText(bookId);
			Jbooksname.setText(bookname);
			Jauthors.setText(author);
			Jprices.setText(price);
			Jpages.setText(page);
			Jhaveing.setText(have);
			Jtotals.setText(total);
			Jtimes.setText(time);
		}		
	}
	
	public static void main(String[] args) {
		new UpdateSurface();
	}
}
