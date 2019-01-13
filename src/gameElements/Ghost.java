package gameElements;

import Geom.Point3D;

/**
 * this class represents a ghost object.
 * @author Atara Zohar & Moria Maman
 *
 */
public class Ghost {
	private int _id;
	private Point3D _currentLocation;
	private double _speed;
	private double _radius;
	
	public Ghost(Point3D _currentLocation ,double _radius, double _speed) {
		this._id=_id++;
		this._radius=_radius;
		this._currentLocation=_currentLocation;	
		this._speed=_speed;
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
	
	public void setRadius(int Radius) {
		this._radius=Radius;
	}
	
	public void setSpeed(int speed) {
		this._speed=speed;
	}
	
	public void setCurrentLocation(Point3D currentLocation) {
		this._currentLocation=currentLocation;
	}

}