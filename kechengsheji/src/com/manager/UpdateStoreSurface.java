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
			JFrame jf = new JFrame("�����Ϣ�޸�����ӽ���");
			Container cp= null;
			
			JPanel bookPanel = null;//�鼮�޸����
			JLabel  storeId = new JLabel("����");
			JLabel name = new JLabel("�����");
			JTextField JstoreId = new JTextField();
			JTextField Jname = new JTextField();
			GridLayout lay;
			
			JTable table = null;
			JPanel mainPanel = null;
			
			GetTable get = new GetTable();
			
			//���������
			public JScrollPane create(Object[][] results, String[] columnNames){		
				JScrollPane scrollPane = new JScrollPane();//�������
				table = new JTable(results,columnNames);//�������
				//����Ӧ����
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				//��굥������е����ݲ����¼���������е����ݷ����ı�����
				table.addMouseListener(new TableListener());
				scrollPane.setViewportView(table);//�������ӵ����������
				return scrollPane;
			}
			
			public UpdateStoreSurface() {
				super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cp = jf.getContentPane();
				jf.setSize(600, 500);
				jf.setVisible(true);
				cp.setLayout(new BorderLayout());
				cp.setBounds(0, 0, 600, 500);//���ô���λ�úʹ�С
				
				Object[][] results = get.getStoreStates();//�������¼
				String[] columnNames = new String[]{
						"����","�����"
				};
				JScrollPane scrollPane = create(results, columnNames);
				//�����
				mainPanel = new JPanel();
				mainPanel.setLayout(new GridLayout(2, 1, 5, 5));
				mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//���ñ߿�
				mainPanel.add(scrollPane);//���������ӵ��������
				cp.add(mainPanel,BorderLayout.CENTER);//�������ӵ�������
				
				bookPanel = new JPanel();
				lay = new GridLayout(2, 2, 5, 5);
				bookPanel.setLayout(lay);//����Ϊ���񲼾�
				bookPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//���ñ߿�
				
				bookPanel.add(storeId);
				bookPanel.add(JstoreId);
				
				bookPanel.add(name);	
				bookPanel.add(Jname);
				
				mainPanel.add(bookPanel);		
				final GridLayout gridLayout = new GridLayout();//���񲼾�
				gridLayout.setVgap(5);//�������֮�䴹ֱ����
				gridLayout.setHgap(5);//�������֮��ƽ�о���
				JPanel bottomPanel = new JPanel();//�����ײ����
				bottomPanel.setBorder(new LineBorder(
						SystemColor.activeCaptionBorder,1,false));//���ñ߿�
				cp.add(bottomPanel,BorderLayout.SOUTH);
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setVgap(2);
				flowLayout.setHgap(30);
				flowLayout.setAlignment(FlowLayout.RIGHT);//�������Ҷ���
				bottomPanel.setLayout(flowLayout);
				JButton btnUpdate = new JButton("�޸�");
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
							JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬�޸ĳɹ�");
							jf.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(getContentPane(), "�Բ����޸�ʧ��");
						}
						
						new UpdateStoreSurface();
						}
				});
				JButton  btnAdd = new JButton("���");
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
							JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬�޸ĳɹ�");
							jf.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(getContentPane(), "�Բ������ʧ��");
						}
						
						new UpdateStoreSurface();
						}
				});
				JButton btnclose = new JButton("�ر�");//�����رհ�ť
				btnclose.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						jf.setVisible(false);
						new Add_Update_Select();
					}
				});
				//��ӵ��ײ����
				bottomPanel.add(btnUpdate);
				bottomPanel.add(btnclose);
				bottomPanel.add(btnAdd);
				
				JLabel logo = new JLabel();//ͼƬ��ǩ
				ImageIcon ico = new ImageIcon("E:\\Workspaces\\picture/3.jpg");//ͼƬͼ��
				logo.setIcon(ico);//���ñ�ǩ��ʾͼƬ
				logo.setOpaque(true);//����ͼƬ��ǩ��͸��
				logo.setBackground(Color.CYAN);
				logo.setPreferredSize(new Dimension(500, 120));//���ñ�ǩ��С
				logo.setBorder(new LineBorder(SystemColor.activeCaptionBorder,1,false));
				cp.add(logo,BorderLayout.NORTH);		
			}
			
			class TableListener extends MouseAdapter{

				@Override
				public void mouseClicked(MouseEvent e) {
					int store;
					String name;
					
					//�����ѡ�к�
					int selRow = table.getSelectedRow();//�����ѡ�к�
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
