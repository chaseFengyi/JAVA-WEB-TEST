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
		JFrame jf = new JFrame("������Ϣ�޸�����ӽ���");
		Container cp= null;
		
		JPanel bookPanel = null;//�鼮�޸����
		JLabel  browId = new JLabel("����֤���");
		JLabel name = new JLabel("����");
		JLabel sex = new JLabel("�Ա�");
		JLabel birthday = new JLabel("��������");
		JLabel idCard = new JLabel("���֤��");
		JLabel browTimes = new JLabel("ͼ����Ĵ���");
		JLabel lost = new JLabel("�Ƿ��ʧ");
		JLabel canBrow = new JLabel("�ɽ����");
		JLabel alreadyBrow = new JLabel("�ѽ����");
		JLabel money = new JLabel("δ��������");
		JLabel password = new JLabel("����");
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
		
		public UpdateReaderSurface() {
			super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cp = jf.getContentPane();
			jf.setSize(600, 500);
			jf.setVisible(true);
			cp.setLayout(new BorderLayout());
			cp.setBounds(0, 0, 600, 500);//���ô���λ�úʹ�С
			
			Object[][] results = get.getReaderStates();//����鼮��¼
			String[] columnNames = new String[]{
					"����֤���","����","�Ա�","��������","���֤��",
					"ͼ����Ĵ���","�Ƿ��ʧ","�ɽ����","�ѽ����",
					"δ��������","����"
			};
			JScrollPane scrollPane = create(results, columnNames);
			//�����
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(2, 1, 5, 5));
			mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//���ñ߿�
			mainPanel.add(scrollPane);//���������ӵ��������
			cp.add(mainPanel,BorderLayout.CENTER);//�������ӵ�������
			
			bookPanel = new JPanel();
			lay = new GridLayout(6, 4, 5, 5);
			bookPanel.setLayout(lay);//����Ϊ���񲼾�
			bookPanel.setBorder(new EmptyBorder(5, 10, 5, 10));//���ñ߿�
			
			bookPanel.add(browId);
			bookPanel.add(JbrowId);
			
			bookPanel.add(name);	
			bookPanel.add(Jname);
		
			bookPanel.add(sex);	
			bookPanel.add(Jsex);
			
			//�������ڸ�ʽ
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//�������������
			JFormattedTextField pubDate = new JFormattedTextField(format.getDateInstance());//�������������
			pubDate.setValue(new Date());//��������Ϊ��ǰϵͳʱ��
			bookPanel.add(birthday); 
			bookPanel.add(Jbirthday);//��ӵ��鼮�޸����
			
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
						System.out.println("�����������");
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
						JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬�޸ĳɹ�");
						jf.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(getContentPane(), "�Բ����޸�ʧ��");
					}
					
					new UpdateReaderSurface();
				}
			});
			JButton  btnAdd = new JButton("���");
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
						System.out.println("������������");
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
						JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬��ӳɹ�");
						jf.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(getContentPane(), "�Բ������ʧ��");
					}
					
					new UpdateReaderSurface();
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
				int JbrowTimess,JcanBrows,JalreadyBrows;
				String JbrowIds,Jnames,Jsexs,JidCards;
				String Jlosts,Jpasswords;
				Double Jmoneys;
				String Jbirthdays = null;
				
				//�����ѡ�к�
				int selRow = table.getSelectedRow();//�����ѡ�к�
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
