package Algorithm;
	
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import Coords.MyCoords;
import gameElements.Fruit;
import gameElements.Packman;

/**
 * this class implement a path object.
 * a path is a packman and all the fruits in the game sorted from the closest fruit to the fares.
 * @author Atara Zohar and Moria Maman
 *
 */

public class Path {
	private Packman packman;
	private ArrayList<Fruit> fruits;
	private ArrayList<closestFruit> priorityList;
	private  Comparator <closestFruit> cmpByToTime= new sortComperator();
	
	/**
	 * a constractor get the packman and all the fruits of the game.
	 * @param p
	 * @param fruit
	 */
	public Path(Packman p,ArrayList<Fruit> fruit) {
		packman=p;
		this.fruits=fruit;
		this.priorityList=new ArrayList<closestFruit>();
		buildPriorityList();
	}
	
	/**
	 * get the array fruit and sort it.
	 */
	private void buildPriorityList() {
		Iterator <Fruit> itF = this.fruits.iterator();
		while(itF.hasNext()) {
			Fruit fru=itF.next();
			double time= getTime(fru,this.packman.time());
			closestFruit currentFruit= new closestFruit( fru,this.packman,time);
			this.priorityList.add(currentFruit);
			priorityList.sort(cmpByToTime);
		}
		
	}
	public ArrayList<closestFruit> getPriorityList(){
		return this.priorityList;
	}

	public ArrayList<Fruit> getFruits(){
		return this.fruits;
	}
	public Packman getPackman() {
		return this.packman;
	}
	
	/**
	 * this function calculate the time take a packman to arrive to a fruit.
	 * @param f
	 * @param time
	 * @return
	 */
	public double getTime(Fruit f,double time){
		double ans=0;
		MyCoords m=new MyCoords();
		double distance=m.distance3d(this.packman.newLocation,f.currentLocation());
		ans=(distance-packman.radius())/packman.speed();
		if (time ==0) {//if the packman don't eat any fruit yet 
			return ans;
		}
		else {
			return ans+time;
		}
	}
	

}
