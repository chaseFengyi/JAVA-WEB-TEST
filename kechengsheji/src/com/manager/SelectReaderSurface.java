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
		JFrame jf = new JFrame("���߲�ѯ��������");
		JPanel mainPanel = new JPanel();
		Container cp ;
		
		static boolean falg = false;
		

		static JTable table;
		static JScrollPane scroll;
		
		static String Reader = null;
		
		JLabel labReader = new JLabel("��������߽���֤���");
		JLabel labStore = new JLabel("����������");
		
		JTextField texBook = new JTextField();
		JTextField texReader = new JTextField();
		JTextField texStore = new JTextField();
		
		String sql = "select * from ����";
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
			cp.setBounds(0, 0, 600, 500);//���ô���λ�úʹ�С
			
			final JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new GridLayout(2, 6, 5, 5));//��Ϊ���񲼾�
			String sign;
			for(int i=0; i<Integer.parseInt(data.get("����").toString());i++){
				sign = data.get("����֤���"+(i+1)).toString();
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
								"����֤���","����","�Ա�","��������","���֤��",
								"ͼ����Ĵ���","�Ƿ��ʧ","�ɽ����","�ѽ����",
								"δ��������","����"
						};
						int t = 1;
						for(int j = 0; j<count; j++){
							if(i.toString() == fill.get(j)){
								sql = "select * from ���� where ����֤���='"+fill.get(j)+"'";
								source = get.findReader(sql);
								Object[][] results = new Object[Integer.parseInt(source.get("����").toString())][11];
								for(int k=0; k<results.length; k++){
									results[k][0] = (source.get("����֤���")).toString();
									results[k][1] = (source.get("����")).toString();
									results[k][2] = (source.get("�Ա�")).toString();
									results[k][3] = (source.get("��������")).toString();
									results[k][4] = (source.get("���֤��")).toString();
									results[k][5] = (source.get("ͼ����Ĵ���")).toString();
									results[k][6] = (source.get("�Ƿ��ʧ")).toString();
									results[k][7] = (source.get("�ɽ����")).toString();
									results[k][8] = (source.get("�ѽ����")).toString();
									results[k][9] = (source.get("δ��������")).toString();
									results[k][10] = (source.get("����")).toString();
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
