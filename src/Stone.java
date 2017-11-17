import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.*;
public class Stone extends JLabel  {
	private int 
		X=0,
		Y=0,
		mul=GUI.multiple/20;
	MainGame mg;
	Random r=new Random();
	public Stone(MainGame m,int pos){
		mg=m;
		mg.add(this);
		setText("\u2B24");
		setForeground(Color.getHSBColor(
				r.nextFloat()*255,
				r.nextFloat()*255,
				r.nextFloat()*255));
		X=calcX(pos);
		Y=calcY(pos);
		setBounds(X,Y,18*mul,18*mul);
		setFont(new Font("Times New Romans",Font.PLAIN,(int)(10*mul)));
	}
	public void move(int pos) {
		int 
			newX=calcX(pos),
			newY=calcY(pos);
		Random r=new Random();
		int time=(pos>=0)? 50:5;
		for(int i=0;i<=time;i++) {
			try {
				setLocation(X+(int)((newX-X)*i/time),Y+(int)((newY-Y)*i/time));
				Thread.sleep(1);
				mg.paintImmediately(mg.getVisibleRect());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		X=newX;
		Y=newY;
	}
	int calcX(int pos) {
		int col=calcCol(pos);
		return (int)(29*mul+col*39*mul+r.nextInt((int)(25*mul)));
	}
	int calcY(int pos) {
		int row=calcRow(pos);
		if(pos!=5&&pos!=11&&pos>=0)	return (int)(53*mul+row*36*mul+r.nextInt((int)(20*mul)));
		if(pos>=0) return (int)(69*mul+r.nextInt((int)(20*mul)));
		if(pos==-2) return (int)(11*mul+r.nextInt((int)(20*mul)));
		return (int)(136*mul+r.nextInt((int)(20*mul)));
	}
	int calcCol(int pos) {
		if(pos<0) return 3; 
		if(pos<6) return(pos+1);
		else if(pos<11) return(11-pos);
			else return 0;
	}
	int calcRow(int pos) {
		if(pos<6) return 1;
			else return 0;
	}
}
