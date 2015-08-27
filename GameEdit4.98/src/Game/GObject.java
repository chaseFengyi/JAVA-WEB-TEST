package Game;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import GameEdit.GAnimation;
import GameEdit.GMap;
import GameEdit.SysDataRoom;
public class GObject {
	public Actor hero;
	public Vector<Enemy> Enemys;
	public MyMouse mouse;
	public GMap Map;
	public GAnimation mouseAn;
	public GInfoWindow Giw;
	public GSelectWindow Gsw;
	public Vector<GAnimation> GAnimations;
	public Vector<GRImage> GImages;
	public Vector<GRSound> GSounds;
	public SysDataRoom SYSDATAROOM=new SysDataRoom();
	public GObject(){	
		this.SYSDATAROOM.loadFileAudio();
		this.SYSDATAROOM.loadFileImage();
		this.SYSDATAROOM.loadAnimation();
		this.SYSDATAROOM.loadMaphome();
		GImages=new Vector();
		GSounds=new Vector();
		GAnimations=new Vector();
		mouse=new MyMouse();
		Enemys=new Vector();
		hero=new Actor();
		Enemy e=new Enemy();
		hero.setOther(e);
		e.setOther(hero);
		Enemys.add(e);
		Enemy e2=new Enemy();
		e2.rect.x=100;
		e2.setOther(e);
		Enemys.add(e2);
	    Giw=new GInfoWindow(30,30,560,120);
	    Giw.windowskin=this.SYSDATAROOM.getResourceImage("windowskin.png");
	    Giw.visible=false;
		Gsw=new GSelectWindow(30,200,4,2);
		Gsw.windowskin=this.SYSDATAROOM.getResourceImage("windowskin.png");
		//Gsw.visible=true;
		
		this.Map=this.SYSDATAROOM.maphome.elementAt(0);
		this.mouseAn=this.SYSDATAROOM.animationhome.elementAt(0);
	}
	public void drawActor(Graphics g,Actor actor){
		g.setColor(actor.mycolor);
		g.drawRect(actor.rect.x-Map.movex,actor.rect.y-Map.movey,actor.rect.width,actor.rect.height);
		g.drawImage(actor.imgActor, actor.rect.x-Map.movex, actor.rect.y-Map.movey, actor.rect.x-Map.movex+actor.rect.width, actor.rect.y+-Map.movey+actor.rect.height, actor.frame/3 * 32,actor.dir*32,actor.frame/3 * 32 + 32, actor.dir*32+32, null);
	}
	public void drawSelf(Graphics g){
		Map.drawAllMapGame(g);
		drawActor(g,this.hero);
		mouseAn.drawSelfGame(g,mouse.x, mouse.y);
		for(int i=0;i<Enemys.size();i++){
			drawActor(g,Enemys.elementAt(i));
		}
		for(int i=0;i<GAnimations.size();i++){
			//GAnimations.elementAt(i).drawSelf(g);
			GAnimations.elementAt(i).drawSelfGame(g, 300, 300);
		}
		for(int i=0;i<GImages.size();i++){
			GImages.elementAt(i).drawSelf(g);
		}
		Giw.drawSelf(g);
		Gsw.drawSelf(g);
	}
	public void Action(){
		hero.Action();
		for(int i=0;i<Enemys.size();i++){
			Enemys.elementAt(i).Action();
		}
	}
	public void AddGImage(GRImage gri){
		if(gri!=null)
		this.GImages.add(gri);
	}
	public void AddGAnimations(GAnimation gan){
		if(gan!=null){
			this.GAnimations.add(gan);
		}
	}
	public void RemoveGImage(String source){
		//System.out.print(arg0);
		for(int i=0;i<GImages.size();){
			GRImage gri=GImages.elementAt(i);
			if(gri.sourcename.equals(source)){
				GImages.remove(i);
			}else{
				i++;
			}
		}
	}
	public void RemoveAnimation(String source){
		for(int i=0;i<GAnimations.size();){
			GAnimation gri=GAnimations.elementAt(i);
			if(gri.AnmotionName.equals(source)){
				GAnimations.remove(i);
			}else{
				i++;
			}
		}
	}
	public void ResetGIWpos(int x,int y,int w,int h){
		Giw.setBounds(x, y, w, h);
	}
	public void ResetGIWskin(Image img){
		Giw.windowskin=img;
	}
	public void ResetGSWpos(int x,int y,int w,int h){
		Gsw.setBounds(x, y, w, h);
	}
	public void ResetGSWskin(Image img){
		Gsw.windowskin=img;
	}
	public void GameshowInfo(String str){
		Giw.initWindow(str);
	}
	public boolean ISpause(){
		return Giw.visible;
	}
}