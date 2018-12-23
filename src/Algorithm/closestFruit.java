package Algorithm;

import gameElements.Fruit;
import gameElements.Packman;

/**
 * this class implements an object of the shortest path algorithem, its contain the packman,
 *  the fruit and the time it took to get to this fruit.
 * @author Atara Zohar & Moria Maman
 *
 */

public class closestFruit {
	
	private Fruit myfruit;
	private Packman mypackman;
	private double time;
	
	/**
	 * constractor
	 * @param myfruit
	 * @param mypackman
	 * @param time
	 */
	public closestFruit (Fruit myfruit, Packman mypackman, double time ){
		this.myfruit= myfruit;
		this.mypackman= mypackman;
		this.time=time;
	}
	
	public double getTime(){
		return this.time;
	}
 
	public Packman getPackman() {
		return this.mypackman;
	}
	
	public Fruit getFruit() {
		return this.myfruit;
	}
	
	public void setTime(double time){
		this.time=time;
	}
	
	public void setPackman(Packman packman){
		this.mypackman=packman;
	}
	
	public void setFruit(Fruit fruit){
		this.myfruit=fruit;
	}
}
	
