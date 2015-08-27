package com.manager;

	import java.awt.BorderLayout;
	import java.awt.Container;
	import java.awt.FlowLayout;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Iterator;
	import java.util.Map;
	import java.util.Set;
	import java.util.Vector;

	import javax.swing.JButton;
	import javax.swing.JCheckBox;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;

	import com.dataDeal.FindData;
	import com.dataDeal.SelectData;
	import com.jiemian.JieMian;

public class SelectReaderSurface extends JFrame {
		JFrame jf = new JFrame("读者查询操作界面");
		JPanel mainPanel = new JPanel();
		Container cp ;
		
		static boolean falg = false;
		

		static JTable table;
		static JScrollPane scroll;
		
		static String Reader = null;
		
		JLabel labReader = new JLabel("请输入读者借书证编号");
		JLabel labStore = new JLabel("请输入书库号");
		
		JTextField texBook = new JTextField();
		JTextField texReader = new JTextField();
		JTextField texStore = new JTextField();
		
		String sql = "select * from 读者";
		SelectData get = new SelectData();
		Vector<String> fill = new Vector<String>();
		Map<String, Object> data = get.select(sql);
		Set<String> storage = new HashSet<String>();
		JComboBox drop = null;
		
		public SelectReaderSurface(){
			cp = jf.getContentPane();
			super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(600, 500);
			jf.setVisible(true);
			cp.setLayout(new BorderLayout());
			cp.setBounds(0, 0, 600, 500);//设置窗体位置和大小
			
			final JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new GridLayout(2, 6, 5, 5));//设为网格布局
			String sign;
			for(int i=0; i<Integer.parseInt(data.get("总数").toString());i++){
				sign = data.get("借书证编号"+(i+1)).toString();
				storage.add(sign);
			}
			fill.addAll(storage);
	
			JButton btnDrop = new JButton("确定");
			JButton btnExit = new JButton("退出");
			
			drop = new JComboBox(fill);
			btnPanel.add(new JLabel());
			btnPanel.add(new JLabel());
			btnPanel.add(new JLabel());
			btnPanel.add(new JLabel());
			btnPanel.add(new JLabel());
			btnPanel.add(new JLabel());
			btnPanel.add(labReader);
			btnPanel.add(drop);
			btnPanel.add(new JLabel());
			btnPanel.add(new JLabel());
			btnPanel.add(btnDrop);
			btnPanel.add(btnExit);
			
			btnDrop.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if( e.getSource()!= null){
						falg = true;
						FindData get = new FindData();
						Map<String, Object> source;
						Object i = drop.getSelectedItem();
						int count = fill.size();
						
						String[] columns = new String[]{
								"借书证编号","姓名","性别","出生日期","身份证号",
								"图书借阅次数","是否挂失","可借册数","已借册数",
								"未交罚款金额","密码"
						};
						int t = 1;
						for(int j = 0; j<count; j++){
							if(i.toString() == fill.get(j)){
								sql = "select * from 读者 where 借书证编号='"+fill.get(j)+"'";
								source = get.findReader(sql);
								Object[][] results = new Object[Integer.parseInt(source.get("总数").toString())][11];
								for(int k=0; k<results.length; k++){
									results[k][0] = (source.get("借书证编号")).toString();
									results[k][1] = (source.get("姓名")).toString();
									results[k][2] = (source.get("性别")).toString();
									results[k][3] = (source.get("出生日期")).toString();
									results[k][4] = (source.get("身份证号")).toString();
									results[k][5] = (source.get("图书借阅次数")).toString();
									results[k][6] = (source.get("是否挂失")).toString();
									results[k][7] = (source.get("可借册数")).toString();
									results[k][8] = (source.get("已借册数")).toString();
									results[k][9] = (source.get("未交罚款金额")).toString();
									results[k][10] = (source.get("密码")).toString();
									t = t+1;
								}
								table = new JTable(results, columns);
								table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
								scroll = new JScrollPane();
								scroll.setViewportView(table);
								cp.add(scroll, BorderLayout.CENTER);
							}
						}
					}
				}
			});
			
			btnExit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					jf.setVisible(false);
					new Add_Update_Select();
				}
			});
			
			cp.add(btnPanel, BorderLayout.NORTH);
		}
		
		public static void main(String[] args) {
			new SelectReaderSurface();
		}
}
