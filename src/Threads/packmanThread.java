package Threads;


import java.util.ArrayList;
import java.util.Iterator;

import GUI.MyFrame;
import Geom.Point3D;
import gameElements.Fruit;
import gameElements.Packman;
/**
 * this class represents a thread type object,that include a packman, frame object and a list of coordinate which will be a path for the packman
 * @author moria and atara 
 *
 */
public class packmanThread extends  Thread {
	
	private Packman p;
	private MyFrame frame;
	private ArrayList<Point3D> steps;
	
	public packmanThread(Packman pack,MyFrame f) {
		this.p=pack;
		this.frame=f;
		this.steps=this.frame.packmanJump(p);
		
	}
	
    public void run() 
	{
			try
			{
				Iterator <Point3D> itP = steps.iterator();
				while(itP.hasNext()) {
					Point3D point=itP.next();
					this.p.setNewLocation(point);//will set the packman next point 
					this.frame.threadMethod();
					this.sleep(1000);				
					}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
    }

	

}
