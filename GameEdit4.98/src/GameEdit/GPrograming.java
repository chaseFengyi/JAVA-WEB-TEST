package GameEdit;
import java.awt.Graphics;

import java.awt.Image;

import sun.audio.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Game.GObject;
import Game.GRImage;
import Game.GRSound;
import Game.GameSystem;

class GEventNode { 
	int  type,x,y,w,h;
	public String info;
	public GRImage eimg;
	public GRSound esound;
	public GAnimation eanimation;
	public GMap emap;
	public String GEventNodeName;
	public GEventNode nextNode;
	public GEventNode(){
		this.GEventNodeName="null";
		type=10;
		x=y=w=h=255;
		info="no";
		nextNode=null;
	}
	public void getNameTypeString(){//getGeventTypes
		String str=this.GEventNodeName;
		switch(type){
			case GPrograming.show_eassy: str="ShowEassy:"+info; break;
			case GPrograming.show_img: str="ShowImage:  "+info+x+" "+y+" "+w+"  "+h; break;
			case GPrograming.destroy_img: str="Destroyimg:  "+info; break;
			case GPrograming.play_sound:str="Playsound:"+info; break;
			case GPrograming.destroy_sound:str="DestroySound:"+info; break;
			case GPrograming.play_animation:str="PlayAnimation:"+info; break;
			case GPrograming.destroy_animation:str="DestroyAnimation:"+info; break;
			default : str="no   way";break;
		}
		this.GEventNodeName=str;
	}
}
public class GPrograming{
	int px=0,py=0;
	public Image pface;
	
	public Image img;
	GObject gobject;
	public int size;
	
	public static final int space=32;
	
	public static final int show_img=1;
	public static final int destroy_img=2;
	public static final int play_sound=3;
	public static final int destroy_sound=4;
	public static final int play_animation=5;
	public static final int destroy_animation=6;
	public static final int show_eassy=7;
	public static final int change_giw_pos=8;
	public static final int change_giw_skin=9;
	public static final int change_siw_pos=10;
	public static final int change_siw_skin=11;
	public static final int change_map=12;
	public static final int sambol_type=100;
	public final int delaytime=1000;
	//data base current screen
	public  GEventNode start,end,current;
	public int timedelay=0;
	public String GprogramingName;
	int row,col;
	public GEventNode elementAt(int index){
		int i=0;
		GEventNode temp=start;
		for(i=0;temp!=null&&i<index;i++){
			temp=temp.nextNode;	
		}
		return  temp;
	}
	public void insertNode(int index,GEventNode gen){
		int i=0;
		GEventNode temp=start;
		for(i=0;temp!=null&&i<index;i++){
			temp=temp.nextNode;	
		}
		gen.nextNode=temp.nextNode;
		temp.nextNode=gen;
		size++;
	}
	public void delNode(int index){
		int i=0;
		GEventNode temp=start;
		for(i=0;temp!=null&&i<index;i++){
			temp=temp.nextNode;	
		}
		if(temp==null||temp.nextNode==null){
			
		}else{
			temp.nextNode=temp.nextNode.nextNode;
			size--;
		}
	}
	public void insertNode(GEventNode ge){
		end.nextNode=ge;
		end=ge;
		this.size++;
	}
	public void Show(){
		GEventNode temp=start;
		System.out.println("name:"+this.GprogramingName);
		while(temp!=null){ 
			System.out.println(temp.GEventNodeName+" "+temp.info+" "+temp.type+" "+temp.x+" "+temp.y+" "+temp.w+" "+temp.h);
			temp=temp.nextNode;
		}
	}
	public GPrograming(GObject gobject){
		this.size=1;
		this.gobject=gobject;
		this.GprogramingName="good";
		start=end=new GEventNode();
		start.GEventNodeName="start";
		current=null;
		this.timedelay=0;
		this.Show();
	}
	public void reSetProgram(){
		current=start.nextNode;
		timedelay=0;
	}
	public void executeProgram(){
		if(timedelay>0){
			System.out.println("timedelay"+timedelay);
			timedelay--;
		}else{
			while(current!=null&&current.type!=delaytime&&gobject.ISpause()==false){//并非延时类型
				System.out.println("Execute *type*:"+current.type+"  "+current.GEventNodeName);
				if(current.type==this.play_sound){
					AudioPlayer.player.start(current.esound.sound);
				}else if(current.type==this.destroy_sound){
					AudioPlayer.player.stop(current.esound.sound);
				}else if(current.type==this.play_animation){
					gobject.GAnimations.add(current.eanimation);
				}else if(current.type==this.show_img){
					gobject.AddGImage(current.eimg);
				}else if(current.type==this.destroy_img){
					System.out.println("disapper img:"+current.info);
					gobject.RemoveGImage(current.info);
				}else if(current.type==this.destroy_animation){
					gobject.RemoveAnimation(current.info);
				}else if(current.type==this.show_eassy){
					gobject.GameshowInfo(current.info);
				}else if(current.type==this.change_map){
					gobject.Map=current.emap;
				}else if(current.type==this.change_giw_pos){
					this.gobject.ResetGIWpos(current.x,current.y,current.w,current.h);
				}
				current=current.nextNode;
			}
			if(current!=null){
			}
		}
	}
	public void loadProgramming(String str){
		String path=System.getProperty("user.dir");
		
		path+="/Gameresource/Gprogramming/";
		File file=new File(path);
		if(file.exists()==false){
			file.mkdir();
		}
		path+=str;
		try {
			DataInputStream dis=new DataInputStream(new FileInputStream(new File(path)));
			try {
				this.GprogramingName=dis.readUTF();
				int size=dis.readInt();
				System.out.println(size);
				for(int i=0;i<size;i++){
					GEventNode gen=new GEventNode();
					gen.GEventNodeName=dis.readUTF();
					gen.type=dis.readInt();
					gen.x=dis.readInt();
					gen.y=dis.readInt();
					gen.w=dis.readInt();
					gen.h=dis.readInt();
					gen.info=dis.readUTF();
					if(gen.type==this.show_img){
						gen.eimg=new GRImage();
						gen.eimg.x=gen.x;
						gen.eimg.y=gen.y;
						gen.eimg.w=gen.w;
						gen.eimg.h=gen.h;
						gen.eimg.sourcename=gen.info;
						gen.eimg.baseimg=this.gobject.SYSDATAROOM.getResourceImage(gen.info);
					}else if(gen.type==this.play_animation){
						gen.eanimation=this.gobject.SYSDATAROOM.getResourceAnimation(gen.info);
					}else if(gen.type==change_map){
						gen.emap=this.gobject.SYSDATAROOM.getResourceMap(gen.info);
					}else if((gen.type==this.play_sound)|| (gen.type==this.destroy_sound)){
						gen.esound=new GRSound(gen.info,gobject.SYSDATAROOM.getResourceSound(gen.info));
					}
					insertNode(gen);
				}
				this.reSetProgram();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveGprogramming(String str){
		String path=System.getProperty("user.dir");
		path+="/Gameresource/Gprogramming/";
		File file=new File(path);
		if(file.exists()==false){
			file.mkdir();
		}
		path+=str;
		try {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(new File(path)));
			try {
				dos.writeUTF(this.GprogramingName);
				int allcount=size;
				dos.writeInt(allcount-1);
				for(current=start.nextNode;current!=null;current=current.nextNode){
					dos.writeUTF(current.GEventNodeName);
					dos.writeInt(current.type);
					dos.writeInt(current.x);
					dos.writeInt(current.y);
					dos.writeInt(current.w);
					dos.writeInt(current.h);
					dos.writeUTF(current.info);
				}
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("save animotion error!");
				e1.printStackTrace();
			}
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void drawSelf(Graphics g){
		g.drawRect( px-gobject.Map.movex*space,py-gobject.Map.movey*space, space,space);
	}
}