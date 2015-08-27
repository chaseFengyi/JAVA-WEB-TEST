package GameEdit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
/*===================================================================================================
 * Every Component with use my self extends BaseComponent
 *==================================================================================================*/
abstract class BaseComponet extends JPanel{
	public int posx,posy,width,height;
	public abstract void Action(MouseState ms);
	public abstract void paint(Graphics g);
	public BaseComponet(int x,int y,int w,int h){
		posx=x;
		posy=y;
		width=w;
		height=h;
		this.setBounds(posx,posy,width,height);
	}
	public BaseComponet(){
		posx=posy=0;
		width=height=10;
		this.setBounds(posx,posy,width,height);
	}
	public boolean PointInRect(int x,int y,int rx,int ry,int rw,int rh){
		return ((x>=rx&&x<=rx+rw)&&(y>=ry&&y<=ry+rh));
	}
}
/*===================================================================================================
 *
 *=================================================================================================*/
class Touche{
	public static boolean PointInRect(int x,int y,int rx,int ry,int rw,int rh){
		return ((x>=rx&&x<=rx+rw)&&(y>=ry&&y<=ry+rh));
	}
	public static boolean RectInRect(int cx,int cy,int cw,int ch,int rx,int ry,int rw,int rh){
		int movex[]={-1,0,1,0};
		int movey[]={0,-1,0,1};
		boolean flag=true;
		for(int i=0;i<4&&flag;i++){
			int x=cx+movex[i]*cw/2;
			int y=cy+movey[i]*ch/2;
			flag=PointInRect(x,y,rx,ry,rw,rh);
		}
		return flag;
	}
}
/*======================================================================================================
 * Main dealing panel BaseComponet extend JPanel
 *=====================================================================================================*/
class BasePanel extends JPanel implements MouseListener,MouseMotionListener,MouseWheelListener{
	//base component
	Vector<BaseComponet> BaseComponets;
	//Data
	Vector<GAnimation> FramesHome;
	//mouse state
	public MouseState ms;
	//current animation is play
	public boolean ispaly=false;
	//frame operator rcursor
	public Rectangle rcursor=new Rectangle(0,400,192,192);
	public Rectangle rcursor2=new Rectangle(100,100,192,192);
	//base image
	public Image img=null;
	//img_shadow
	public Image shadow=null;
	//row slider rectangle
	public Rectangle rsr=new Rectangle(0,400+196,100,30);
	//row slider box
	public Rectangle rsrbb=new Rectangle(0,400+196,670,30);
	
	public int opscntl=6;
	//instance slider
	Slider sr=new Slider(rsrbb.x,rsrbb.y,rsrbb.width,rsrbb.height,rsr.x,rsr.y,rsr.width,rsr.height);
	
	//as kind as rsr
	public Rectangle rsc=new Rectangle(640,400,30,35);
	public Rectangle rscbb=new Rectangle(640,400,30,200);
	Slider sc=new Slider(rscbb.x,rscbb.y,rscbb.width,rscbb.height,rsc.x,rsc.y,rsc.width,rsc.height);
	
	//visual panel rectangle
	public Rectangle op=new Rectangle(0,400,640,200);
	//visual panel rectangle
	public Rectangle showpane=new Rectangle(0,0,640,400);
	//
	public Rectangle rshowpane=new Rectangle(640,0,30,40);
	public Rectangle rshowpanebox=new Rectangle(640,0,30,400);
	
	Slider showsc=new Slider(rshowpanebox.x,rshowpanebox.y,rshowpanebox.width,rshowpanebox.height,rshowpane.x,rshowpane.y,rshowpane.width,rshowpane.height);
	
	//frame panel rectangle
	public Rectangle framepane=new Rectangle(10,10,100,30);
	
	//box : control ground of rucrsor
	Rectangle rcursor_box=new Rectangle(op.x,op.y,0,0);
	public JList arrangecount;//user for list panel
	//use for draw Small picture state
	public int SmallerN=10;
	public int SmallerPicX=673,SmallerPicY=0,SmallerPicWid=126,SmallerPicHei=90;
	public Vector<Image> SmallerImages=null;
	public Vector<Graphics> SmallerGs=null;
	
	//Construct function
	public BasePanel(int x,int y,int w,int h,JList arrangecount,Vector<GAnimation> Animationhome){
		
		ms=new MouseState();

		this.setBounds(x,y,w,h);
		this.arrangecount=arrangecount;
		
		BaseComponets=new Vector();
		//get Data Pointer From GDataFrame
		
		this.FramesHome=Animationhome;
		
		img=this.FramesHome.elementAt(0).baseimg;
		shadow=this.loadImage("/shadow.png");
		//create off graphics and off image
		SmallerImages=new Vector();
		SmallerGs=new Vector();
		for(int i=0;i<SmallerN;i++){
			SmallerImages.add(new BufferedImage(640,400,BufferedImage.SCALE_REPLICATE));
			SmallerGs.add(SmallerImages.elementAt(i).getGraphics());
		}
		//base add base component
		this.BaseaddComponet(sc);
		this.BaseaddComponet(sr);
		this.BaseaddComponet(showsc);
		
		//repair position for each component we add
		for(int i=0;i<BaseComponets.size();i++){
			BaseComponets.elementAt(i).posx+=x;
			BaseComponets.elementAt(i).posy+=y;
		}
		//add Listener
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		
		//Start thread
		new Runner().start();
	}
	/*
	 * draw thing we need
	 */
	public void drawFramePane(Graphics g){
		g.setColor(Color.blue);
		g.drawRect(framepane.x, framepane.y, framepane.width, framepane.height);
		g.setColor(Color.red);
		g.drawString("frame:"+showsc.movey,framepane.x+10, framepane.y+10);
	}
	public void drawShowPane(Graphics g){
		g.setColor(Color.black);
		g.fillRect(showpane.x, showpane.y, showpane.width, showpane.height);
		g.setColor(Color.gray);
		g.drawLine(showpane.x,(showpane.x+showpane.height)/2,showpane.x+showpane.width,(showpane.x+showpane.height)/2);
		g.drawLine((showpane.x+showpane.width)/2, showpane.y,(showpane.x+showpane.width)/2,showpane.y+showpane.height);
		g.drawRect(showpane.x,showpane.y,showpane.width,showpane.height);
	}
	public void drawOperator(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(op.x, op.y, op.width, op.height);
		g.drawImage(img, op.x, op.y,op.x+op.width,op.y+op.height,(sr.movex*opscntl)/rcursor.width*rcursor.width,sc.movey*opscntl/rcursor.height*rcursor.height,sr.movex*opscntl/rcursor.width*rcursor.width+op.width,sc.movey*opscntl/rcursor.height*rcursor.height+op.height,this);
	}
	public void drawSCursor(Graphics g){
		g.setColor(Color.red);
		g.drawRect(rcursor.x,rcursor.y,rcursor.width,rcursor.height);
	}
	public  void drawSCursors(Graphics g){
		g.drawRect(rcursor.x,rcursor.y,rcursor.width,rcursor.height);
	}
	public void drawSCursor2(Graphics g){
		g.setColor(Color.GREEN);
		g.drawRect(rcursor2.x,rcursor2.y,rcursor2.width,rcursor2.height);
	}
	//something was green
	public void drawBack(Graphics g){
		g.setColor(Color.green);
		g.fillRect(this.getX(), getY(), getWidth(),this.getHeight());
	}
	public void show(Graphics g){
		drawOperator(g);
		
		drawSCursor(g);
		drawSCursor2(g);
		drawSCursors(g);
		drawShowPane(g);
	}
	//draw Current Frame and component
	public void drawFrame(Graphics g){
		int player=showsc.movey/9;
		int indexcount=arrangecount.getSelectedIndex();
		if(indexcount<0) indexcount=0;
		GAnimation Frames=FramesHome.elementAt(indexcount);

		for(int i=0;i<BaseComponets.size();i++){
			BaseComponets.elementAt(i).paint(g);
			BaseComponets.elementAt(i).Action(ms);
		}
		if(player>0){
			for(int i=0;player -1 <Frames.animotions.size()&&i<Frames.animotions.elementAt(player - 1).flashs.size();i++){
				Frames.animotions.elementAt(player-1).flashs.elementAt(i).drawBack(g,Frames.baseimg,0);
			}
			g.drawImage(shadow,0, 0, showpane.width,showpane.height,this);
		}
		
		for(int i=0;player<Frames.animotions.size()&&i<Frames.animotions.elementAt(player).flashs.size();i++){
			Frames.animotions.elementAt(player).flashs.elementAt(i).drawBack(g,Frames.baseimg,0);
			Frames.animotions.elementAt(player).flashs.elementAt(i).Action(ms);
		}
		int splayer=player-1;
		for(int i=0;i<SmallerN;i++){
			SmallerGs.elementAt(i).clearRect(0, 0, 1000, 1000);
			if(splayer+i>=0&&splayer+i<Frames.animotions.size()){
				for(int j=0;j<Frames.animotions.elementAt(splayer+i).flashs.size();j++){
				   Frames.animotions.elementAt(splayer+i).flashs.elementAt(j).drawBack(SmallerGs.elementAt(i),Frames.baseimg,1);
				}
			}
		}
		for(int i=0;i<SmallerN;i++){
			g.setClip(SmallerPicX, SmallerPicY,SmallerPicWid,SmallerN*SmallerPicHei);
			g.drawImage(SmallerImages.elementAt(i),SmallerPicX,i*SmallerPicHei+i-showsc.movey%9*SmallerN,SmallerPicWid,SmallerPicHei,this);
		}
	}
	public void paint(Graphics g){
		drawBack(g);
		show(g);
		this.drawFrame(g);
		drawFramePane(g);
	}
	/*
	 * operation for base panel 
	 */
	public void BaseaddComponet(BaseComponet bc){
		 BaseComponets.add(bc);
	}
	public void BaseremoveComponet(BaseComponet bc){
		 BaseComponets.remove(bc);
	}
	//to refresh every thing we have pained
	public class Runner extends Thread{
		public void run(){
			while(true){
				try {
					Thread.sleep(25);
					if(ispaly==true){
						int indexcount=arrangecount.getSelectedIndex();
						
						if(indexcount<0) indexcount=0;
						
						GAnimation Frames=FramesHome.elementAt(indexcount);
						//update showsc
						if(showsc.movey<Frames.animotions.size()*9)
							showsc.posy=showsc.movey=(showsc.movey+1);
						else{
							ispaly=false;
							showsc.movey=0;
							showsc.posy=0;
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("mouse:"+arg0.getModifiers());
		//can get Modifiers 4 or 16
		//set cursor position
		int currentx=arg0.getX();
		int currenty=arg0.getY();
		if(Touche.PointInRect(currentx, currenty, op.x, op.y, op.x+op.width,op.y+op.height)){//
			
			rcursor.x=op.x+(currentx-op.x)/rcursor.width*rcursor.width;
			rcursor.y=op.y+(currenty-op.y)/rcursor.height*rcursor.height;
			/*int xlu=currentx-rcursor.width/2,ylu=currenty-rcursor.height/2;
			int xrd=currentx+rcursor.width/2,yrd=currenty+rcursor.height/2;
			rcursor.x=currentx-rcursor.width/2;
			rcursor.y=currenty-rcursor.height/2;
			if(xlu<op.x) rcursor.x=op.x;
			if(ylu<op.y) rcursor.y=op.y;
			if(xrd>op.x+op.width) rcursor.x=op.width+op.x-rcursor.width;
			if(yrd>op.y+op.height) rcursor.y=op.y+op.height-rcursor.height;*/
		}
		int indexcount=arrangecount.getSelectedIndex();
		if(indexcount<0) indexcount=0;
		
		GAnimation Frames=FramesHome.elementAt(indexcount);
		img=Frames.baseimg;
		
		while(Frames.animotions.size()<showsc.movey/9){
			Frames.animotions.add(new SAnimotion());
		}
		
		if(arg0.getModifiers()==16){
			if(Touche.PointInRect(currentx, currenty, showpane.x, showpane.y, showpane.x+showpane.width, showpane.y+showpane.height)){
				int xlu=currentx-rcursor2.width/2,ylu=currenty-rcursor2.height/2;
				int xrd=currentx+rcursor2.width/2,yrd=currenty+rcursor2.height/2;
				rcursor2.x=currentx-rcursor2.width/2;
				rcursor2.y=currenty-rcursor2.height/2;
				if(xlu<showpane.x) rcursor2.x=showpane.x;
				if(ylu<showpane.y) rcursor2.y=showpane.y;
				if(xrd>showpane.x+showpane.width) rcursor2.x=showpane.width+showpane.x-rcursor2.width;
				if(yrd>showpane.y+showpane.height) rcursor2.y=showpane.y+showpane.height-rcursor2.height;
				
				Flash ff=new Flash(showpane.x,showpane.y,showpane.x+showpane.width,showpane.y+showpane.height,
						rcursor2.x,rcursor2.y,rcursor2.x+rcursor.width,rcursor2.y+rcursor.height,
						(rcursor.x-op.x)+(sr.movex*opscntl)/rcursor.width*rcursor.width,(rcursor.y-op.y)+sc.movey*opscntl/rcursor.height*rcursor.height,(rcursor.x-op.x)+sr.movex*opscntl/rcursor.width*rcursor.width+rcursor.width,(rcursor.y-op.y)+sc.movey*opscntl/rcursor.height*rcursor.height+rcursor.height);
						//sr.movex*opscntl+rcursor.x-op.x,sc.movey*opscntl+rcursor.y-(op.y),sr.movex*opscntl+rcursor.x+rcursor.width-op.x,sc.movey*opscntl+rcursor.y+rcursor.height-(op.y));				
				while(Frames.animotions.size()<40)
					Frames.animotions.add(new SAnimotion());
				Frames.animotions.elementAt(showsc.movey/9).flashs.add(ff);
			}
		}else{
			SAnimotion sa=Frames.animotions.elementAt(showsc.movey/9);
			for(int i=0;i<sa.flashs.size();){
				int x=sa.flashs.elementAt(i).posx;
				int y=sa.flashs.elementAt(i).posy;
				int w=sa.flashs.elementAt(i).width;
				int h=sa.flashs.elementAt(i).height;
				if(Touche.PointInRect(currentx, currenty,x,y,w,h))
					Frames.animotions.elementAt(showsc.movey/9).flashs.removeElementAt(i);
			else
				i++;
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0)  {	
	}
	public void mousePressed(MouseEvent arg0) {
		ms.isPress=true;
		ms.mousetype=arg0.getModifiers();
		ms.px=ms.x=arg0.getX();
		ms.py=ms.y=arg0.getY();
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		ms.isPress=false;
		ms.isDraged=false;
		ms.x=arg0.getX();
		ms.y=arg0.getY();
		int indexcount=arrangecount.getSelectedIndex();
		if(indexcount<0) indexcount=0;
		
		GAnimation Frames=FramesHome.elementAt(indexcount);
		img=Frames.baseimg;
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("d: "+arg0.getX()+" "+arg0.getY());
		ms.isDraged=true;
		ms.x=arg0.getX();
		ms.y=arg0.getY();
		//System.out.println(arg0.getX()+" , "+arg0.getY());
	}
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		ms.x=arg0.getX();
		ms.y=arg0.getY();
	}
	public Image loadImage(String filename){
		File file;
		String str=System.getProperty("user.dir");
		file=new File(str);
		if(file.exists()==false)
			file.mkdir();
		str+="/Gameresource/Graphics/";
		str+=filename;
		return Toolkit.getDefaultToolkit().getImage(str);
	}
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getPreciseWheelRotation()==1){
			if((ms.isPress==false || ms.mousetype==4)&&rcursor.width>2){
				rcursor.width--;
				rcursor2.width--;
			}
			if((ms.isPress==false || ms.mousetype==16)&&rcursor.height>2){
				rcursor.height--;
				rcursor2.height--;
			}
		}
		if(arg0.getPreciseWheelRotation()==-1&&rcursor.width<193){
			if((ms.isPress==false || ms.mousetype==4)&&rcursor.width<193){
				rcursor.width++;
				rcursor2.width++;
			}
			if((ms.isPress==false || ms.mousetype==16)&&rcursor.width<193){
				rcursor2.height++;
				rcursor.height++;
			}
		}
	}
}
/*=============================================================================================
 *
 *=============================================================================================*/
class MouseState{
	public boolean isClick,isPress,isEnter,isReless,isDraged;
	public int x,y,px,py;
	public int mousetype;
	public MouseState(){
		isClick=isPress=isEnter=isReless=isDraged=false;
		x=0;
		y=0;
	}
}
