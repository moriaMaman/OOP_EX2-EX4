package gameElements;

import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;
import Geom.Point3D;

/**
 * this class is the object fruit, its include a GPS point if location on the map.
 * a weight which mean how much points the packman get for eating the fruit, the id, and the time the packman get to eat it.
 * @author Atara Zohar & Moria Maman
 *
 */

public class Fruit   {
    
	//private long _id;
	private Point3D _currentLocation;
	private double _weight;
	public static int id;
	private double time;
	
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
