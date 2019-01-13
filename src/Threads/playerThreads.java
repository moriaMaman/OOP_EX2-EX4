package Threads;

import java.util.ArrayList;

import Coords.MyCoords;
import GUI.MyFrame;
import Geom.Point3D;
import gameElements.Player;

public class playerThreads extends  Thread {
	private Player p;
	private ArrayList<Point3D> pathPoints;
	private MyFrame frame;
	
	public playerThreads(Player p,MyFrame frame ) {
		this.p=p;
		this.frame=frame;
		
	}
	public void run(){
		while(this.frame.play1.isRuning()) {
			this.frame.algo();
			this.pathPoints=this.frame.pointsPath;
	 for(int i=0;i<pathPoints.size();i++) {
		 MyCoords mc=new MyCoords();
		 double [] azimuth=mc.azimuth_elevation_dist(this.p.getCurrentLocation(), pathPoints.get(i));//calculate the angle and the distance between the two points 
		 double angel=azimuth[0];
		 double distance=azimuth[2];
		 
		 while(distance>1) {//As long as the player has not reached the fruit yet
			this.frame.play1.rotate(angel);
			this.frame.updateData();
			distance=mc.distance3d(this.p.getCurrentLocation(), pathPoints.get(i));
		 }
		 try {
			 this.sleep(50);
			 
		 }
		 catch (Exception e) 
			{
				e.printStackTrace();
			}
		 frame.repaint();
	 }
		}
	}
	
	
}
