package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
/*===============================================================================================================
 * the super window of game window 
 *==============================================================================================================*/
class GWindow{
	public int x,h,w,y;
	public boolean visible;
	public int count=0;
	public int currentcount=0;
	int base=16;
	int movex[]=new int[]{160,176,160,176};
	int movey[]=new int[]{64,64,80,80};
	String str="";
	Image windowskin;
	int frame=0;
	int reframe=32;
	int every=reframe/4;
	public void setBounds(int x,int y,int w,int h){//闁跨喐鏋婚幏椋庛仛闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		if(w<4*base) x=4*base;
		if(h<4*base) h=4*base;
	}
	
	public GWindow(int x,int y,int w,int h){//闁跨喐鏋婚幏椋庛仛闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		if(w<4*base) x=4*base;
		if(h<4*base) h=4*base;
		windowskin=Toolkit.getDefaultToolkit().getImage(SystemTool.getDirectory()+"/GameResource/Graphcis/windowskin.png");
		visible=false;
	}
	public void setString(String str){
		this.str=str;
		count=str.length();
	}
	public void initWindow(String str){
		this.setString(str);
		currentcount=0;
		visible=true;
	}
	public void MydrawImage(Graphics g,Image img,int x,int y,int w,int h,int cx,int cy,int cw,int ch){
		g.drawImage(windowskin,x,y,x+w,y+h,cx,cy,cx+cw,cy+ch,null);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	}
	public void drawDevide(Graphics g){
		
	}
	public void drawSelf(Graphics g){
		if(visible==true){
		this.MydrawImage(g, windowskin,x+2,y+2,w-4, h-4, 0, 0, 127, 127);
		int i=0;
		for(i=0;i<(w-2*base)/(2*base);i++)
		this.MydrawImage(g, windowskin,x+base+i*2*base,y,2*base,base,127+base+1,0,2*base,base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.MydrawImage(g, windowskin,x+base+i*2*base,y,w-2*base-i*2*base,base,127+base+1,0,2*base,base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		
		for(i=0;i<(w-2*base)/(2*base);i++)
		this.MydrawImage(g, windowskin,x+base+i*2*base,y+h-base,2*base,base,127+base+1,base*3-1,2*base,base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.MydrawImage(g, windowskin,x+base+i*2*base,y+h-base,w-2*base-i*2*base,base,127+base+1,base*3-1,2*base,base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		
		
		for(i=0;i<(h-2*base)/(2*base);i++)
		this.MydrawImage(g, windowskin, x,y+base+i*2*base,base,2*base,128,base,base,2*base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.MydrawImage(g, windowskin, x,y+base+i*2*base,base,h-2*base-i*2*base,128,base,base,2*base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		
		for(i=0;i<(h-2*base)/(2*base);i++)
		this.MydrawImage(g, windowskin, x+w-base+1,y+base+i*2*base,base,2*base,192-base+1,base,base,2*base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.MydrawImage(g, windowskin, x+w-base+1,y+base+i*2*base,base,h-2*base-i*2*base,192-base+1,base,base,2*base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		//this.MydrawImage(g, windowskin, x+w-base,y+base+1,base,h-2*base,192-base+1,base,base-1,2*base-1);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		//闁跨喍鑼庨弬銈嗗
		this.MydrawImage(g, windowskin, x, y,base,base,128,0,base-1,base-1);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.MydrawImage(g, windowskin, x+w-base, y,base,base,192-base+1,0,base-1,base-1);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.MydrawImage(g, windowskin, x,y+h-base,base,base,128,base*3-1,base,base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
		this.MydrawImage(g, windowskin, x+w-base,y+h-base,base,base,192-base+1,base*3-1,base-1,base);//闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
	}
	}
}
/*===============================================================================================================
 * GWindow
 *==============================================================================================================*/
class GInfoWindow extends GWindow{
	int wordcount=0,size;
	public GInfoWindow(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
		size=GameSystem.fontsize;
		this.wordcount=w-4*base;
	}
	public void drawarticle(Graphics g){
		int i=0;
		this.wordcount=(w-2*base)/size;
		for(i=0;i<wordcount*5&&i<str.length()&&i<currentcount/4;i++){
			/*if(str.toCharArray()[i]=='\n'){
				continue;
			}*/
			g.drawString(str.toCharArray()[i]+"",i%wordcount*size+x+base+(w-2*base)%size/2,y+i/wordcount*size+3*base/2);
			if(y+i/wordcount*size+3*base/2>y+h-size*3/2)
				return;
		}
		if(currentcount/4<str.length()){
			currentcount++;
		}
	}
	public void drawSelf(Graphics g){
		super.drawSelf(g);
		if(visible){
			g.setFont(GameSystem.sysfont);
			g.setColor(GameSystem.sysfontcolor);
			this.drawarticle(g);
		}
	}
}
/*===============================================================================================================
 * 
 *==============================================================================================================*/
class GSelectWindow extends GWindow{
	int wordcount=0,size=12;
	private static int sc;
	private static int sr;
	private static int sw;
	private static int sh;
	static int maxr;
	static int maxc;
	int sbase=base;
	public GSelectWindow(int x, int y, int mr, int mc){
		super(0,0,0,0);
		sr=sc=0;
		sw=100;
		sh=30;
		this.x=x;
		this.y=y;
		this.w=(mc-1)*16+mc*sw+2*base;
		this.h=(mr-1)*16+mr*sh+2*base;
		maxr=mr;
		maxc=mc;
		this.visible=false;
		// TODO Auto-generated constructor stub
	}
	public static void Action(char c){
		System.out.println(c);
		if(c=='k'&&sr<maxr-1){
			sr++;
		}else if(c=='j'&&sc>0){
			sc--;
		}else if(c=='i'&&sr>0){
			sr--;
		}else if(c=='l'&&sc<maxc-1){
			sc++;
		}
	}
	public void drawCursor(Graphics g){
		//this.MydrawImage(g, windowskin,Cursor.x+base, Cursor.y+base, Cursor.width-2*base, Cursor.height-2*base,128+8,4*base+8,base,base);
		g.fillRect(sc*(sw+16)+x+base,sr*(sh+16)+y+base,sw,sh);
	}
	public void drawSelf(Graphics g){
		if(this.visible==true){
			super.drawSelf(g);
			this.drawCursor(g);
		}
	}

}