package GameEdit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Game.GObject;
/*4.99*/
class SysCRFrame extends JFrame{//make disappear
	public int state=1;
	GObject GOBJECT;
	public DefaultListModel dlmGanimation;
	public DefaultListModel dlmGimage;
	public DefaultListModel dlmGsound;
	public JList CRimagelist =new JList();
	public JList CRanimationlist =new JList();
	public JList CRsoundlist =new JList();
	
	public JScrollPane listpane=new JScrollPane();
	public JPanel TopPanel=new JPanel();
	
	public JButton decide=new JButton("Detemine");
	
	public SysCRFrame(GObject GOBJECT,ActionListener ac){
		this.GOBJECT=GOBJECT;
		
		MyAdapator myadaptor=new MyAdapator();
		
		dlmGanimation=new DefaultListModel();
		dlmGimage=new DefaultListModel();
		dlmGsound=new DefaultListModel();
		for(int i=0;i<GOBJECT.GAnimations.size();i++){
			dlmGanimation.add(i, GOBJECT.GAnimations.elementAt(i).AnmotionName);
		}
		for(int i=0;i<GOBJECT.GImages.size();i++){
			dlmGimage.add(i, GOBJECT.GImages.elementAt(i).sourcename);
		}
		this.CRanimationlist.setModel(dlmGanimation);
		this.CRimagelist.setModel(dlmGimage);
		this.CRsoundlist.setModel(dlmGsound);
		
		
		this.CRanimationlist.addMouseListener(myadaptor);
		this.CRimagelist.addMouseListener(myadaptor);
		this.CRsoundlist.addMouseListener(myadaptor);
		
		decide.setActionCommand("SYS_DISSAPPEAR");
		decide.addActionListener(ac);
		
		state=2;
		listpane.setViewportView(CRimagelist);
		TopPanel.setLayout(new BorderLayout());
		TopPanel.add(listpane,BorderLayout.CENTER);
		
		JPanel southpanel=new JPanel();
		southpanel.setLayout(new FlowLayout());
		southpanel.add(decide);
		TopPanel.add(southpanel,BorderLayout.SOUTH);
		this.add(TopPanel);
		this.setBounds(200, 200, 270, 220);
		this.setVisible(false);
	}
	class MyAdapator extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
            if(e.getClickCount()>1){
            	if(e.getComponent().equals(CRanimationlist)){
            		int index=CRanimationlist.getSelectedIndex();
            		if(index>=0&&index<CRanimationlist.getModel().getSize()){
            			dlmGanimation.remove(index);
            			CRanimationlist.updateUI();
            		}
            	}else if(e.getComponent().equals(CRimagelist)){
            		int index=CRimagelist.getSelectedIndex();
            		if(index>=0&&index<CRimagelist.getModel().getSize()){
            			dlmGimage.remove(index);
            			CRimagelist.updateUI();
            		}
            		
            	}else if(e.getComponent().equals(CRsoundlist)){
            		int index=CRsoundlist.getSelectedIndex();
            		if(index>=0&&index<CRsoundlist.getModel().getSize()){
            			dlmGsound.remove(index);
            			CRsoundlist.updateUI();
            		}
            	}
            }
        }
	}
	/*public boolean isCLimage(ActionEvent e){
		
	}*/
	public void addItemGimage(String name){
		this.dlmGimage.add(this.dlmGimage.size(), name);
		this.CRimagelist.updateUI();
		this.listpane.setViewportView(CRimagelist);
	}
	public void addItemSound(String name){
		this.dlmGsound.add(this.dlmGsound.size(), name);
		this.CRsoundlist.updateUI();
		this.listpane.setViewportView(CRsoundlist);
	}
	public void addItemGAnimation(String name){
		this.dlmGanimation.add(this.dlmGanimation.size(), name);
		this.CRanimationlist.updateUI();
		this.listpane.setViewportView(CRanimationlist);
	}
	public void reMoveSelect(){
		
	}
	public void deldlmItem(DefaultListModel dlm,String name){
		int i;
		for(i=0;i<dlm.size();){
			if(name.equals(dlm.elementAt(i))){
				dlm.remove(i);
			}else{
				i++;
			}
		}
	}
	public void delItemGimage(String name){
		this.deldlmItem(dlmGimage, name);
		this.CRimagelist.updateUI();
	}
	public void delItemGanimation(String name){
		this.deldlmItem(dlmGanimation, name);
		this.CRanimationlist.updateUI();
	}
	public void delItemGsound(String name){
		this.deldlmItem(dlmGsound, name);
		this.CRsoundlist.updateUI();
	}
	public String getNameSelectGimage(){
		int index=CRimagelist.getSelectedIndex();
		if(index>=0&&index<dlmGimage.size()){
			return (String)this.dlmGimage.elementAt(index);
		}
		return "Null";
	}
	public String getNameSelectGsound(){
		int index=CRsoundlist.getSelectedIndex();
		if(index>=0&&index<dlmGsound.size()){
			return (String)this.dlmGsound.elementAt(index);
		}
		return "Null";
	}
	public String getNameSelectGanimation(){
		int index=CRanimationlist.getSelectedIndex();
		if(index>=0&&index<dlmGanimation.size()){
			return (String)this.dlmGanimation.elementAt(index);
		}
		return "Null";
	}
	public void saveCResource(String str){
		String path=System.getProperty("user.dir");
		path+="/Gameresource/CurrentResource/";
		File file=new File(path);
		if(file.exists()==false){
			file.mkdir();
		}
		path+=str;
		try {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(new File(path)));
			this.saveCResourcedlm(dos, dlmGsound);
			this.saveCResourcedlm(dos, dlmGimage);
			this.saveCResourcedlm(dos, dlmGanimation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveCResourcedlm(DataOutputStream dos,DefaultListModel dlm){
		try {
			//System.out.println("hello:"+dlm.getSize());
			dos.writeInt(dlm.getSize());
			for(int i=0;i<dlm.size();i++){
				//	System.out.println("name:"+(String)dlm.elementAt(i));
					dos.writeUTF((String)dlm.elementAt(i));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public void loadCResource(String str){
		String path=System.getProperty("user.dir");
		path+="/Gameresource/CurrentResource/";
		path+=str;
		try {
			DataInputStream dis=new DataInputStream(new FileInputStream(new File(path)));
			this.dlmGanimation.removeAllElements();
			this.dlmGimage.removeAllElements();
			this.dlmGsound.removeAllElements();
			this.loadCResourcedlm(dis, dlmGsound,3);
			this.loadCResourcedlm(dis, dlmGimage,2);
			this.loadCResourcedlm(dis, dlmGanimation,1);
			this.CRanimationlist.updateUI();
			this.CRimagelist.updateUI();
			this.CRsoundlist.updateUI();
			this.changViemSound();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadCResourcedlm(DataInputStream dis,DefaultListModel dlm,int type){
		int size;
		try {
			size = dis.readInt();
			//System.out.println("Size:"+size);
			for(int i=0;i<size;i++){
				String str=dis.readUTF();
				if(type==1){
					this.dlmGanimation.add(i, str);
				}else if(type==2){
					this.dlmGimage.add(i, str);
				}else if(type==3){
					this.dlmGsound.add(i, str);
				}else{
					System.out.println("error load type currentresourceframe");
				}
				//System.out.println("name:"+str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void changViewAnmation(){
		state=1;
		this.listpane.setViewportView(CRanimationlist);
	}
	public void changViemSound(){
		state=3;
		this.listpane.setViewportView(CRsoundlist);
	}
	public void changViemImage(){
		state=2;
		this.listpane.setViewportView(CRimagelist);
	}
}
