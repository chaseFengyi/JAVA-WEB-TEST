package GameEdit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import java.util.jar.JarInputStream;

import sun.audio.*;

import javax.swing.*;

import Game.GRImage;
import Game.GRSound;
/*==========================================================================
 * BaseComponet
 *==========================================================================*/
class Slider extends BaseComponet{
	public int movex=0,movey=0;
	private int dx=-1,dy=-1;
	private boolean aimed=false;
	public Rectangle borderbox;
	public Color Scolor;
	public Color Bcolor;
	public Slider(){
		super();
		Scolor=Color.white;
		Bcolor=Color.blue;
	} 
	public Slider(int x,int y,int w,int h){
		super(x,y,w,h);
		Scolor=Color.white;
		Bcolor=Color.blue;
	}
	public Slider(int bx,int by,int bw,int bh,int x,int y,int w,int h){
		super(x,y,w,h);
		setBorderBox(bx,by,bw,bh);
	}
	public void setBorderBox(int x,int y,int w,int h){
		this.borderbox=new Rectangle(x,y,w,h);
		Scolor=Color.GRAY;
		Bcolor=Color.CYAN;
	}
	public void drawBorderBox(Graphics g){
		g.setColor(this.getBackground());
		g.fillRect(borderbox.x, borderbox.y, borderbox.width, borderbox.height);
		g.setColor(Scolor);
		g.draw3DRect(borderbox.x, borderbox.y, borderbox.width, borderbox.height,false);
		g.draw3DRect(borderbox.x+1,borderbox.y+1,borderbox.width-2,borderbox.height-2,false);
	}
	public void drawBack(Graphics g){
		g.setColor(Bcolor);
		g.fill3DRect(posx+2, posy+2, width-3,height-3,false);
	}
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		drawBorderBox(g);
		drawBack(g);
	}
	public void Action(MouseState ms) {
		// TODO Auto-generated method stub
		if(ms.isPress){
			if(aimed==false&&this.PointInRect(ms.px, ms.py, posx, posy, width, height)){
				aimed=true;
				dx=ms.px-posx;
				dy=ms.py-posy;
			}
		}else{
			aimed=false;
		}
		if((ms.isDraged&&aimed)){
			int nx=ms.x-dx;
			int ny=ms.y-dy;
			if(nx+width>=borderbox.x+borderbox.width){
				posx=borderbox.x+borderbox.width-width;
			}else if(nx<=borderbox.x){
				posx=borderbox.x;
			}else{
				posx=ms.x-dx;
			}
			if(ny+height>=borderbox.y+borderbox.height){
				posy=borderbox.y+borderbox.height-height;
			}else if(ny<=borderbox.y){
				posy=borderbox.y;
			}else{
				posy=ms.y-dy;
			}
			movex=posx-borderbox.x;
			movey=posy-borderbox.y;
		}
	}
}
class SelectWindow extends BaseComponet{
	Color bc=Color.green;
	public void Action(MouseState ms){
		if(this.PointInRect(ms.x, ms.y, posx, posy, width, height)){
			bc=Color.orange;
		}else{
			bc=Color.green;
		}
	}
	public SelectWindow(int i, int j, int k, int l) {
		super(i,j,k,l);
	}
	public void drawBack(Graphics g){
		g.setColor(bc);
		g.fillRect(posx, posy, width,height);
	}
	public void paint(Graphics g){
		drawBack(g);
	}
}
class AutoSetFrame extends JFrame{
	MyTextField tx,ty,tw,th;
	JButton determine;
	public AutoSetFrame(ActionListener ac){
		JLabel Warnning;
		JPanel jp=new JPanel();
		tx=new MyTextField("start:");
		ty=new MyTextField("widthres:");
		tw=new MyTextField("heightres");
		th=new MyTextField("count:");
		jp.add(tx);
		jp.add(ty);
		jp.add(tw);
		jp.add(th);
		this.add(jp);
		determine=new JButton("determine");
		determine.setActionCommand("SYS_AUTO_SET_DETEMINE");
		determine.addActionListener(ac);
		this.add(determine,BorderLayout.SOUTH);
		this.setBounds(100, 200, 100, 200);
		this.setResizable(false);
		this.setVisible(false);
	}
	public int GetIntValue(String str){
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
/*
 * deal with event happened
 */
class AnimationPanel extends JPanel implements ActionListener{
	public Vector<GAnimation> animationhome;
	public DataRoomFrame drf;
	BasePanel basepanel=null;
	int cnt=10;
	String []cmdname=new String []{"play","copy","past","delete","aslast"};
	JButton cmds[]=new JButton[cnt];
	DateRomPanel drp=new DateRomPanel(this,new doubleClick());
	JButton create=new JButton("SBack");
	JButton play=new JButton("CSound");
	
	JButton autoset =new JButton("AutoSet");
	
	public AutoSetFrame asf;
	public AnimationPanel(Vector<GAnimation> an){
		this.setLayout(null);
		
		asf=new AutoSetFrame(this);
	
		this.animationhome=an;
		basepanel=new BasePanel(0,0,800,660,drp.frameList,an);
		for(int i=0;i<cmdname.length&&i<cnt;i++){
			cmds[i]=new JButton(cmdname[i]);
			cmds[i].addActionListener(this);
			cmds[i].setActionCommand(cmdname[i]);
			cmds[i].setBounds(800,0+i*32,80,30);
			this.add(cmds[i]);
		}
		create.setActionCommand("SYS_CREATE_AN");
		create.addActionListener(this);
		create.setBounds(800,410,80,32);
		play.setActionCommand("SYS_CHANGE_SOUND");
		play.addActionListener(this);
		play.setBounds(800,442,80,32);
		
		autoset.addActionListener(this);
		autoset.setActionCommand("SYS_AUTO_SET");
		autoset.setBounds(800,474,80,32);
		
		this.add(create);
		this.add(play);
		this.add(autoset);
		
		drp.setBounds(800,200,80,200);
		this.add(drp);
		this.add(basepanel);
	}
	public void setDataRoomFrame(DataRoomFrame drf){
		this.drf=drf;
		drf.setDecideListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getActionCommand());
		int player=basepanel.showsc.movey/9;
		int indexcount=drp.frameList.getSelectedIndex();
		if(indexcount<0) indexcount=0;
		GAnimation Frames=basepanel.FramesHome.elementAt(indexcount);
		
		if(player<Frames.animotions.size()&&arg0.getActionCommand()=="delete"){
			
			Frames.animotions.remove(player); 
		
		}else if(player+1<Frames.animotions.size()&&arg0.getActionCommand()=="aslast"){
			int size=Frames.animotions.elementAt(player).flashs.size();
			basepanel.showsc.movey+=9;
			basepanel.showsc.posy=basepanel.showsc.movey;
			//Frames.set(player+1,(Vector)Frames.animotions.elementAt(player).clone());
		}else if(arg0.getActionCommand().equals("SYS_CREATE_AN")){
			drf.setImagestate();
	        drf.setVisible(true);
		}else if(arg0.getActionCommand().equals("SYS_CHANGE_SOUND")){
			drf.setSoundstate();
	        drf.setVisible(true);
		}else if(arg0.getActionCommand().equals("SYS_DECIDE_ANIMATION_NAME")){
			drp.dlm.set(player,drp.nf.getNewName());
			drp.frameList.updateUI();
		}else if(arg0.getActionCommand().equals("SYS_ADD_NODE_RESOURCE")){
			if(drf.state==2){
				GRImage gri=drf.getDataImage();
				Frames.baseimg=gri.baseimg;
				Frames.baseimgName=gri.sourcename;
				this.basepanel.img=gri.baseimg;
			}else if(drf.state==3){
				GRSound grs=drf.getDataSound();
				Frames.animotions.elementAt(player).sound=grs.sound;
				Frames.animotions.elementAt(player).soundname=grs.sourcename;
			}
			drf.setVisible(false);
		}else if(arg0.getActionCommand().equals("SYS_AUTO_SET")){
			//asf.setVisible(true);
		}else if(arg0.getActionCommand().equals("SYS_AUTO_SET_DETEMINE")){
			/*int s=-1,w=-1,h=-1,c=-1;
			s=asf.getIx();
			w=asf.getIy();
			h=asf.getIw();
			c=asf.getIh();
			int xx=this.basepanel.op.x;
			int yy=this.basepanel.op.y;
			for(int i=0;i<c&&i<Frames.animotions.size();i++){
				int row=c/w;
				int col=c%w;
			}
			asf.setVisible(false);*/
		}
	}
    class doubleClick extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount()==1){
            	int index=drp.frameList.getSelectedIndex();
            	System.out.println(index);
            	if(index<basepanel.FramesHome.size()&&index>=0){
            		basepanel.img=basepanel.FramesHome.elementAt(index).baseimg;
            	}
            }
        }
    }
}
/*Input Name for Animation*/
class NameFrame extends JFrame{//get information for each Animation 
	JTextField jft=new JTextField("                   ");
	private JButton decidebutton=null;
	boolean visible;
	public NameFrame(ActionListener ac){
		JLabel jb=new JLabel("Name");
		JPanel jp=new JPanel();
		decidebutton=new JButton("SYS_decide");
		decidebutton.addActionListener(ac);
		decidebutton.setActionCommand("SYS_DECIDE_ANIMATION_NAME");
		jp.add(jb);
		jp.add(jft);
		this.add(jp,BorderLayout.CENTER);
		this.add(decidebutton,BorderLayout.SOUTH);
		this.setBounds(100, 100, 200, 100);
		visible=false;
		this.setVisible(false);
	}
	public void turnStat(){
		visible=!visible;
		setVisible(visible);
	}
	public String getNewName(){
		String name=jft.getText();
		System.out.println(jft.getText());
		return name;
	}
}
class DateRomPanel extends JScrollPane{
	NameFrame nf=null;
	public JList frameList;
	public DefaultListModel dlm;
	String [] names;
	public DateRomPanel(ActionListener ac,MouseListener ml){
		names=new String[30];
		dlm=new DefaultListModel();
		for(int i=0;i<names.length;i++){
			names[i]=i+"";
			dlm.add(i,names[i]);
		}
		frameList=new JList();
		nf=new NameFrame(ac);
		frameList.addMouseListener(ml);
		frameList.addMouseListener(new doubleClick());
		frameList.setModel(dlm);
		
		this.setViewportView(frameList);
	}
	public void UpdateList(Vector<String> name){
		dlm.removeAllElements();
		for(int i=0;i<name.size();i++){
			dlm.add(i,name.elementAt(i));
		}
		frameList.setModel(dlm);
		frameList.updateUI();
	}
	
    class doubleClick extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() ==2) {
            	nf.turnStat();
            }
        }
    }
}
class ProjectAnimationPanel  extends JPanel implements ActionListener{
	public SysDataRoom DATAROOM;
	public int width=906,height=710,posx=150,posy=20;
	JMenuBar jmb=new JMenuBar();
	AnimationPanel mp;
	public void setDataRoomFrame(DataRoomFrame drf){
		this.mp.setDataRoomFrame(drf);
	}
	public ProjectAnimationPanel(SysDataRoom sdr,DataRoomFrame drf){
		JMenu filemenu=new JMenu("fileoperator");
		String[] menuitemname=new String[]{"load","save","play"};
		JMenuItem []filemenuitem=new JMenuItem[10];
		for(int i=0;i<menuitemname.length;i++){
			filemenuitem[i]=new JMenuItem(menuitemname[i]);
			filemenuitem[i].addActionListener(this);
			filemenuitem[i].setActionCommand(menuitemname[i]);
			filemenu.add(filemenuitem[i]);
		}
		jmb.add(filemenu);
		
		this.DATAROOM=sdr;
		mp=new AnimationPanel(this.DATAROOM.animationhome);
		this.setDataRoomFrame(drf);
		this.setLayout(new BorderLayout());
		this.add(jmb,BorderLayout.NORTH);
		this.add(mp,BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand()=="load"){
			System.out.println("load");
			this.DATAROOM.loadAnimation();
			Vector<String> namelist=new Vector();
			for(int i=0;i<DATAROOM.animationhome.size();i++){
				namelist.add(DATAROOM.animationhome.elementAt(i).AnmotionName);
			}
			this.mp.drp.UpdateList(namelist);
		}
		if(arg0.getActionCommand()=="save"){
			this.DATAROOM.saveAnimation();
		}
		if(arg0.getActionCommand()=="play"){
			mp.basepanel.ispaly=true;
		}
	}
}