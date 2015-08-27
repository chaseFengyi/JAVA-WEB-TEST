package GameEdit;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import Game.GRImage;
import Game.GRSound;
import Game.GameSystem;
import sun.audio.*;
class DataSound{
	String soundname;
	AudioStream soundsource;
}
class DataImage{
	String imgname;
	Image imgsource;
}
public class SysDataRoom{//resource 
	public static Vector<DataImage> imagehome;
	public static Vector<DataSound> soundhome;
	public static Vector<GAnimation> animationhome;
	public static Vector<GMap> maphome;
	public SysDataRoom(){
		soundhome=new Vector();
		imagehome=new Vector();
		animationhome=new Vector();
		maphome=new Vector();
	}
	public static Image getResourceImage(String imgname){
		for(int i=0;i<imagehome.size();i++){
			if(imagehome.elementAt(i).imgname.equals(imgname)){
				//System.out.println("find:"+imagehome.elementAt(i).imgname);
				return imagehome.elementAt(i).imgsource;
			}
		}
		/*
		 * in this way you find a bad image
		 * */
		System.out.println(imgname+"is not in database!");
		return null;
	}
	public static AudioStream getResourceSound(String soundname){
		for(int i=0;i<soundhome.size();i++){
			if(soundhome.elementAt(i).soundname.equals(soundname))
				return soundhome.elementAt(i).soundsource;
		}
		System.out.println("read sound error!");
		return null;
	}
	public static GAnimation getResourceAnimation(String soundname){
		for(int i=0;i<animationhome.size();i++){
			if(animationhome.elementAt(i).AnmotionName.equals(soundname))
				return animationhome.elementAt(i);
		}
		System.out.println("get g animation error!");
		return null;
	}
	public static GMap getResourceMap(String soundname){
		for(int i=0;i<maphome.size();i++){
			if(maphome.elementAt(i).mapname.equals(soundname))
				return maphome.elementAt(i);
		}
		System.out.println("get g Map error!");
		return null;
	}
	/*load data*/
	public void loadFileAudio(){
		File file;
		String str=System.getProperty("user.dir");
		str+="/Gameresource/";
		str+="Audio/";
		file=new File(str);
		if(file.exists()==false||file.isDirectory()==false){
			file.mkdir();
		}
		File[] fi=file.listFiles();
			for(int i=0;i<fi.length;i++){
				 try {
						InputStream in = new FileInputStream (fi[i]);
						try {
							AudioStream as = new AudioStream (in);
							DataSound ds=new DataSound();
							ds.soundname=fi[i].getName();
							ds.soundsource=as;
							soundhome.add(ds);
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
	
	public void loadFileImage(){
		File file;
		String str=System.getProperty("user.dir");
		str+="/Gameresource/";
		str+="Graphics/";
		file=new File(str);
		if(file.exists()==false||file.isDirectory()==false){
			file.mkdir();
		}
		File[] fi=file.listFiles();
		String [] names=file.list();
		for(int i=0;i<fi.length;i++){
			Image img=Toolkit.getDefaultToolkit().getImage(fi[i].getAbsolutePath());
			DataImage di=new DataImage();
			di.imgname=names[i];
			di.imgsource=img;
			imagehome.add(di);
		}
	}
	class MyCmp implements Comparator{
		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			String stra=(String)arg0;
			String strb=(String)arg1;
			int la=stra.length();
			int lb=strb.length();
			if(la==lb){
				for(int i=0;i<la && i<lb;i++){
					char ca=stra.charAt(i);
					char cb=strb.charAt(i);
					if(ca!=cb){
						return ca-cb;
					}
				}
				return 1;
			}else{
				return la-lb;
			}
		}
	}
	
	public void loadAnimation(){
		File file;
		String str=System.getProperty("user.dir");
		str+="/Gameresource/";
		str+="Animations/";
		file=new File(str);
		if(file.exists()==false||file.isDirectory()==false){
			file.mkdir();
		}
		this.animationhome.removeAllElements();
	
		String [] filename=file.list();
		Vector<String> v=new Vector();
		
		for(int i=0;i<filename.length;i++){
			v.add(filename[i]);
		}
		Collections.sort(v,new MyCmp());
		
		for(int i=0;i<filename.length;i++){
			GAnimation an=new GAnimation(filename[i]);
			an.loadAnimotion(this,str+v.elementAt(i),0,0,800,800);
			System.out.println(an.AnmotionName);
			this.animationhome.add(an);
		}
		System.out.println(this.animationhome.size());
	}
	public void saveAnimation(){
		File file;
		String str=System.getProperty("user.dir");
		str+="/Gameresource/";
		str+="Animations/";
		file=new File(str);
		if(file.exists()==false||file.isDirectory()==false){
			file.mkdir();
		}
		for(int i=0;i<this.animationhome.size();i++){
			GAnimation an=animationhome.elementAt(i);
			System.out.println(an.AnmotionName);
			an.saveAnimotion(str+an.AnmotionName);
		}
	}
	
	public void saveMapHome(){
		File file;
		String str=System.getProperty("user.dir");
		str+="/Gameresource/";
		str+="Maps/";
		file=new File(str);
		if(file.exists()==false||file.isDirectory()==false){
			file.mkdir();
		}
		for(int i=0;i<maphome.size();i++){
			GMap ma=maphome.elementAt(i);
			System.out.println(ma.mapname);
			ma.saveMap(str+ma.mapname);
		}
	}
	
	
	public void loadMaphome(){
		File file;
		String str=System.getProperty("user.dir");
		str+="/Gameresource/";
		str+="Maps/";
		file=new File(str);
		if(file.exists()==false||file.isDirectory()==false){
			file.mkdir();
		}
		
		this.maphome.removeAllElements();
	
		String [] filename=file.list();
		Vector<String> v=new Vector();
		
		for(int i=0;i<filename.length;i++){
			v.add(filename[i]);
		}
		Collections.sort(v,new MyCmp());
		
		for(int i=0;i<filename.length;i++){
			GMap ma=new GMap();
			ma.loadMap(this,str+v.elementAt(i));
			maphome.add(ma);
		}
		//System.out.println(this.animationhome.size());
	}
	
	public static void main(String []args){
		//new GDataRoom(new Vector(),new Vector(),new Vector(),new Vector());
		
	}
}

class DataRoomFrame {
	public static int state=1;
	JTabbedPane toppanel=new JTabbedPane();
	JButton turnNext=new JButton("Decide");
	JFrame DataRoomFrame;
	JList imglist,soundlist,animationlist,maplist;
	DefaultListModel imgdlm,sounddlm,animadlm,mapdml;
	SysDataRoom GDATAROOM;
	JScrollPane imgdisplaypane;
	JScrollPane sounddisplaypane;
	JScrollPane animationdisplaypane;
	JScrollPane mapdisplaypane;
	
	JPanel topmappanel=new JPanel();
	JPanel topimgpanel=new JPanel();
	JPanel topsoundpanel=new JPanel();
	JPanel topanimationpanel=new JPanel();
	
	JPanel topmapmenupanel=new JPanel();
	ShowImagePanel topimgmenupanel=new ShowImagePanel();
	JPanel topsoundmenupanel=new JPanel();
	JPanel topanimationmenupanel=new JPanel();
	 
	/*
	 * 
	 * */
	private boolean isPlaySound=false;
	
	
	public void setAudioList(){
		int i,size=GDATAROOM.soundhome.size();
		for(i=0;i<size;i++){
			sounddlm.add(i,GDATAROOM.soundhome.elementAt(i).soundname);
		}
		sounddlm.add(i, "                                                      ");
		soundlist.setModel(sounddlm);
		soundlist.updateUI();
	}
	public void setImageList(){
		int i,size=GDATAROOM.imagehome.size();
		for(i=0;i<size;i++){
			imgdlm.add(i,GDATAROOM.imagehome.elementAt(i).imgname);
		}
		imgdlm.add(i, "                                                      ");
		imglist.setModel(imgdlm);
		imglist.updateUI();
	}
	public void setAnimationList(){
		int i,size=GDATAROOM.animationhome.size();
		for(i=0;i<size;i++){
			animadlm.add(i,GDATAROOM.animationhome.elementAt(i).AnmotionName);
		}
		animadlm.add(i, "                                                      ");
		animationlist.setModel(animadlm);
		animationlist.updateUI();
	}
	public void setMapList(){
		int i,size=GDATAROOM.maphome.size();
		for(i=0;i<size;i++){
			mapdml.add(i,GDATAROOM.maphome.elementAt(i).mapname);
		}
		mapdml.add(i, "                                                      ");
		maplist.setModel(mapdml);
		maplist.updateUI();
	}
	public void setVisible(boolean s){
		this.DataRoomFrame.setVisible(s);
	}
	public void setDecideListener(ActionListener decide){
		this.turnNext.addActionListener(decide);
	}
	public DataRoomFrame(SysDataRoom GDATAROOM){
		/*create other*/
		
		this.GDATAROOM=GDATAROOM;
		imgdisplaypane=new JScrollPane();
		sounddisplaypane=new JScrollPane();
		animationdisplaypane=new JScrollPane();
		mapdisplaypane=new JScrollPane();
		
		soundlist=new JList();
		sounddlm=new DefaultListModel();
		imglist=new JList();
		imgdlm=new DefaultListModel();
		animationlist=new JList();
		animadlm=new DefaultListModel();
		maplist=new JList();
		mapdml=new DefaultListModel();
		this.setAudioList();
		this.setImageList();
		this.setAnimationList();
		this.setMapList();
		/*
		 *crate a mouser adapter 
		 */
		doubleClick doubleclick=new doubleClick();

		soundlist.addMouseListener(doubleclick);
		imglist.addMouseListener(doubleclick);
		animationlist.addMouseListener(doubleclick);
		maplist.addMouseListener(doubleclick);
		
		sounddisplaypane.setViewportView(soundlist);
		imgdisplaypane.setViewportView(imglist);
		animationdisplaypane.setViewportView(animationlist);
		mapdisplaypane.setViewportView(maplist);
		DataRoomFrame=new JFrame();
	
		/*add action listener*/
		turnNext.setActionCommand("SYS_ADD_NODE_RESOURCE");
		
		
		topmappanel.setLayout(new BorderLayout());
		topmappanel.add(mapdisplaypane,BorderLayout.WEST);
		topmappanel.add(topmapmenupanel,BorderLayout.CENTER);
		
		toppanel.addTab("MapData",topmappanel);
		
		topanimationpanel.setLayout(new BorderLayout());
		topanimationpanel.add(animationdisplaypane,BorderLayout.WEST);
		topanimationpanel.add(topanimationmenupanel,BorderLayout.CENTER);
		
		toppanel.addTab("AnimationData",topanimationpanel);
		
		topimgpanel.setLayout(new BorderLayout());
		topimgpanel.add(imgdisplaypane,BorderLayout.WEST);
		topimgpanel.add(topimgmenupanel,BorderLayout.CENTER);
		
		toppanel.addTab("ImageData",topimgpanel);
		
		topsoundpanel.setLayout(new BorderLayout());
		topsoundpanel.add(sounddisplaypane,BorderLayout.WEST);
		topsoundpanel.add(topsoundmenupanel,BorderLayout.CENTER);
		
		toppanel.addTab("SoundData",topsoundpanel);
		
		DataRoomFrame.add(toppanel);
		DataRoomFrame.add(turnNext,BorderLayout.SOUTH);
		DataRoomFrame.setBounds(200, 200, 640, 480);
		DataRoomFrame.setVisible(false);
		
	}
    class doubleClick extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
        	if(e.getClickCount()>=1){
        		if(e.getClickCount()==1&&e.getComponent().equals(imglist)){
        			System.out.println("imglist"+"select"+imglist.getSelectedIndex());
        			Image simg=GDATAROOM.imagehome.elementAt(imglist.getSelectedIndex()).imgsource;
        			topimgmenupanel.img=simg;
        		}else if(e.getClickCount()==2&&e.getComponent().equals(soundlist)){
        			if(soundlist.getSelectedIndex()>=0&&soundlist.getSelectedIndex()<soundlist.getModel().getSize()-1){
        				isPlaySound=!isPlaySound;
        				if(isPlaySound){
        					AudioPlayer.player.start(GDATAROOM.soundhome.elementAt(soundlist.getSelectedIndex()).soundsource);
        				}else{
        					AudioPlayer.player.stop(GDATAROOM.soundhome.elementAt(soundlist.getSelectedIndex()).soundsource);
        				}
        			}
        		}else if(e.getComponent().equals(animationlist)){
 
        		}else{
        			
        		}
        	}
        }
    }
    public void setMapstate(){
    	state=0;
    	toppanel.setSelectedIndex(state);
    	toppanel.setEnabled(false);
    	toppanel.setEnabledAt(state,true);
    }
    public void setAnimationstate(){
    	state=1;
    	toppanel.setSelectedIndex(state);
    	toppanel.setEnabled(false);
    	toppanel.setEnabledAt(state,true);
    }
    public void setImagestate(){
    	state=2;
    	toppanel.setSelectedIndex(state);
    	toppanel.setEnabled(false);
    	toppanel.setEnabledAt(state,true);
    }
    public void setSoundstate(){
    	state=3;
    	toppanel.setSelectedIndex(state);
    	toppanel.setEnabled(false);
    	toppanel.setEnabledAt(state,true);
    }
    public void setDataRoomstate(){
    	state=0;
    	toppanel.setSelectedIndex(state);
    	toppanel.setEnabled(true);
    }
    public GMap getDataMap(){
    	int index=this.maplist.getSelectedIndex();
    	return this.GDATAROOM.maphome.elementAt(index);
    }
    public GAnimation getDataAnimation(){
    	int index=this.animationlist.getSelectedIndex();
    	return GDATAROOM.animationhome.elementAt(index);
    }
    public GRImage getDataImage(){
    	int index=this.imglist.getSelectedIndex();
    	GRImage gimg=new GRImage();
    	gimg.baseimg=this.GDATAROOM.imagehome.elementAt(index).imgsource;
    	gimg.sourcename=this.GDATAROOM.imagehome.elementAt(index).imgname;
    	gimg.x=topimgmenupanel.x;
    	gimg.y=topimgmenupanel.y;
    	gimg.w=255;
    	gimg.h=255;
    	return gimg;
    }
    public GRSound getDataSound(){
    	int index=this.soundlist.getSelectedIndex();
    	GRSound grs=new GRSound(GDATAROOM.soundhome.elementAt(index).soundname,GDATAROOM.soundhome.elementAt(index).soundsource);
    	return grs;
    }
}
class ShowImageFrame extends JFrame{
	public ShowImagePanel sip;
	public void setBaseImage(Image img){
		sip.img=img;
	}
	public ShowImageFrame(){
		sip=new ShowImagePanel();
		this.add(sip);
		this.setBounds(200, 300, 640, 480);
		//this.setVisible(true);
	}
}
class ShowImagePanel extends JPanel implements Runnable,MouseListener{
	  Image img=null;
	  public int x;
	  public int y;
	  public int w;
	  public int h;
	  public ShowImagePanel(){
		  this.addMouseListener(this);
		  new Thread(this).start();
	  }
	  public void setImage(Image img){
		  this.img=img;
	  }
	  public void drawCurosr(Graphics g){
		 g.drawString("x:"+x+"  y:"+y, x+20, y+20);
		 g.drawRect(x, y, 40, 40); 
		 g.drawRect(0, 0, GameSystem.screen_width, GameSystem.screen_height);
	  }
	  public void paint(Graphics g){
		  super.paint(g);//锟斤拷锟斤拷要锟侥帮拷锟斤拷去锟斤拷锟斤拷锟斤拷
		  g.drawImage(img, x, y,this);
		  this.drawCurosr(g);
	  }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		x=arg0.getX()-100;
		y=arg0.getY()-100;
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}