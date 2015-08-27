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
	JFrame jf = new JFrame("ͼ����Ϣ�޸�����ӽ���");
	Container cp= null;
	
	JPanel bookPanel = null;//�鼮�޸����
	JLabel  publishId = new JLabel("��������");//��������
	JLabel booksTypeId = new JLabel("ͼ�����ͱ��");//ͼ�����ͱ��
	JLabel storeRoomId = new JLabel("����");//����
	JLabel booksId = new JLabel("ͼ����");//ͼ����
	JLabel booksname = new JLabel("����"); //����
	JLabel authors = new JLabel("����");//����
	JLabel prices = new JLabel("�۸�");//�۸�
	JLabel pages = new JLabel("ҳ��");//ҳ��
	JLabel haveing = new JLabel("�ִ���");//�ִ���
	JLabel totals = new JLabel("�������");//�������
	JLabel times = new JLabel("���ʱ��");//���ʱ��
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
	
	public UpdateSurface() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = jf.getContentPane();
		jf.setSize(600, 500);
		jf.setVisible(true);
		cp.setLayout(new BorderLayout());
		cp.setBounds(0, 0, 600, 500);//���ô���λ�úʹ�С
		
		Object[][] results = get.getFileStates();//����鼮��¼
		String[] columnNames = new String[]{
				"��������","ͼ�����ͱ��","����"
				,"ͼ����","����","����","�۸�",
				"ҳ��","�ִ���","�������","���ʱ��"
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
		
		
		//�������ڸ�ʽ
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//�������������
		JFormattedTextField pubDate = new JFormattedTextField(format.getDateInstance());//�������������
		pubDate.setValue(new Date());//��������Ϊ��ǰϵͳʱ��
		bookPanel.add(times); 
		bookPanel.add(Jtimes);//��ӵ��鼮�޸����
		
		mainPanel.add(bookPanel);		
		final GridLayout gridLayout = new GridLayout();//���񲼾�
		gridLayout.setVgap(5);//�������֮�䴹ֱ����
		gridLayout.setHgap(5);//�������֮��ƽ�о���
		//bookPanel.setLayout(gridLayout);//�����鼮�޸���岼��
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
					System.out.println("������������");
				}
				
				UpdateData ud = new UpdateData();
				if(ud.update(publishIds, booksTypeIds, storeRoomIds,booksIds, 
						booksNames, authorss, pricess, pagess, haveings, 
						totalss, timess)){
					JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬�޸ĳɹ�");
					jf.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "�Բ����޸�ʧ��");
				}
				
				new UpdateSurface();
				}
		});
		JButton  btnAdd = new JButton("���");
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
					System.out.println("������������");
				}
				InsertData ud = new InsertData();
				System.out.println(timess);
				if(ud.insertBook(publishIds,booksTypeIds,storeRoomIds,booksIds,
						booksNames,authorss,pricess,pagess,haveings,totalss,timess)){
					Date time = new java.sql.Date(System.currentTimeMillis());
					if(ud.insertBookStore(storeRoomIds, booksIds, time)){
					JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬��ӳɹ�");
					jf.setVisible(false);
					}
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "��Ǹ�����ʧ��");
				}
				
				new UpdateSurface();
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
			String pubId,bookTypeId,storeRoonId,bookId,bookname,
				author,price,page,have,total,time;
			//�����ѡ�к�
			int selRow = table.getSelectedRow();//�����ѡ�к�
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
