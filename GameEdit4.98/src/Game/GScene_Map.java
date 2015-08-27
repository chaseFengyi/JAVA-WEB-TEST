package Game;
import javax.swing.*;
import GameEdit.GPrograming;
import GameEdit.SysDataRoom;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
class MyPanel extends JPanel implements KeyListener,MouseListener,MouseMotionListener{
	int timecounter=0;
	int deadline=1000000; 
	GObject GOBJECT=new GObject();
	GPrograming gp;
	Smart smart=new Smart();
	public  MyPanel(){
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		gp=new GPrograming(GOBJECT);
		gp.loadProgramming("pro.txt");
		new Runner ().start();
	}
	public void drawBack(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(),this.getHeight());
	}
	public void paint(Graphics g){
		drawBack(g);
		GOBJECT.drawSelf(g);
	} 
	public  void ActorMoveTo(Smart smart,Actor actor){
		if(smart.roterear>=0){//the way is factually exist
			int presentr=actor.rect.y/GOBJECT.Map.space;
			int presentc=actor.rect.x/GOBJECT.Map.space;
			if(presentr==smart.roter[smart.roterear]&&presentc==smart.rotec[smart.roterear]){
				smart.roterear--;
			}
			GOBJECT.hero.isleft=false;
			GOBJECT.hero.isright=false;
			GOBJECT.hero.isup=false;
			GOBJECT.hero.isdown=false;
			if(smart.roterear<0) return;
			if(presentr<smart.roter[smart.roterear]){
				GOBJECT.hero.isdown=true;
				GOBJECT.hero.dir=0;
			}
			if(presentr>smart.roter[smart.roterear]){
				GOBJECT.hero.dir=3;
				GOBJECT.hero.isup=true;
			}
			if(presentc<smart.rotec[smart.roterear]){
				GOBJECT.hero.dir=2;
				GOBJECT.hero.isright=true;
			}
			if(presentc>smart.rotec[smart.roterear]){
				GOBJECT.hero.dir=1;
				GOBJECT.hero.isleft=true;
			}
		}
	}
	class Runner extends Thread{
		public void run(){
			while(true){
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
				SystemAction();//screen move
				GOBJECT.Action();
				gp.executeProgram();
				ActorMoveTo(smart, GOBJECT.hero);//actor get position
				if(++timecounter>=deadline) timecounter=0; //refresh frame counter
			}
		}
	}
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
			GOBJECT.Giw.visible=false;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_A){
			GOBJECT.hero.dir=1;
			GOBJECT.hero.isleft=true;
		}else if(arg0.getKeyCode()==KeyEvent.VK_S){
			GOBJECT.hero.isdown=true;
			GOBJECT.hero.dir=0;
		}else if(arg0.getKeyCode()==KeyEvent.VK_D){
			GOBJECT.hero.dir=2;
			GOBJECT.hero.isright=true;
		}else if(arg0.getKeyCode()==KeyEvent.VK_W){
			GOBJECT.hero.dir=3;
			GOBJECT.hero.isup=true;
		}else if(arg0.getKeyCode()==KeyEvent.VK_J){
		}
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_A){
			GOBJECT.hero.isleft=false;
			
		}else if(arg0.getKeyCode()==KeyEvent.VK_S){
			GOBJECT.hero.isdown=false;
			GOBJECT.hero.dir=0;
		}else if(arg0.getKeyCode()==KeyEvent.VK_D){
			GOBJECT.hero.dir=2;
			GOBJECT.hero.isright=false;
		}else if(arg0.getKeyCode()==KeyEvent.VK_W){
			GOBJECT.hero.dir=3;
			GOBJECT.hero.isup=false;
		}else if(arg0.getKeyCode()==KeyEvent.VK_J){
			
		}
		GSelectWindow.Action(arg0.getKeyChar());
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int aimr=(arg0.getY()+GOBJECT.Map.movey)/GOBJECT.Map.space;
		int aimc=(arg0.getX()+GOBJECT.Map.movex)/GOBJECT.Map.space;
		int startr=(GOBJECT.hero.rect.y+GOBJECT.Map.movey)/GOBJECT.Map.space;
		int startc=(GOBJECT.hero.rect.x+GOBJECT.Map.movex)/GOBJECT.Map.space;
		smart.bfs(GOBJECT.Map.maptable[0], GOBJECT.Map.maxr, GOBJECT.Map.maxc, startr, startc, aimr, aimc);
		smart.getRoad();
		this.GOBJECT.mouseAn.getLive();
	}
	public void mouseEntered(MouseEvent arg0){
	}
	public void mouseExited(MouseEvent arg0){
	}
	public void mousePressed(MouseEvent arg0){
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public void mouseDragged(MouseEvent arg0) {
	}
	public void mouseMoved(MouseEvent arg0) {
		GOBJECT.mouse.reSet(arg0.getX(),arg0.getY());
	}
	public void SystemAction(){//move screen according to mouse position
		if(GOBJECT.mouse.x<GOBJECT.Map.space&&GOBJECT.Map.movex>0){
			GOBJECT.Map.movex--;
		}
		if(GOBJECT.mouse.x>this.getWidth()-GOBJECT.Map.space&&(GOBJECT.Map.movex+GameSystem.screen_width)<GOBJECT.Map.maxc*GOBJECT.Map.space){
			GOBJECT.Map.movex++;
		}
		if(GOBJECT.mouse.y<GOBJECT.Map.space&&GOBJECT.Map.movey>0){
			GOBJECT.Map.movey--;
		}
		if(GOBJECT.mouse.y>this.getHeight()-GOBJECT.Map.space&&GOBJECT.Map.movey+GameSystem.screen_height<GOBJECT.Map.maxr*GOBJECT.Map.space){
			GOBJECT.Map.movey++;
		}
	}
}
class MyFrame extends JFrame{
	MyPanel mp;
	public MyFrame(){
		mp=new MyPanel();
		this.setTitle("Game");
		this.add(mp);
		this.addKeyListener(mp);
		this.setBounds(100,100,640,480);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
class MyTouched{
	public static boolean isNotTouched(Actor a1,Actor a2){
			boolean flag=false;
			int i;
			Rectangle r1=a1.mrect;
			Rectangle r2=a2.mrect;
			int []movex={0,r1.width,0,r1.width};
			int []movey={0,0,r1.height,r1.height};
			String [] type={"ud","lr","ul","dr"};
			for(i=0;i<4&&flag==false;i++){
				flag=(r1.x+movex[i]>=r2.x&&r1.x+movex[i]<=r2.x+r2.width&&r1.y+movey[i]>=r2.y&&r1.y+movey[i]<=r2.y+r2.height);
			}
			return !flag;
	}
}
public class GScene_Map{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame myframe=new MyFrame();
	}
}
class GameState{//control state with this static variable
	public static final int GameInShowInfo=0;
	public static final int GameInPasue=1;
	public static final int GameInBattle=2;
	public static final int GameInCheck=3;
	public static final int GameInTitle=4;
	public static int GameStat=GameInTitle;
}
