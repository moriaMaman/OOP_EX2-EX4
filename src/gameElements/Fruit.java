package gameElements;

import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;



/**
 * this class represents a fruit object.
 * @author Atara Zohar & Moria Maman
 *
 */

public class Fruit   {
    
	private Point3D _currentLocation;
	private double _weight;//The value of the fruit
	private int id;
	private double time;//The time it took for the packman to eat the fruit
	
	public Fruit (Point3D p,double w) {
		this._currentLocation =p;
		this._weight=w;
		this.id++;
	}
	
	public Point3D currentLocation() {
		return _currentLocation;
	}
	public double weight() {
		return _weight;
	}
	public int id() {
		return id;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
}
