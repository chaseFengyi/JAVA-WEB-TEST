package GameEdit;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventSetDescriptor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Game.GObject;
import Game.GRImage;
import sun.audio.AudioStream;
class GeventPanel extends JScrollPane{
	JPanel left=new JPanel();
	DefaultListModel dl=new DefaultListModel();
	JList jl;
	GPrograming gp;
	public GeventPanel(GPrograming gprograming){
		this.gp=gprograming;
		int i=0;
		for(GEventNode gen=gp.start; gen!=null ; gen=gen.nextNode,i++){
			dl.add(i,gen.GEventNodeName);
		}
		jl=new JList();	
		jl.setModel(dl);
		this.setViewportView(jl);
	}
	public void UPSetListMode(){
		int i=0;
		dl.removeAllElements();
		for(GEventNode gen=gp.start; gen!=null ; gen=gen.nextNode,i++){
			dl.add(i,gen.GEventNodeName);
		}
		jl.updateUI();
	}
	public int getListSelectIndex(){
		return jl.getSelectedIndex();
	}
	public void changeItem(int index,String element){
		dl.setElementAt(element, index);
	}
	public void addItem(int index,String str){
		if(dl.size()==0||(index<=dl.size()&&index>=0)){
			dl.add(index, str);
			jl.setModel(dl);
			jl.updateUI();
		}
	}
	public void delItem(int x){
		if(dl.size()>0&&x>=0&&x<=dl.size()){
			dl.remove(x);
			jl.setModel(dl);
			jl.updateUI();
		}
	}
	public void delItemSelect(){
		delItem(getListSelectIndex());
	}
}
class GEventOperatorFrame extends JFrame {
	JPanel Buttonpanelleft=new JPanel();
	JPanel Buttonpanelright=new JPanel();
	JPanel sonmenupanel=new JPanel();
	public GEventOperatorFrame(ActionListener acl){
		String names[]=new String[]{"ShowEassy","changeMap","showimage","DestroyImage","PlaySound","DestroySound","PlayAnimation","DestroyAnimation","setIcon","setObject"};
		String names2[]=new String[]{"New Actor!","chinfowin","command","command","command","command","command","command","command","command"};
		Buttonpanelleft.setLayout(new GridLayout(names.length,1,5,5));
		Buttonpanelright.setLayout(new GridLayout(names2.length,1,5,5));
		int n=names.length;
		for(int i=0;i<n;i++){
			JButton jb=new JButton(names[i]);
			
			jb.setActionCommand("SYS_"+names[i].toUpperCase());
			jb.addActionListener(acl);
			Buttonpanelleft.add(jb);
		}
		n=names2.length;
		for(int i=0;i<n;i++){
			JButton jb=new JButton(names2[i]);
			
			jb.setActionCommand("SYS_"+names2[i].toUpperCase());
			jb.addActionListener(acl);
			Buttonpanelright.add(jb);
		}
		this.setLayout(new GridLayout(1,1));
		this.add(Buttonpanelleft);
		this.add(Buttonpanelright);
		//this.add(sonmenupanel);
		this.setBounds(400, 200,320,320);
		this.setVisible(false);
	}
}
public class SysEventFrame extends JFrame implements ActionListener{
	private GwindowchangeFrame gwch;
	GPrograming gprograming;
	SysCRFrame syscrframe;
	DataRoomFrame dataroomframe;
	GObject gOBJECT;
	public static final int SETTYPE = 1;
	public static int state=0;        
	public GeventPanel geventpanel;
	GwindowchangeFrame gwcf=new GwindowchangeFrame(this);    
    GEventOperatorFrame geof=new GEventOperatorFrame(this);
    GTextFieldFrame gtff=new GTextFieldFrame(this);   
    GEventNode cn=null;
    public int indexc;
	public SysEventFrame(GObject gOBJECT, DataRoomFrame dataroomframe, GPrograming gp){   
		this.gOBJECT=gOBJECT;
		this.dataroomframe=dataroomframe;
		this.dataroomframe.setDecideListener(this);
		//this.dataroomframe.setVisible(false);
		this.gprograming=gp;
		
		
		gwch=new GwindowchangeFrame(this);
		
		syscrframe=new SysCRFrame(this.gOBJECT,this);
		geventpanel=new  GeventPanel(this.gprograming);
		JPanel jp=new JPanel();
		String [] names=new String[]{"new","delete","edit","save","load"};
		for(int i=0;i<names.length;i++){ 
			JButton jb=new JButton(names[i]);
			jb.addActionListener(this);
			jb.setActionCommand("GW_"+names[i].toUpperCase());
			jp.add(jb);
		}
		this.add(geventpanel);
		this.add(jp,BorderLayout.EAST);
		this.setBounds(400, 200, 600,400);
		this.setResizable(false);
		this.setVisible(false);
	}
	@Override
	public void actionPerformed(ActionEvent arg0){
		String cmd=arg0.getActionCommand();
		System.out.println(cmd);
		if(cmd.equals("GW_NEW")){
			int index=this.geventpanel.getListSelectIndex();
			indexc=index;
			if(index>=0&&index<this.gprograming.size){
				cn=new GEventNode();
				geof.setVisible(true);
			}
		}else if(cmd.equals("GW_DELETE")){
			int index=this.geventpanel.getListSelectIndex();
			if(index>=0&&index<this.gprograming.size){
				this.geventpanel.delItem(index);
				this.gprograming.delNode(index-1);
				this.geventpanel.gp.Show();
			}
		}else if(cmd.equals("GW_SAVE")){
			this.gprograming.saveGprogramming("pro.txt");
			this.syscrframe.saveCResource("res.txt");
		}else if(cmd.equals("GW_EDIT")){
			int index=this.geventpanel.getListSelectIndex();
			cn=this.gprograming.elementAt(index);
			if(cn!=null){
				this.geventpanel.setVisible(true);
			}
		}else if(cmd.equals("GW_LOAD")){
			this.gprograming.loadProgramming("pro.txt");
			this.syscrframe.loadCResource("res.txt");
			this.geventpanel.UPSetListMode();
			this.gprograming.Show();
		}else if(cmd.equals("GW_DETEMINE")){
			
		}else if(cmd.equals("SYS_SHOWEASSY")){
			gtff.setVisible(true);
			cn.type=GPrograming.show_eassy;
		}else if(cmd.equals("SYS_ADD_NODE_SHOWEASSY")){
			cn.info=gtff.getTextInput();
			cn.getNameTypeString();
			gprograming.insertNode(indexc, cn);
			this.geventpanel.addItem(indexc+1,cn.GEventNodeName);
			gtff.setVisible(false);
			geof.setVisible(false);
		}else if(cmd.equals("SYS_CHANGEMAP")){
			cn.type=GPrograming.change_map;
			this.dataroomframe.setVisible(true);
			this.dataroomframe.setMapstate();
		}else if(cmd.equals("SYS_PLAYANIMATION")){
			cn.type=GPrograming.play_animation;
			this.dataroomframe.setVisible(true);
			this.dataroomframe.setAnimationstate();
		}else if(cmd.equals("SYS_SHOWIMAGE")){
			cn.type=GPrograming.show_img;
			this.dataroomframe.setVisible(true);
			this.dataroomframe.setImagestate();
		}else if(cmd.equals("SYS_PLAYSOUND")){
			cn.type=GPrograming.play_sound;
			this.dataroomframe.setVisible(true);
			this.dataroomframe.setSoundstate();
		}else if(cmd.equals("SYS_DESTROYIMAGE")){
			cn.type=GPrograming.destroy_img;
			this.syscrframe.setVisible(true);
			this.syscrframe.changViemImage();
		}else if(cmd.equals("SYS_DESTROYANIMATION")){
			cn.type=GPrograming.destroy_animation;
			this.syscrframe.setVisible(true);
			this.syscrframe.changViewAnmation();
		}else if(cmd.equals("SYS_DESTROYSOUND")){
			cn.type=GPrograming.destroy_sound;
			this.syscrframe.setVisible(true);
			this.syscrframe.changViemSound();
		}else if(cmd.equals("SYS_ADD_NODE_RESOURCE")){
			if(dataroomframe.state==0){
				cn.emap=dataroomframe.getDataMap();
				cn.info=cn.emap.mapname;
				cn.getNameTypeString();
			}else if(dataroomframe.state==1){
				cn.eanimation=dataroomframe.getDataAnimation();
				cn.info=cn.eanimation.AnmotionName;
				cn.getNameTypeString();
				this.syscrframe.addItemGAnimation(cn.info);
			}else if(dataroomframe.state==2){
				cn.eimg=new GRImage();
				cn.eimg=dataroomframe.getDataImage();
				cn.info=cn.eimg.sourcename;
				cn.getNameTypeString();
				cn.x=cn.eimg.x;
				cn.y=cn.eimg.y;
				cn.w=cn.eimg.w;
				cn.h=cn.eimg.h;
				this.syscrframe.addItemGimage(cn.info);
			}else if(dataroomframe.state==3){
					cn.esound=dataroomframe.getDataSound();
					cn.info=cn.esound.sourcename;
					cn.getNameTypeString();
					this.syscrframe.addItemSound(cn.info);
			}
			gprograming.insertNode(indexc, cn);
			this.geventpanel.addItem(indexc+1,cn.GEventNodeName);
			this.dataroomframe.setVisible(false);
			this.geof.setVisible(false);
		}else if(cmd.equals("SYS_DISSAPPEAR")){
			if(this.syscrframe.state==2){
				cn.info=this.syscrframe.getNameSelectGimage();
				cn.getNameTypeString();
				this.syscrframe.delItemGimage(cn.info);
			}else if(this.syscrframe.state==1){
				cn.info=this.syscrframe.getNameSelectGanimation();
				cn.getNameTypeString();
				this.syscrframe.delItemGanimation(cn.info);
			}else if(this.syscrframe.state==3){
				cn.info=this.syscrframe.getNameSelectGsound();
				cn.getNameTypeString();
				this.syscrframe.delItemGsound(cn.info);
			}
			gprograming.insertNode(indexc, cn);
			this.geventpanel.addItem(indexc+1,cn.GEventNodeName);
			this.syscrframe.setVisible(false);
			this.geof.setVisible(false);
		}else if(cmd.equals("SYS_CHINFOWIN")){
			cn.type=GPrograming.change_giw_pos;
			this.gwcf.setVisible(true);
		}else if(cmd.equals("E_window_size")){
			cn.x=gwcf.getIx();
			cn.y=gwcf.getIy();
			cn.w=gwcf.getIw();
			cn.h=gwcf.getIh();
			gprograming.insertNode(indexc, cn);
			this.geventpanel.addItem(indexc+1,cn.GEventNodeName);
			this.gwcf.setVisible(false);
			this.geof.setVisible(false);
		}
	}
}
class MyTextField extends JFormattedTextField{
	/*get number for this field text*/
	public MyTextField(String str){
		this.setText(str);
	}
	public int GetIntValue(){
		String str=this.getText();
		char[] strs=str.toCharArray();
		int adder=0;
		for(int i=0;i<str.length();i++){
			if(strs[i]>='0'&&strs[i]<='9')
				adder=adder*10+(strs[i]-'0');
		}
		if(adder>49)
			adder=49;
		return adder;
	}
}
class GTextFieldFrame extends JFrame{
	public JButton gettext=new JButton("gettext");
	public JPanel buttonPanel=new JPanel();
	public JTextArea jtf=new JTextArea();
	public GTextFieldFrame(ActionListener gf){
		gettext.setActionCommand("SYS_ADD_NODE_SHOWEASSY");
		gettext.addActionListener(gf);
		buttonPanel.add(gettext);
		this.add(buttonPanel,BorderLayout.SOUTH);
		this.add(jtf);
		this.setBounds(100,100,400,300);
		this.setVisible(false);
	}
	public String getTextInput(){
		return jtf.getText();
	}
}
class GwindowchangeFrame extends JFrame{
	MyTextField tx,ty,tw,th;
	JButton determine;
	public GwindowchangeFrame(SysEventFrame gf){
		JLabel Warnning;
		JPanel jp=new JPanel();
		tx=new MyTextField("x:");
		ty=new MyTextField("y:");
		tw=new MyTextField("w:");
		th=new MyTextField("h:");
		jp.add(tx);
		jp.add(ty);
		jp.add(tw);
		jp.add(th);
		this.add(jp);
		determine=new JButton("determine");
		determine.setActionCommand("E_window_size");
		determine.addActionListener(gf);
		this.add(determine,BorderLayout.SOUTH);
		this.setBounds(100, 200, 100, 200);
		this.setResizable(false);
		this.setVisible(false);
	}
	public int getIx(){
		return tx.GetIntValue();
	}
	public int getIy(){
		return ty.GetIntValue();
	}
	public int getIw(){
		return tw.GetIntValue();
	}
	public int getIh(){
		return th.GetIntValue();
	}
}