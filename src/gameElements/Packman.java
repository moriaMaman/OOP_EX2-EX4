package gameElements;

import java.util.ArrayList;

import Geom.Point3D;



/**
 * this class represents a packman object.
 * @author moria and atara
 *
 */

public class Packman{
	private Point3D _currentLocation;//first location 
	private Point3D _previosLocation;
	private  double _speed;//the packman's speed
	private double _radius;//the packman's eating radius 
	private double _time;//the total eating time of the packman
	private int id;
	public ArrayList<Fruit> eatList;//a list of fruits the packman need to eat 
	public Point3D newLocation;//the location of the packman after he eat one of the fruits
	private long _utc;

	public Packman (Point3D currentLocation, double speed, double radius) {
		this._currentLocation = currentLocation;
		this._speed = speed;
		this._radius = radius;
		this._time=0;
		this._previosLocation=new Point3D(0,0,0);
		this.eatList=new ArrayList<Fruit>();
		this.newLocation=currentLocation;
		
		id++;
		
	}

	
	public Point3D currentLocation() {
		return _currentLocation;
	}
	public boolean isEqal(Packman p) {
		if (this.currentLocation()==p.currentLocation() && this.speed()==p._speed && this._radius==p._radius) {
			return true;
		}
		return false;
	}
	
	public int getId() {
		return id;
	}
	public double speed() {
		return _speed;
	}

	public double radius() {
		return _radius;
	}

	public double time() {
		return _time;
	}
	public void setTime(double t)
	{
		this._time=t;
	}
	public void setLocation(Point3D p) {
		this._currentLocation=p;
	}
	public void setNewLocation(Point3D p) {
		this.newLocation=p;
	}

	public Point3D previosLocation() {
		return _previosLocation;
	}

	public void setUtc(long utc) {
		this._utc = utc;
	}
	
	public long getUtc() {
		return this._utc;
	}
	public void setPreviosLocation(Point3D _previosLocation) {
		this._previosLocation = _previosLocation;
	}

}