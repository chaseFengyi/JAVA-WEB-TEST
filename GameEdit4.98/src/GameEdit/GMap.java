package GameEdit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import Game.GameSystem;
import Game.SystemTool;
import sun.audio.*;
public class GMap {//the map using set
	public String mapname;
	public final static int maxlayer=4;
	public final static int space=32;
	public int maxr,maxc,layer=5,movex,movey;
	public int maptable[][][]=new int [5][50][50];//current map 1,2,3,4,5 1-3 is map 4 is event 5 is Touchable board
	public Image baseImage=null;
	public String baseImageName;
	public AudioStream sound;
	public String soundName;
	public GMap(){
		soundName=baseImageName=mapname="unknow";
		maptable=new int [5][50][50];
		movex=movey=maxr=maxc=0;
		maxc=maxr=50;
		layer=4;
		baseImage=Toolkit.getDefaultToolkit().getImage(SystemTool.getDirectory()+"map_default.png");
		for(int k=0;k<this.maxlayer;k++){
			for(int i=0;i<maxr;i++){
				for(int j=0;j<maxc;j++){
					if(k==0)
						maptable[k][i][j]=-1;
					else
						maptable[k][i][j]=-1;
				}
			}
		}
	}
	public void getImageMap(){
		int i,j;
		for(i=0;i<maxr;i++){
			for(j=0;j<maxc;j++){
				maptable[0][i][j]=i*100+j;
			}
		}
	}
	//draw map with current map
	public void drawAllMapGame(Graphics g){
		int l,i,j;
		for(l=0;l<=layer;l++){
			if(l<layer)	{ 
				for(int r=0;r<GameSystem.screen_height/space+2&&r+movey/space<maxr;r++)
					for(int c=0;c<GameSystem.screen_width/space+2&&c+movex/space<maxc;c++){
						int cr = maptable[l][movey/space+r][movex/space+c]/100,cc=maptable[l][movey/space+r][movex/space+c]%100;
						if(maptable[l][r+movey/space][c+movex/space]!=-1)
							g.drawImage(baseImage,
									c*space-movex%space,r*space-movey%space,c*space+space-movex%space,r*space+space-movey%space
									,cc*space,cr*space,(cc+1)*space,(cr+1)*space,null);
					}
			}
		}
	}
	
	public void drawAllMapEdit(Graphics g,Image shadow,int layeredit,int width,int height,int mx,int my){
		//System.out.println("mapl:"+layeredit);
		for(int l=0;l<=layeredit&&l<4;l++){
			if(l==layeredit&&layeredit!=0&&layeredit<3){
				g.drawImage(shadow,0,0,width,height,null);
			}
			movex=mx;
			movey=my;
			for(int r=0;r<height/space+1&&r+movey<maxr;r++)
				for(int c=0;c<width/space+1&&c+movex<maxc;c++){
					
					int cr = maptable[l][movey+r][movex+c]/100,cc=maptable[l][movey+r][movex+c]%100;
					
					if(maptable[l][r+movey][c+movex]!=-1)
						g.drawImage(baseImage,c*space,r*space,c*space+space,r*space+space,cc*space,cr*space,(cc+1)*space,(cr+1)*space, null);
				}
		}
		if(layeredit==4){
			g.setColor(Color.white);
			for(int r=0;r<height/space+1&&r+movey<maxr;r++)
				for(int c=0;c<width/space+1&&c+movex<maxc;c++){
					int cr = maptable[4][movey+r][movex+c]/100,cc=maptable[4][movey+r][movex+c]%100;
					g.drawRect(c*space, r*space, space, space);
					g.drawString(maptable[4][r+movey][c+movex]+"", c*space+13, r*space+13);
				}
		}
	}
	
	public void loadMap(SysDataRoom sdr,String str){
		try {
			DataInputStream dis=new DataInputStream(new FileInputStream(new File(str)));
			try {
				mapname=dis.readUTF();
				baseImageName=dis.readUTF();
				this.baseImage=sdr.getResourceImage(baseImageName);
				soundName=dis.readUTF();
				maxr=dis.readInt();
				maxc=dis.readInt();
				for(int i=0;i<maxlayer;i++){
					for(int j=0;j<maxr;j++){
						for(int k=0;k<maxc;k++){
							maptable[i][j][k]=dis.readInt();
						}
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("failed to load map!");
			}
			try {
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
	public void saveMap(String str){
		try {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(new File(str)));
			try {
				dos.writeUTF(mapname);
				dos.writeUTF(baseImageName);
				dos.writeUTF(soundName);
				dos.writeInt(maxr);
				dos.writeInt(maxc);
				for(int i=0;i<maxlayer;i++){
					for(int j=0;j<maxr;j++){
						for(int k=0;k<maxc;k++){
							dos.writeInt(maptable[i][j][k]);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
}