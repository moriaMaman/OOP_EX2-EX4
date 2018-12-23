package Algorithm;
	
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import Coords.MyCoords;
import gameElements.Fruit;
import gameElements.Packman;

/**
 * this class represent a single packman and the list of all the fruits in the game.
 *  the class will return a priority List of fruits for each packman compare by time and distance.
 * @param f
 * @param time
 * @return
 */


public class Path {
	private Packman packman;
	private ArrayList<Fruit> fruits;
	private ArrayList<closestFruit> priorityList;
	private  Comparator <closestFruit> cmpByToTime= new sortComperator();
	
	public Path(Packman p,ArrayList<Fruit> fruit) {
		packman=p;
		this.fruits=fruit;
		this.priorityList=new ArrayList<closestFruit>();
		buildPriorityList();
		
	}
	/*
	 * this function will build a Priority List of the object closestFruit  for each packman considering time and distance 
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
	public double getTime(Fruit f,double time){
		double ans=0;
		MyCoords m=new MyCoords();
		double distance=m.distance3d(this.packman.newLocation,f.currentLocation());
		ans=(distance-packman.radius())/packman.speed();
		if (time ==0) {//if the packman don't eat any fruit yet 
			return ans;
		}
		else {
			return ans+time;//will sum the total eating time for each packman 
		}
	}
	

}
