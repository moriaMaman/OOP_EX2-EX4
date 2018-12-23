package gameElements;

import java.util.ArrayList;

import Geom.Point3D;

/**
 * this class implements the packman object.
 * its includ a location on the map, the speed he can eat in, the radius of its eat scale, 
 * meaning he dosent have to be actuaky on the fruit to eat him.
 *  a id.
 *  the list of fruits its eats. and utc the time the packman was create.
 * @author עטרה
 *
 */
public class Packman{
	private Point3D _currentLocation;
	private Point3D _previosLocation;
	private  double _speed;
	private double _radius;
	private double _time;
	public static int id;
	public ArrayList<Fruit> eatList;
	public Point3D newLocation;
	private long _utc;
	
/**
 * Constructor
 * @param currentLocation
 * @param speed
 * @param radius
 */
	public Packman (Point3D currentLocation, double speed, double radius) {
		this._currentLocation = currentLocation;
		this._speed = speed;
		this._radius = radius;
		this._time=System.currentTimeMillis();
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
	
	public void setPreviosLocation(Point3D _previosLocation) {
		this._previosLocation = _previosLocation;
	}
	
	public void setUtc(long utc) {
		this._utc = utc;
	}
	
	public long getUtc() {
		return this._utc;
	}

}