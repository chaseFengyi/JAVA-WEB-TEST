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
	JFrame jf = new JFrame("ɾ����Ϣ����");
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
		btnPanel.setLayout(new FlowLayout());//��Ϊ��ʽ����
		JButton btnBook = new JButton("ͼ��");
		JButton btnReader = new JButton("����");
		JButton btnStore = new JButton("���");
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
		flow.setAlignment(FlowLayout.RIGHT);//�����Ҷ���
		bottomPanel.setLayout(flow);//��Ϊ��ʽ����
		JButton btnDrop = new JButton("ɾ��");
		JButton btnExit = new JButton("�˳�");
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
		cp.setBounds(0, 0, 600, 300);//���ô���λ�úʹ�С
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
				//�����ѡ�к�
				selRow = table.getSelectedRow();//�����ѡ�к�
				bookId = table.getValueAt(selRow, 3).toString().trim();
				System.out.println(bookId);
			}else if (Reader != null) {
				//�����ѡ�к�
				selRow = table.getSelectedRow();//�����ѡ�к�
				browId = table.getValueAt(selRow, 0).toString().trim();
				System.out.println(browId);
			}else if (Store != null){
				//�����ѡ�к�
				selRow = table.getSelectedRow();//�����ѡ�к�
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
			Object[][] results = get.getFileStates();//���ͼ���
			String[] columnNames = new String[]{
					"��������","ͼ�����ͱ��","����"
					,"ͼ����","����","����","�۸�",
					"ҳ��","�ִ���","�������","���ʱ��"
			};
			scrollPane = new JScrollPane();//�������
			table = new JTable(results,columnNames);//�������
			//����Ӧ����
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//��굥������е����ݲ����¼���������е����ݷ����ı�����
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);//�������ӵ����������
			new DeleteSurface();
			
		}
		
	}
	
	class BtnReaderListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Reader = e.getActionCommand();
			jf.setVisible(false);
			Object[][] results = get.getReaderStates();//���ͼ���
			String[] columnNames = new String[]{
					"����֤���","����","�Ա�","��������","���֤��",
					"ͼ����Ĵ���","�Ƿ��ʧ","�ɽ����","�ѽ����",
					"δ��������","����","����ʱ��","Ӧ��ʱ��"
			};
			scrollPane = new JScrollPane();//�������
			table = new JTable(results,columnNames);//�������
			//����Ӧ����
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//��굥������е����ݲ����¼���������е����ݷ����ı�����
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);//�������ӵ����������
			new DeleteSurface();
		}
		
	}
	
	class btnStoreListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Store = e.getActionCommand();
			jf.setVisible(false);
			Object[][] results = get.getStoreStates();//���ͼ���
			String[] columnNames = new String[]{
					"����","�����"
			};
			scrollPane = new JScrollPane();//�������
			table = new JTable(results,columnNames);//�������
			//����Ӧ����
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//��굥������е����ݲ����¼���������е����ݷ����ı�����
			table.addMouseListener(new TableListener());
			scrollPane.setViewportView(table);//�������ӵ����������
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
				if(delete.deleteBook("ͼ��", Integer.parseInt(bookId)))
					JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬ɾ���ɹ�");
					jf.setVisible(false);
					new DeleteSurface();
			}else if (browId != null) {
				if(delete.deleteReader("����", browId))
					JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬ɾ���ɹ�");
						jf.setVisible(false);
					new DeleteSurface();
			}else if(storeId != null){
				if(delete.deleteStore("���", Integer.parseInt(storeId)))
					JOptionPane.showMessageDialog(getContentPane(), "��ϲ�㣬ɾ���ɹ�");
					jf.setVisible(false);
					new DeleteSurface();
			}else{
				JOptionPane.showMessageDialog(getContentPane(), "�Բ���ɾ��ʧ��");
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
