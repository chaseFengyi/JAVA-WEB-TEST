/*
 * description:
 * can find path with search algorithm and A*
 * author: zhangxu
 */
package Game; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
class Road{
	public int r;
	public int c;
	public int pre;
}
public class Smart {
	public int pathlen=-1;
	public Byte way[]=new Byte[20101];
	public Road ro[]=new Road[20101];
	public int roter[]=new int[20101];
	public int rotec[]=new int[201101]; 
	public int roterear=-1;
	int mover[]=new int[]{0,-1,0,1};
	int movec[]=new int[]{-1,0,1,0};
	public Road[] bfs(int map[][],int maxr,int maxc,int r,int c,int er,int ec){
		byte [][]vis=new byte [101][101];
		int front=0,rear=0;
		Road rt=new Road();
		ro[0]=new Road();
		pathlen=-1;
		ro[rear].r=r;
		ro[rear].c=c;
		rt=ro[0];
		rt.pre=-1;
		vis[r][c]=-1;
		rear++;
		while(front<rear){
			int row=ro[front].r;
			int col=ro[front].c;
			if(row==er&&col==ec){
				pathlen=front;
				break;
			}else{
				vis[row][col]=-1;
				int first=rear;
				for(int i=0;i<4;i++){
					int nr=row+mover[i];
					int nc=col+movec[i];
						if(nr>=0&&nr<maxr&&nc>=0&&nc<maxc&&vis[nr][nc]!=-1&&map[nr][nc]!=-1){
							vis[nr][nc]=-1;
							ro[rear]=new Road();
							ro[rear].r=nr;
							ro[rear].c=nc;
							ro[rear].pre=front;
							rear++;
						}
						Qsort(ro,first,rear-1,r,c,er,ec);
				}
			}
			front++;
		}
		ro[0]=rt;
		return ro;
	}
	public boolean getRoad(){
		int no=pathlen,j;
		for(roterear=0;no>0;roterear++){
			roter[roterear]=ro[no].r;
			rotec[roterear]=ro[no].c;
			no=ro[no].pre;
		}
		roterear--;
		return pathlen!=-1;
	}
	public boolean getPath(){
		int i=0,no=pathlen,j,rear;
		for(i=0;no!=0;i++){
			int dtr=ro[ro[no].pre].r-ro[no].r;
			int dtc=ro[ro[no].pre].c-ro[no].c;
			if(dtr>0) way[i]=1;
			else if(dtr<0) way[i]=3;
			else if(dtc>0) way[i]=0;
			else way[i]=2;
			no=ro[no].pre;
		}
		return pathlen!=-1;
	}
	public double mylength(Road a,int r,int c,int er,int ec){
		return Math.sqrt((a.r-r)*(a.r-r)+(a.c-c)*(a.c-c))+Math.sqrt((a.r-er)*(a.r-er)+(a.c-ec)*(a.c-ec));
	}
	public void Qsort(Road A[],int L,int R,int sr,int sc,int er,int ec){
		int l=L,r=R;
		A[0]=A[l];
		if(l<r){
			while(l<r){
				while(l<r&&mylength(A[r],sr,sc,er,ec)>=mylength(A[0],sr,sc,er,ec)) r--;
				A[l]=A[r];
				while(l<r&&mylength(A[l],sr,sc,er,ec)<=mylength(A[0],sr,sc,er,ec)) l++;
				A[r]=A[l];
			}
			A[l]=A[0];
			Qsort(A,L,l,sr,sc,er,ec);
			Qsort(A,l+1,R,sr,sc,er,ec);
		}
	}
}