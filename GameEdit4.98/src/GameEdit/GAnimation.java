package GameEdit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
class Flash extends Slider implements Cloneable{
	public int frame,cx,cy,cw,ch;
	public int fashSleep=1;
	public boolean isplay;
	public Flash(int i,int j,int k,int l,int x,int y,int w,int h,int cx,int cy,int cw,int ch){
		super(i,j,k,l,x,y,w-x,h-y);
		this.cx=cx;
		this.cy=cy;
		this.cw=cw;
		this.ch=ch;
		//System.out.println("hello"+i+" "+j+" "+k+" "+l+" "+x+" "+y+" "+(w-x)+" "+(h-y)+" "+cx+" "+cy+" "+cw+" "+ch);
	}
	public Flash(Rectangle rect,int x,int y,int w,int h,int cx,int cy,int cw,int ch){
		super(rect.x,rect.y,rect.width,rect.height,x,y,w-x,h-y);
		this.cx=cx;
		this.cy=cy;
		this.cw=cw;
		this.ch=ch;
		//System.out.println(i+" "+j+" "+k+" "+l+" "+x+" "+y+" "+(w-x)+" "+(h-y)+" "+cx+" "+cy+" "+cw+" "+ch);
	} 
	public void drawBack(Graphics g,Image img,int type){
		g.setColor(Color.yellow); 
		g.drawImage(img,posx, posy,posx+width,posy+height,cx,cy,cw,ch, null);
		if(type==0)
		g.drawRect(posx-1,posy-1,width+2,height+2);
	}
	public void drawBackGame(Graphics g,Image img,int x,int y){
		g.setColor(Color.yellow); 
		int xx=x-320+posx;
		int yy=y-240+posy;
		int ww=xx+width;
		int hh=yy+height;
		g.drawImage(img,xx, yy,ww,hh,cx,cy,cw,ch, null);
	}
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e){
			e.printStackTrace();
			return null;
		}
	}
}
class SAnimotion{
	Vector<Flash> flashs;
	String soundname;
	AudioStream sound;
	public SAnimotion(){
		this.flashs=new Vector();
		sound=null;
		soundname=null;
	}
}
public class GAnimation{
	public String AnmotionName;
	Vector<SAnimotion> animotions=null;
	Image baseimg=null;
	String baseimgName=null;
	int startcount=0;
	int allcount=0;
	int timecount=0;
	public boolean islive=true;
	public GAnimation(String str){
		this.AnmotionName=str;
	}
	public GAnimation(String str,int a,int b,int c,int d){
		islive=true;
		this.AnmotionName=str;
		String filename=System.getProperty("user.dir");
		filename+="/System/init_animation.png";
		this.baseimgName="init_animation.png";
		this.baseimg=baseimg=Toolkit.getDefaultToolkit().getImage(filename);	
		animotions=new Vector();
		allcount=0;
	}
	public void setBaseImage(Image baseimg){
		this.baseimg=baseimg;
	}
	public void loadAnimotion(SysDataRoom sdr,String str,int a,int b,int c,int d){
		try {
			Vector<SAnimotion> flash = new Vector();
			DataInputStream dis=new DataInputStream(new FileInputStream(new File(str)));
			try {
				this.AnmotionName=dis.readUTF();                        //read name
				this.baseimgName=dis.readUTF();                         //read imgname
				this.baseimg=sdr.getResourceImage(baseimgName);         //get img
				
				int allcount=dis.readInt();                                //read animation count
				this.allcount=allcount;
				//System.out.println(this.AnmotionName+" "+this.baseimgName+" "+allcount);
				for(int i=0;i<allcount;i++){
					SAnimotion sa=new SAnimotion();   
					sa.soundname=dis.readUTF();
					
					if(sa.soundname.equals("@_null")==false)
					sa.sound=sdr.getResourceSound(sa.soundname);
					//if(this.baseimg==null) System.out.println("NoSuchSound:"+sa.soundname);
					int count=dis.readInt();
					//System.out.print(count+" "+sa.soundname);
					
					int x = 0,y = 0,w=0,h=0,cx=0,cy=0,cw=0,ch=0;
					for(int j=0;j<count;j++){
						
						x=dis.readInt();
						y=dis.readInt();
						w=dis.readInt();
						h=dis.readInt();
						cx=dis.readInt();
						cy=dis.readInt();
						cw=dis.readInt();
						ch=dis.readInt();
						Flash f=new Flash(a,b,c,d,x,y,w+x,h+y,cx,cy,cw,ch);
						sa.flashs.add(f);
					}
					flash.add(sa);
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//System.out.println("load animotion error!");
				e1.printStackTrace();
			}
			try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.animotions=flash;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void saveAnimotion(String str){
		try {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(new File(str)));
			try {
				dos.writeUTF(this.AnmotionName);
				dos.writeUTF(this.baseimgName);
				
				
				int allcount=this.animotions.size();
				
				//System.out.println(this.AnmotionName+" "+this .baseimgName+" "+allcount);
				
				dos.writeInt(allcount);
				for(int i=0;i<allcount;i++){
					SAnimotion sa=this.animotions.elementAt(i);
					int count=sa.flashs.size();
					//System.out.print(count+" ");
					if(sa.sound==null){
						dos.writeUTF("@_null");
						//System.out.print("@_null"+" ");
					}else{
						dos.writeUTF(sa.soundname);
						//System.out.print(sa.soundname+" ");
					}
					dos.writeInt(sa.flashs.size());
					//System.out.print(sa.flashs.size()+" ");
					for(int j=0;j<count;j++){
						Flash flash=sa.flashs.elementAt(j);
						dos.writeInt(flash.posx);
						dos.writeInt(flash.posy);
						dos.writeInt(flash.width);
						dos.writeInt(flash.height);
						dos.writeInt(flash.cx);
						dos.writeInt(flash.cy);
						dos.writeInt(flash.cw);
						dos.writeInt(flash.ch);
						
						//System.out.println(flash.posx+" "+flash.posy+" "+flash.width+" "+flash.height);
					}
				}
			} catch (IOException e1) {
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
		for(int i=0;i<animotions.elementAt(timecount).flashs.size();i++){
			animotions.elementAt(timecount).flashs.elementAt(i).drawBack(g,baseimg,1);
		}
		timecount=(timecount+1)%10;
	}
	public void getLive(){
		islive=true;
	}
	public void drawSelfGame(Graphics g,int x,int y){
		if(islive){
			if(timecount+1>=this.allcount*2){
				timecount=0;
				islive=false;
			}else{
				
				for(int i=0;i<animotions.elementAt(timecount/2).flashs.size();i++){
					animotions.elementAt(timecount/2).flashs.elementAt(i).drawBackGame(g,baseimg,x,y);
				}
				if(animotions.elementAt(timecount/2).sound!=null){
					AudioPlayer.player.start(animotions.elementAt(timecount/2).sound);
				}
				timecount++;
			}
		}
	}
	public void AnimotionPlay(Graphics g,int x,int y){
		int i,j,size;
		if(this.startcount>timecount){
			timecount++;
			return ;
		}
		if(timecount<allcount&&islive==true){
			SAnimotion sa=animotions.elementAt(timecount);
			size=sa.flashs.size();
			if(sa.sound!=null){//play sound
				AudioPlayer.player.start(sa.sound);
			}
			for(i=0;i<size;i++){
				sa.flashs.elementAt(i).drawBackGame(g, baseimg,x,y);
			}
			timecount++;
			
		}else{
			timecount=0;
			islive=false;
		}
	}
}