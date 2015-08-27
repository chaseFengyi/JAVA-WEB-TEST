package com.readerinfor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.crypto.Data;

import com.dataDeal.FindData;
import com.dataDeal.SelectData;
import com.dataDeal.UpdateData;
import com.jiemian.JieMian;

public class ReaderInfo extends JFrame {
	JFrame jf = new JFrame("读者借书情况");
	Container cp = jf.getContentPane();
	FindData find = new FindData();
	SelectData sd = new SelectData();
	JButton back;
	JButton again;
	
	static String browId ,name,names;
	static String browTime;
	static String backTime;
	
	JTable table;
	
	public ReaderInfo(final String brow) {
		jf.setSize(650,400);
		jf.setVisible(true);
		cp.setBounds(0, 0, 650, 400);
		cp.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.RIGHT);
		panel.setLayout(flow);
		back = new JButton("返回");
		again = new JButton("续借");
		panel.add(again);
		panel.add(back);
		
		cp.add(panel, BorderLayout.SOUTH);
		String sqlStr = "select 姓名,书名,应还时间,借书时间 from 借书,读者" +
				" where 借书.借书证编号=读者.借书证编号 and 借书." +
				"借书证编号 ='"+brow+"'" ;
		Map<String, Object> book = find.findReader(sqlStr); 
//		System.out.println("ww"+book.size());
		String[] columnNames = {"序号","姓名","借书证编号","所借书籍","流通状态","应还日期"};
		String[][] tableValue = new String[1][columnNames.length];
		if(book.size() == 1){
			JOptionPane.showMessageDialog(getContentPane(), "抱歉，没有找到该读者借书信息");
		}else{
			JButton btnContinue = new JButton("续借");
			int i = 0;
			for(int j=0; j<tableValue.length; j++){
					tableValue[j][0] = (i+1)+"";
					tableValue[j][1] = (String) book.get("姓名");
					tableValue[j][2] = brow;
					tableValue[j][3] = (String)book.get("书名");
					tableValue[j][4] = "已借出";
					DateFormat format1= new SimpleDateFormat("yyyy-MM-dd");
					tableValue[j][5] = book.get("应还时间").toString();
					i = i+1;
			}
			browTime = book.get("借书时间").toString();
			table = new JTable(tableValue, columnNames);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					int selRow = table.getSelectedRow();
					browId = brow;
					names = table.getValueAt(selRow, 1).toString().trim();
					name = table.getValueAt(selRow, 3).toString().trim();
					DateFormat format1= new SimpleDateFormat("yyyy-MM-dd");
//					browTime = table.getValueAt(selRow, 5).toString().trim();
//					System.out.println("aa"+browTime);
//					Date time = new java.sql.Date(Long.parseLong((String)table.getValueAt(selRow, 5)));
//					Calendar gc = Calendar.getInstance();
//					gc.setTime(time);
//					gc.add(Calendar.DAY_OF_MONTH, 15);
					backTime = table.getValueAt(selRow, 5).toString().trim();
					System.out.println(backTime);
					System.out.println(browId);
					System.out.println(name);
					System.out.println(browTime);
				}
			});
			//创建显示面板的滚动条
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setViewportView(table);
			//将滚动面板添加到边界布局的中央
			cp.add(scrollPane, BorderLayout.CENTER);
			
		}
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.setVisible(false);
				new JieMian();
			}
		});
		
		again.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date brows = null,back = null;
				System.out.println("ggg");
				UpdateData change = new UpdateData();
				System.out.println(backTime);
				System.out.println("qq");
				System.out.println(browTime);
				System.out.println("aa"+browId);
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					brows = format.parse(browTime);
					back = format.parse(backTime);
				} catch (ParseException e1) {
//					System.out.println("日期输入有误");
					e1.printStackTrace();
				}
				
				if(change.update(browId, name, brows, back)){
					JOptionPane.showMessageDialog(getContentPane(), "恭喜你，修改成功");
					jf.setVisible(false);
					new ReaderInfo(brow);
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "恭喜你，成功续借");
				}
			}
		});
		
	}
	
	public static void main(String[] args) {
		new ReaderInfo("S04123124");
	}
}
