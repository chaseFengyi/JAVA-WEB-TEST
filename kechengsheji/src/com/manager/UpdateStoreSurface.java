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

public class UpdateStoreSurface extends JFrame {	
			JFrame jf = new JFrame("书库信息修改与添加界面");
			Container cp= null;
			
			JPanel bookPanel = null;//书籍修改面板
			JLabel  storeId = new JLabel("书库号");
			JLabel name = new JLabel("书库名");
			JTextField JstoreId = new JTextField();
			JTextField Jname = new JTextField();
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
			
			public UpdateStoreSurface() {
				super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cp = jf.getContentPane();
				jf.setSize(600, 500);
				jf.setVisible(true);
				cp.setLayout(new BorderLayout());
				cp.setBounds(0, 0, 600, 500);//设置窗体位置和大小
				
				Object[][] results = get.getStoreStates();//获得书库记录
				String[] columnNames = new String[]{
						"书库号","书库名"
				};
				JScrollPane scrollPane = create(results, columnNames);
				//主面板
				mainPanel = new JPanel();
				mainPanel.setLayout(new GridLayout(2, 1, 5, 5));
				mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
				mainPanel.add(scrollPane);//将滚动面板加到主面板中
				cp.add(mainPanel,BorderLayout.CENTER);//将面板添加到窗体中
				
				bookPanel = new JPanel();
				lay = new GridLayout(2, 2, 5, 5);
				bookPanel.setLayout(lay);//设置为网格布句
				bookPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//设置边框
				
				bookPanel.add(storeId);
				bookPanel.add(JstoreId);
				
				bookPanel.add(name);	
				bookPanel.add(Jname);
				
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
						int JstoreIds;
						String Jnames;
						
						JstoreIds = Integer.parseInt(JstoreId.getText());
						Jnames = Jname.getText();
						
						UpdateData ud = new UpdateData();
						if(ud.update(JstoreIds,Jnames)){
							JOptionPane.showMessageDialog(getContentPane(), "恭喜你，修改成功");
							jf.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(getContentPane(), "对不起，修改失败");
						}
						
						new UpdateStoreSurface();
						}
				});
				JButton  btnAdd = new JButton("添加");
				btnAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int storeIds;
						String names;
						
						storeIds = Integer.parseInt(JstoreId.getText());
						names = Jname.getText();
						
						InsertData insert = new InsertData();
						if(insert.insertStore(storeIds, names)){
							JOptionPane.showMessageDialog(getContentPane(), "恭喜你，修改成功");
							jf.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(getContentPane(), "对不起，添加失败");
						}
						
						new UpdateStoreSurface();
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
					int store;
					String name;
					
					//获得所选行号
					int selRow = table.getSelectedRow();//获得所选行号
					store = Integer.parseInt(table.getValueAt(selRow, 0).toString().trim());
					name = table.getValueAt(selRow, 1).toString().trim();
					
					JstoreId.setText(store+"");
					Jname.setText(name);
				}		
			}
			
			public static void main(String[] args) {
				new UpdateStoreSurface();
			}
}
