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

public class SelectSurface extends JFrame {
	JFrame jf = new JFrame("ͼ���ѯ��������");
	JPanel mainPanel = new JPanel();
	Container cp ;
	
	static boolean falg = false;
	

	static JTable table;
	static JScrollPane scroll;
	
	static String Book = null;
	static String Reader = null;
	static String Store = null;
	
	JLabel labBook = new JLabel("������ͼ����");
	JLabel labReader = new JLabel("��������߽���֤���");
	JLabel labStore = new JLabel("����������");
	
	JTextField texBook = new JTextField();
	JTextField texReader = new JTextField();
	JTextField texStore = new JTextField();
	
	String sql = "select * from ͼ��";
	SelectData get = new SelectData();
	Vector<Integer> fill = new Vector<Integer>();
	Map<String, Object> data = get.select(sql);
	Set<Integer> storage = new HashSet<Integer>();
	JComboBox drop = null;
	
	public SelectSurface(){
		cp = jf.getContentPane();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(600, 500);
		jf.setVisible(true);
		cp.setLayout(new BorderLayout());
		cp.setBounds(0, 0, 600, 500);//���ô���λ�úʹ�С
		
		final JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(2, 6, 5, 5));//��Ϊ���񲼾�
		int sign;
		for(int i=0; i<Integer.parseInt(data.get("����").toString());i++){
			sign = Integer.parseInt(data.get("ͼ����"+(i+1)).toString());
			storage.add(sign);
		}
		fill.addAll(storage);
		JButton btnDrop = new JButton("ȷ��");
		JButton btnExit = new JButton("�˳�");
		
		drop = new JComboBox(fill);
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(labBook);
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
							"��������","ͼ�����ͱ��","����"
							,"ͼ����","����","����","�۸�",
							"ҳ��","�ִ���","�������","���ʱ��"
					};
					int t = 1;
					for(int j = 0; j<count; j++){
						if(Integer.parseInt(i.toString()) == fill.get(j)){
							source = get.findBook(fill.get(j));
							Object[][] results = new Object[Integer.parseInt(source.get("����").toString())][11];
							for(int k=0; k<results.length; k++){
								results[k][0] = (source.get("��������")).toString();
								results[k][1] = (source.get("ͼ�����ͱ��")).toString();
								results[k][2] = (source.get("����")).toString();
								results[k][3] = (source.get("ͼ����")).toString();
								results[k][4] = (source.get("����")).toString();
								results[k][5] = (source.get("����")).toString();
								results[k][6] = (source.get("�۸�")).toString();
								results[k][7] = (source.get("ҳ��")).toString();
								results[k][8] = (source.get("�ִ���")).toString();
								results[k][9] = (source.get("�������")).toString();
								results[k][10] = (source.get("���ʱ��")).toString();
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
		new SelectSurface();
	}
}
