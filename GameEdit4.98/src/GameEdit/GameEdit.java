package GameEdit;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Game.GObject;
public class GameEdit {
	/**
	 * @param args
	 */ 
	GObject GOBJECT=new GObject();
	GPrograming gp;
	GameEdit(){
		    gp=new GPrograming(GOBJECT);
		    DataRoomFrame dataroomframe=new DataRoomFrame(GOBJECT.SYSDATAROOM);
		    DataRoomFrame dataroomft=new DataRoomFrame(GOBJECT.SYSDATAROOM);
		    DataRoomFrame dataroomf2=new DataRoomFrame(GOBJECT.SYSDATAROOM);
		    DataRoomFrame dataroomfmap=new DataRoomFrame(GOBJECT.SYSDATAROOM);
			JFrame jf=new JFrame("tabpanel");
			JPanel jp1,jp2,jp3,jp4,jp5,jp6;   
			ProjectAnimationPanel jap=new ProjectAnimationPanel(GOBJECT.SYSDATAROOM,dataroomframe);
			PorjectMapPanel jp=new PorjectMapPanel(GOBJECT.SYSDATAROOM,dataroomfmap);
	
			jp.mppane.setGEventFrame(new  SysEventFrame(GOBJECT,dataroomf2,gp));
			JTabbedPane tab=new JTabbedPane();
			JLabel jb1,jb2,jb3,jb4,jb5,jb6;
			jp1=new JPanel();
			jp2=new JPanel();
			jp3=new JPanel();
			jp4=new JPanel();
			jp5=new JPanel();
			jp6=new JPanel();
			
			jb1=new JLabel("one panel");
			jb2=new JLabel("two panel");
			jb3=new JLabel("three panel");
			jb4=new JLabel("four panel");
			jb5=new JLabel("five panel");
			jb6=new JLabel("sixth panel");
			
			jp1.add(jb1);
			jp2.add(jb2);
			jp3.add(jb3);
			jp4.add(jb4);
			jp5.add(jb5);
			jp6.add(jb6);
			
			jp1.setBackground(Color.blue);
			jp2.setBackground(Color.yellow);
			jp3.setBackground(Color.green);
			jp4.setBackground(Color.orange);
			jp5.setBackground(Color.pink);
			jp6.setBackground(Color.white);
			
			tab.addTab("MapEdit", null, jp,"first panel" );
			tab.addTab("Animation", null, jap,"second panel" );
			tab.addTab("DataRoom", null,dataroomft.toppanel,"third panel" );
			tab.addTab("unknow", null, jp4,"fourth panel" );
			tab.addTab("unkonw", null, jp5,"fifth panel" );
			tab.addTab("unkonw", null, jp6,"sixth panel" );

			jf.add(tab);
			jf.setBounds(200, 2, 900, 720);
			
			jf.setVisible(true);
		}
	public static void main(String[] args) {
		GameEdit gameedit=new GameEdit();
	}

}