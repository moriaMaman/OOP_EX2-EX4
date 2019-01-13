package gameElements;

import Geom.Point3D;
/**
 * this class represents a Player object.
 * @author Atara Zohar & Moria Maman
 *
 */
public class Player {
	
	private int _id;
	private double _speed;
	private double _radius;
	private Point3D _currentLocation;
	
	public Player() {
		this._id++;
		this._speed=1;
		this._radius=1;
		this._currentLocation=new Point3D(0,0,0);
	}
	public Player(double s,double r,Point3D p) {
		this._id++;
		this._speed=s;
		this._radius=r;
		this._currentLocation=p;
	}
	
	public Point3D getCurrentLocation() {
		return _currentLocation;
	}
	
	public double getRadius() {
		return _radius;
	}
	
	public int getId() {
		return _id;
	}
	
	public double getSpeed() {
		return _speed;
	}
	
	public void setId(int id) {
		this._id=id;
	}
	
	public void setRadius(double Radius) {
		this._radius=Radius;
	}
	
	public void setSpeed(double speed) {
		this._speed=speed;
	}
	
	public void setCurrentLocation(Point3D currentLocation) {
		this._currentLocation=currentLocation;
	}

}