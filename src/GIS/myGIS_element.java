package GIS;

import java.util.Iterator;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class myGIS_element implements GIS_element{
	
	private  String [] _line;
	private  String _MAC;
	private  String _SSID;
	private  String _AuthMode;
	private  int _Channel;
	private  String _firstSeen;
	private  String _type;
	private  int _RSSI;
	private  int _AccuracyMeters;
	private Point3D _gps;
	private Meta_data data;

	public myGIS_element(String [] l) {
		this._line=l;
		_SSID=_line[1];
    	_firstSeen=_line[3];
    	_type=_line[10];
    	_MAC=_line[0];
    	_AuthMode=_line[2];
    	_Channel=Integer.parseInt(_line[4]);
    	_RSSI=Integer.parseInt(_line[5]);
    	_AccuracyMeters=Integer.parseInt(_line[9]);
    	double x=Double.parseDouble(this._line[7]);
		double y=Double.parseDouble(this._line[6]);
		double z=Double.parseDouble(this._line[8]);
    	_gps=new Point3D (x,y,z);
    	this.data=new elementMeta_data(_line);
	}
	
	public String MAC() {
		return _MAC;
	}
	public String SSID() {
		return _SSID;
	}
	public String AuthMode() {
		return _AuthMode;
	}
	public String firstSeen() {
		return _firstSeen;
	}
	public String type() {
		return _type;
	}
	public int Channel() {
		return _Channel;
	}
	public int RSSI() {
		return _RSSI;
	}
	public int AccuracyMeters() {
		return _AccuracyMeters;
	}
	
	public String [] Line () {
		return _line;
	}

	@Override
	public Geom_element getGeom() {
		return _gps;
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
	public String toString() {
		String ans=
				"Mac:"+MAC()+"\n"
				+"SSID:"+SSID()+"\n"
				+"firstSeen:"+firstSeen()+"\n"
				+"AuthMode:"+AuthMode()+"\n"
				+"Channel:"+Channel()+"\n"
				+"type:"+type()+"\n"
				+"RSSI:"+RSSI()+"\n"
				+"AccuracyMeters"+AccuracyMeters()+"\n"
				+"cordinate:"+_gps.toString()+"\n";
		return ans;
			
		}

}
