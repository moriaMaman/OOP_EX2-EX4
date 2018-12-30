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
	private Point3D _gps2;
	
	private elementMeta_data data;


	public myGIS_element(String [] l) {
		this._line=l;
		_type=_line[0];
		_ID= Integer.parseInt(_line[1]);
		_Speed=Double.parseDouble(_line[5]);
		_Weight=Double.parseDouble(_line[5]);
		if(_type.equals("P")) {
			_radius=Double.parseDouble(_line[6]);
		}
		if(_type.equals("G")||_type.equals("G") ) {
			_radius=Double.parseDouble(_line[6]);
		}
		if(_type.equals("B")) {
			_Weight=Double.parseDouble(_line[8]);
			double x2=Double.parseDouble(this._line[5]);
			double y2=Double.parseDouble(this._line[6]);
			double z2=Double.parseDouble(this._line[7]);
			_gps2=new Point3D (x2,y2,z2);
			
		}
		double x=Double.parseDouble(this._line[2]);
		double y=Double.parseDouble(this._line[3]);
		double z=Double.parseDouble(this._line[4]);
		_gps=new Point3D (x,y,z);
		
		this.data=new elementMeta_data(this.line());
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
	
	public String [] line() {
		return _line;
	}

	@Override
	public Geom_element getGeom() {
		return _gps;
	}
	public Geom_element getGeom2() {
		return _gps2;
	}
	@Override
	public Meta_data getData() {
		return this.data;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords m=new MyCoords();
		Point3D temp=_gps;
		Point3D _gps=m.add(temp, vec);
	}

}
