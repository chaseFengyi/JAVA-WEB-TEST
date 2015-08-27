package Game;
import java.awt.Graphics;
import java.awt.Image;
public class GRImage{
	public int x,y,w,h;
	public String sourcename;
	public Image baseimg=null;
	public GRImage(){
		sourcename="";
		x=y=w=h=10;
	}
	public GRImage(String sourcename,int index,int x,int y,int w,int h){
		this.sourcename=sourcename;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	public void setImage(String imgname,Image img){
		sourcename=imgname;
		baseimg=img;
	}
	public void setPos(int x,int y,int w,int h){
		x=x;
		y=y;
		w=w;
		h=h;
	}
	public void drawSelf(Graphics g){
		g.drawImage(baseimg,x,y,null);
	}
}