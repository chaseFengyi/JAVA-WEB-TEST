package GameEdit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Vector;

import javax.swing.*;

import Game.GRImage;
import Game.GameSystem;
/*---------------------------------------------------------------------------------------------------
 * 
 *---------------------------------------------------------------------------------------------------*/
class MapPane extends OperatorPane{
	public Vector<GMap> maphome;
	public boolean MouseIn=false;
	public SysEventFrame gef;
	public int lastmovex=0,lastmovey=0,lastr=0,lastc=0;
	public int layer=0;
	public int EditScreenWidth;
	public int EditScreenHeight;
	
	public final Image shadow=this.loadImage("/shadow.png");
	
	public GMap currentmap;
	
	private OperatorPane op=null;
	public MapPane(Vector<GMap> maphome){
		super();
		this.maphome=maphome;
		currentmap=maphome.elementAt(0);
	}
	public void ChangCurrentMap(int i){
		if(maphome==null||maphome.size()<i){
			System.out.println("Fail to reset map");
		}else{
			currentmap=maphome.elementAt(i);
		}
	}
	public void setOperatorPane(OperatorPane op){
		this.op=op;
	}
	public void setGEventFrame(SysEventFrame gef){
		this.gef=gef;
	}
	
	public Image loadImage(String filename){
		File file;
		String str=System.getProperty("user.dir");
		str+="/System";
		file=new File(str);
		if(file.exists()==false)
			file.mkdir();
		str+=filename;
		System.out.println(str);
		return Toolkit.getDefaultToolkit().getImage(str);
	}
	public void drawShadow(Graphics g){
		width=this.getWidth();
		height=this.getHeight();
		g.drawImage(shadow,0,0,width,height,this);
	}
	
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		drawBack(g);
		movex=this.jsbr.getValue();
		movey=this.jsbc.getValue();
		currentmap.drawAllMapEdit(g,shadow,layer,this.getWidth(),this.getHeight(),jsbr.getValue(),jsbc.getValue());
		if(currentmap.layer==4){
			this.gef.gprograming.drawSelf(g);
		}
		drawCursor(g);
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		MouseIn=false;
	}
	public void mouseClicked(MouseEvent arg0){
		// TODO Auto-generated method stub
		super.mouseClicked(arg0);
		int r=arg0.getY()/space;
		int c=arg0.getX()/space;
		if(this.layer==3){
			gef.setVisible(true);
		}
		rcursor.width=op.rcursor.width;
		rcursor.height=op.rcursor.height;
		for(int j=0;j<op.rcursor.height/space;j++){
			for(int i=0;i<op.rcursor.width/space;i++){
				if(r+j+movey<maxr&&c+i+movex<maxc){
					currentmap.maptable[layer][r+j+movey][c+i+movex]=(op.rcursor.x+(op.movex+i)*space)/space+(op.rcursor.y+(j+op.movey)*space)*100/space;
					currentmap.maptable[4][r+j+movey][c+i+movex]=(/*currentmap.maptable[4][r+j+movey][c+i+movex] | */op.element[op.rcursor.y/space+op.movey+j][op.rcursor.x/space+op.movex+i]);
				}
			}
		}
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(mymouse.RightPressed){
			super.mouseDragged(arg0);
			MouseIn=true;
			lastmovex=movex;lastmovey=movey;lastr=rcursor.y/space;lastc=rcursor.x/space;
		}
		if(mymouse.LeftPressed){
			int r=arg0.getY()/space;
			int c=arg0.getX()/space;
			if(MouseIn==false){
				rcursor.width=op.rcursor.width;
				rcursor.height=op.rcursor.height;
				for(int i=0;i<op.rcursor.width/space;i++)
					for(int j=0;j<op.rcursor.height/space;j++)
						if(r+j+movey>=0&&r+j+movey<maxr&&c+i+movex<maxc&&c+i+movex>=0){
							currentmap.maptable[layer][r+j+movey][c+i+movex]=(op.rcursor.x+(op.movex+i)*space)/space+(op.rcursor.y+(j+op.movey)*space)*100/space;
							currentmap.maptable[4][r+j+movey][c+i+movex]=(currentmap.maptable[4][r+j+movey][c+i+movex] | op.element[op.rcursor.y/space+op.movey+j][op.rcursor.x/space+op.movex+i]);
						}
			}else{
				for(int i=0;i<rcursor.width/space;i++)
					for(int j=0;j<rcursor.height/space;j++)
						if(r+j+movey>=0&&r+j+movey<maxr&&c+i+movex<maxc&&c+i+movex>=0){
							currentmap.maptable[layer][r+j+movey][c+i+movex]=currentmap.maptable[layer][lastr+j+lastmovey][lastc+i+lastmovex];
							currentmap.maptable[4][r+j+movey][c+i+movex]=currentmap.maptable[4][lastr+j+lastmovey][lastc+i+lastmovex];
						}
			}
			rcursor.x=arg0.getX()/space*space;
			rcursor.y=arg0.getY()/space*space;
		}
	}
}
/*---------------------------------------------------------------------------------------------------
 * 
 *---------------------------------------------------------------------------------------------------*/
class OperatorPane extends MyScrollPane implements MouseListener,MouseMotionListener{
	protected MyMouse mymouse;
	public static final int SHOWELEMENT=1;
	public static final int OPERATORELEMENT=2;
	public static int operatorState=2;
	public int elementdir=15;
	public Image img_bg=null;
	protected int movex;
	protected int movey;
	protected int width;
	protected int height;
	protected int rank;
	protected int space;
	public Rectangle rcursor=null;
	public int maxr=50,maxc=50,element[][]=new int[50][50];
	public OperatorPane(){
		mymouse=new MyMouse();
		movex=movey=0;
		space=32;
		rank=space;
		img_bg=this.loadImage("/map_default.png");
		rcursor=new Rectangle(0,0,space,space);
		for(int i=0;i<50;i++)
			for(int j=0;j<50;j++)
				element[i][j]=elementdir;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	public Image loadImage(String filename){
		File file;
		String str=System.getProperty("user.dir");
		str+="/System";
		file=new File(str);
		if(file.exists()==false)
			file.mkdir();
		str+=filename;
		System.out.println(str);
		return Toolkit.getDefaultToolkit().getImage(str);
	}
	public void drawBack(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	public void drawImage(Graphics g){
		width=this.getWidth();
		height=this.getHeight();
		movex=this.jsbr.getValue();
		movey=this.jsbc.getValue();
		g.setColor(Color.white);
		g.drawImage(img_bg,0,0,width,height,movex*space,movey*space,movex*space+width,movey*space+height,this);
		if(this.operatorState==this.OPERATORELEMENT){
		for(int i=0;i<50&&i+movey<maxr;i++)
			for(int j=0;j<50&&j+movex<maxc;j++)
				g.drawString(element[i+movey][j+movex]+"",j*space+space/2,i*space+space/2);
		}
	}
	public void drawCursor(Graphics g){
		g.setColor(Color.white);
		g.drawRect(rcursor.x, rcursor.y, rcursor.width, rcursor.height);
		g.drawRect(rcursor.x+1, rcursor.y+1, rcursor.width-2, rcursor.height-2);
	}
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		drawBack(g);
		drawImage(g);
		drawCursor(g);
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(this.operatorState==this.operatorState){
			element[arg0.getY()/space+movey][arg0.getX()/space+movex]=elementdir;
		}
		rcursor.x=arg0.getX()/space*space;
		rcursor.y=arg0.getY()/space*space; 
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getModifiers()==16){
			mymouse.LeftPressed=true;
		}
		if(arg0.getModifiers()==4){
			mymouse.RightPressed=true;
		}
		rcursor.x=arg0.getX()/space*space;
		rcursor.y=arg0.getY()/space*space;
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mymouse.LeftPressed=mymouse.RightPressed=false;
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getX()>rcursor.x+rcursor.width){
			rcursor.width+=space;
		}
		if(rcursor.x<arg0.getX()&&arg0.getX()<rcursor.x+rcursor.width-space){
			rcursor.width-=space;
		}
		if(arg0.getY()>rcursor.y+rcursor.height){
			rcursor.height+=space;
		}
		if(rcursor.y<arg0.getY()&&arg0.getY()<rcursor.y+rcursor.height-space){
			rcursor.height-=space;
		}
	}
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
/*---------------------------------------------------------------------------------------------------
 *
 *---------------------------------------------------------------------------------------------------*/
class PorjectMapPanel extends JPanel  implements ActionListener{
	private int stat=-1;
	SysDataRoom sdr;
	Vector<GMap> maphome;
	private DataRoomFrame sdrf;
	MyScrollPanel op;//operator
	MyScrollPanel mp;//mappanel
	MapPane mppane; 
	OperatorPane oppane;
	ResizeFrame resizeframe;
	DirSetPanel dsp;
	JPanel operatpanel=new JPanel();
	JPanel tankpanel=new JPanel();
	JPanel cardpanel=new JPanel();
	
	JScrollPane listpanel=new JScrollPane();
	JList maplist=new JList();
	CardLayout card=new CardLayout();
	//GEventsOperator geo;
	SysEventFrame geventframe;
	public PorjectMapPanel(SysDataRoom sdr,DataRoomFrame sdrf){
		this.sdrf=sdrf;
		sdrf.setDecideListener(this);
		this.sdr=sdr;
		maphome=sdr.maphome;
		JMenuBar jmb=new JMenuBar();
		String[] menunames=new String []{"file","edit","mlayer"};
		String[][] itemnames=new String [][]{
				{"save","load"},
				{"newmap","changeback","changestate","useImage"},
				{"one","two","three","four","five"}
		};
		JMenu[] mapmenu=new JMenu[menunames.length];
		for(int i=0;i<menunames.length;i++){
			mapmenu[i]=new JMenu(menunames[i]);
			jmb.add(mapmenu[i]);
			for(int j=0;j<itemnames[i].length;j++){
				JMenuItem jmi=new JMenuItem(itemnames[i][j]);
				jmi.addActionListener(this);
				jmi.setActionCommand("CMD"+itemnames[i][j].toUpperCase());
				mapmenu[i].add(jmi);
			}
		}
		resizeframe=new ResizeFrame(this);
		oppane=new OperatorPane();
		op=new MyScrollPanel(oppane);
		mppane=new MapPane(maphome);

		mppane.setGEventFrame(geventframe);
		mppane.setOperatorPane(oppane);
		mp=new MyScrollPanel(mppane);
		
		mppane.currentmap=mppane.maphome.elementAt(0);
		oppane.img_bg=mppane.currentmap.baseImage;
		
		cardpanel.setLayout(card);
		cardpanel.add(mp);
		dsp=new DirSetPanel(this);
		cardpanel.add(dsp);
		tankpanel.setLayout(new BorderLayout());
		tankpanel.add(cardpanel);
		operatpanel.setLayout(new BorderLayout());
		operatpanel.add(op,BorderLayout.CENTER);
		
		
		maplist.addMouseListener(new MyMouseAdapter());
		
		this.refreshMaplist();
		listpanel.setViewportView(maplist);
		operatpanel.add(listpanel,BorderLayout.SOUTH);
		tankpanel.add(operatpanel,BorderLayout.EAST);
		this.setLayout(new BorderLayout());
		this.add(jmb,BorderLayout.NORTH);
		this.add(tankpanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	class MyMouseAdapter extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() ==2 && e.getComponent()==maplist) {
            	int si=maplist.getSelectedIndex();
            	if(si<maplist.getModel().getSize()-1){
            		mppane.ChangCurrentMap(si);
            		refreshMaplist();
            		oppane.img_bg=mppane.currentmap.baseImage;
            	}
            }
        }
	}
	public void refreshMaplist(){
		DefaultListModel dml=new DefaultListModel();
		for(int i=0;i<maphome.size();i++)
			dml.add(i,maphome.elementAt(i).mapname);
			dml.add(maphome.size(), "The last choose map                                  ");
		maplist.setModel(dml);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String cmdstr=arg0.getActionCommand();
		System.out.println(cmdstr);
		if(cmdstr.equals("CMDSAVE")){
			sdr.saveMapHome();
		}else if(cmdstr.equals("CMDLOAD")){
			sdr.loadMaphome();
		}else if(cmdstr.equals("CMDNEWMAP")){
			resizeframe.setVisible(true);
		}else if(cmdstr.equals("CMDCHANGEBACK")){
			/*JFileChooser jfc=new JFileChooser();  
	        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
	        jfc.showDialog(new JLabel(), "SELECT IMGBACK");  
	        File file=jfc.getSelectedFile();  
			oppane.img_bg=mppane.currentmap.baseImage=new ImageIcon(file.getAbsolutePath()).getImage();*/
			stat=1;
			sdrf.setVisible(true);
			sdrf.setImagestate();
		}else if(cmdstr.equals("CMDDETERMINESIZE")){
			mppane.currentmap.maxr=resizeframe.getdetermineW();
			mppane.currentmap.maxc=resizeframe.getdetermineH();
			resizeframe.setVisible(false);
		}else if(cmdstr.equals("CMDONE")){
			mppane.layer=0;
		}else if(cmdstr.equals("CMDTWO")){
			mppane.layer=1;
		}else if(cmdstr.equals("CMDTHREE")){
			mppane.layer=2;
		}else if(cmdstr.equals("CMDFOUR")){
			mppane.layer=3;
		}else if(cmdstr.equals("CMDFIVE")){
			mppane.layer=4;
		}else if(cmdstr.equals("CMDDETERMINEDIR")){
			oppane.elementdir=dsp.getDir();
		}else if(cmdstr.equals("CMDCHANGESTATE")){
			card.next(cardpanel);
		}else if(arg0.getActionCommand().equals("SYS_ADD_NODE_RESOURCE")){
			
				GRImage gri=sdrf.getDataImage();
				oppane.img_bg=mppane.currentmap.baseImage=gri.baseimg;
				mppane.currentmap.baseImageName=gri.sourcename;
				sdrf.setVisible(false);
			if(stat==2){
				mppane.currentmap.getImageMap();
			}
		}else if(arg0.getActionCommand().equals("CMDUSEIMAGE")){
			stat=2;
			sdrf.setVisible(true);
			sdrf.setImagestate();
		}
	}
}
/*class MainFrame extends JFrame{
	public MainFrame(){		
		 PorjectMapPanel jp=new PorjectMapPanel();
		
		 this.setBounds(100, 100, 500, 500);
		this.setLayout(new BorderLayout());
		this.add(jp);
		this.setVisible(true);
	}
}
public class SysMapEditProject {
	public static void main(String[] args){
		MainFrame my=new MainFrame();
	}
}*/
/*---------------------------------------------------------------------------------------------------
 * 
 *---------------------------------------------------------------------------------------------------*/
abstract class MyScrollPane extends JPanel{
	private boolean isLive;
	private Runner runner;
	public JScrollBar jsbr,jsbc;
	public void Start(){}
	public MyScrollPane(){
		isLive=false;
		runner=new Runner();
		this.refresh();
	}
	public void setScrollBar(JScrollBar jsbr,JScrollBar jsbc){
		this.jsbr=jsbr;
		this.jsbc=jsbc;
	}
	public void refresh(){
		isLive=true;
		runner.start();
	}
	public void paint(Graphics g){
		this.draw(g);
		super.repaint();
	}
	public abstract void draw(Graphics g);
	class Runner extends Thread{
		public void run(){
			while(isLive){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
		}
	}
}
/*---------------------------------------------------------------------------------------------------
 * 
 *---------------------------------------------------------------------------------------------------*/
class MyScrollPanel extends JPanel{
	public MyScrollPanel(MyScrollPane mp){
		this.setLayout(new BorderLayout());
		JScrollBar jsbc=new JScrollBar();
		JScrollBar jsbr=new JScrollBar(JScrollBar.HORIZONTAL);
		jsbr.setMaximum(60);
		jsbc.setMaximum(60);
		this.add(jsbr,BorderLayout.SOUTH);
		this.add(jsbc,BorderLayout.EAST);
		mp.setScrollBar(jsbr, jsbc);
		this.add(mp);
	}
}
/*---------------------------------------------------------------------------------------------------
 *
 *---------------------------------------------------------------------------------------------------*/
class ResizeFrame extends JFrame{
	public JTextField Wfield=new JTextField("            ");
	public JTextField Hfield=new JTextField("            ");
	public int getdetermineW(){
		String str=Wfield.getText();
		return StringtoInt(str);
	}
	public int getdetermineH(){
		String str=Hfield.getText();
		return StringtoInt(str);
	}
	public int StringtoInt(String str){
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
	public ResizeFrame(ActionListener mf){
		JLabel wlabel=new JLabel("Width  ");
		JLabel hlabel=new JLabel("Height ");
		JButton determine=new JButton("detemine");
		JPanel center=new JPanel();
		center.add(wlabel);
		center.add(Wfield);
		center.add(hlabel);
		center.add(Hfield);
		this.add(center);
		JPanel down=new JPanel();
		down.add(determine);
		this.add(down,BorderLayout.SOUTH);
		this.add(new JPanel(),BorderLayout.NORTH);
		determine.addActionListener(mf);
		determine.setActionCommand("CMDDETERMINESIZE");
		this.setBounds(100,100,200,150);
		this.setVisible(false);
	}
}
/*---------------------------------------------------------------------------------------------------
 *set direction panel for set direction in map title ͨ
 *---------------------------------------------------------------------------------------------------*/
class DirSetPanel extends JPanel { 
	String names[]=new String[]{"Canleft","CanUp","CanRight","CanDown"};
	JCheckBox jcbs[]=new JCheckBox[names.length];
	public DirSetPanel(ActionListener mf){
		for(int i=0;i<names.length;i++){
			jcbs[i]=new JCheckBox(names[i]);
			add(jcbs[i]);
		}
		JButton determine=new JButton("determine");
		determine.setActionCommand("CMDDETERMINEDIR");
		determine.addActionListener(mf);
		add(determine);
		this.setVisible(true);
	}
	public int getDir(){
		int adder=0;
		int x=1;
		for(int i=0;i<jcbs.length;i++){
			if(jcbs[i].isSelected()==true){
				adder+=x;
			}
			x*=2;
		}
		return adder;
	}
}
/*---------------------------------------------------------------------------------------------------
 *
 *---------------------------------------------------------------------------------------------------*/
class MyMouse{
	public boolean RightPressed;
	public boolean LeftPressed;
	public MyMouse(){
		RightPressed=false;
		LeftPressed=false;
	}
}