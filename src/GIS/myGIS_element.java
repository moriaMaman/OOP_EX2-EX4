package GIS;

import java.util.Iterator;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class myGIS_element implements GIS_element{

	private  String [] _line;
	private String _type;
	private int _ID;
	private double  _Speed;	
	private double _Weight;
	double _radius;
	private Point3D _gps;
	//private int _numberOfPackman;
	//private int _numberOfFruit;

	//	private Meta_data data;

	public myGIS_element(String [] l) {
		this._line=l;
		_type=_line[0];
		_ID= Integer.parseInt(_line[1]);
		_Speed=Double.parseDouble(_line[5]);
		_Weight=Double.parseDouble(_line[5]);
		if(_type.equals("P")) {
			_radius=Double.parseDouble(_line[6]);
		}
		//_numberOfPackman=Integer.parseInt(_line[7]);
		//_numberOfFruit=Integer.parseInt(_line[8]);
		double x=Double.parseDouble(this._line[2]);
		double y=Double.parseDouble(this._line[3]);
		double z=Double.parseDouble(this._line[4]);
		_gps=new Point3D (x,y,z);
		//this.data=new elementMeta_data(_line);
	}

	public String type() {
		return _type;
	} 
	public double speed() {
		return _Speed;
	}
	public double weight() {
		return _Weight;
	}
	public double radius() {
		return _radius;	
	}
	public int id() {
		return _ID;
	}
	//	public int numberOfPackman() {
	//		return _numberOfPackman;
	//	}
	//	public int numberOfFruit() {
	//		return _numberOfFruit;
	//	}
	public String [] line() {
		return _line;
	}

	@Override
	public Geom_element getGeom() {
		return _gps;
	}

	@Override
	public Meta_data getData() {
		return null;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords m=new MyCoords();
		Point3D temp=_gps;
		Point3D _gps=m.add(temp, vec);
	}
	//	public String toString() {
	//		String ans=
	//				"Mac:"+MAC()+"\n"
	//				+"SSID:"+SSID()+"\n"
	//				+"firstSeen:"+firstSeen()+"\n"
	//				+"AuthMode:"+AuthMode()+"\n"
	//				+"Channel:"+Channel()+"\n"
	//				+"type:"+type()+"\n"
	//				+"RSSI:"+RSSI()+"\n"
	//				+"AccuracyMeters"+AccuracyMeters()+"\n"
	//				+"cordinate:"+_gps.toString()+"\n";
	//		return ans;
	//			
	//		}

}
