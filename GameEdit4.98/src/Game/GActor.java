package Game;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;
//���˹�
class Actor{
	public boolean isfire;
	public boolean isleft;
	public boolean isright;
	public boolean isup;
	public boolean isdown;
	public Rectangle rect,mrect;
	Actor r2;
	public int speed;
	public Image imgActor;
	public int dir,frame;
	public Color mycolor;
	public int toucheddir=-1;
	public Actor(){
		mycolor=Color.gray;
		rect=new Rectangle();
		mrect=new Rectangle();
		isfire=isright=isup=isdown=isleft=false;
		dir=frame=rect.x=rect.y=0;
		speed=2;
		imgActor=Toolkit.getDefaultToolkit().getImage(SystemTool.getDirectory()+"Graphics/actor_default.png");
		rect.width=rect.height=32;
	}
	public void setOther(Actor r2){
		this.r2=r2;
	}
	public void moveLeft(){
		mrect=rect;
		mrect.x-=speed;
		if(MyTouched.isNotTouched(this, r2))
			rect=mrect;
		else{
			if(r2.dir==2){
				rect.x=rect.x-(rect.x-(r2.rect.x+r2.rect.width))/2+1;
			}else{
				rect.x=(r2.rect.x+r2.rect.width)+1;
			}
		}
	}
	public void moveRight(){
		mrect=rect;
		mrect.x+=speed;
		if(MyTouched.isNotTouched(this, r2))
			rect=mrect;
		else{
			if(r2.dir==1){
				rect.x=rect.x+(r2.rect.x-(rect.x+rect.width))/2-1;
			}else{
				rect.x=(r2.rect.x-rect.width)-1;
			}
		}
	}
	public void moveUp(){
		mrect=rect;
		mrect.y-=speed;
		if(MyTouched.isNotTouched(this, r2))
			rect=mrect;
		else{
			if(r2.dir==0){
				rect.y=rect.y-(rect.y-(r2.rect.y+r2.rect.height))/2+1;
			}else{
				rect.y=r2.rect.y+r2.rect.height-1;
			}
		}
	}
	public void moveDown(){
		mrect=rect;
		mrect.y+=speed;
		if(MyTouched.isNotTouched(this, r2))
			rect=mrect;
		else{
			if(r2.dir==3){
				rect.y=rect.y+(r2.rect.y-(rect.y+rect.height))/2+1;
			}else{
				rect.y=r2.rect.y-rect.height+1;
			}
		}
	}
	public void Action(){
		if(isdown||isleft||isright||isup){
			if(isdown){
				moveDown();
			}
			if(isleft){
				moveLeft();
			}
			if(isright){
				moveRight();
			}
			if(isup){
				moveUp();
			}
			frame=(frame+1)%9;
		}else{
			frame=0;
		}
	}
	public void moveTo(int x,int y){
		int px=rect.x+rect.width/2;
		int py=rect.y+rect.height/2;
		isright=px<x;
		isleft=px>x;
		isup=py>y;
		isdown=py<y;
	}
}
//����
class Enemy extends Actor implements Runnable{
	public Random r;
	public Enemy(){
		super.Action();
		speed=3;
		rect.x=rect.y=200;
		r=new Random(100);
		new Thread(this).start();
	}
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				dir=r.nextInt(4);
				if(dir==0){
					isdown=true;
					isleft=false;
					isright=false;
					isup=false;
				}
				if(dir==1){
				isdown=false;
				isleft=true;
				isright=false;
				isup=false;
				}
				if(dir==2){
					isdown=false;
					isleft=false;
					isright=true;
					isup=false;
				}
				if(dir==3){
					isdown=false;
					isleft=false;
					isright=false;
					isup=true;
				}
		}
		
	}
}