package Coords;

import Geom.Point3D;
/**
 * this class represents a map picture
 * this class converts from pixel to coordinate and from coordinate to pixel
 * @author Atara Zohar and Moria Maman
 *
 */


public class map {
	
	static final Point3D mapCoordsStart=new Point3D(32.106046,35.202574);
	static final Point3D mapCoordsEnd=new Point3D(32.101858,35.212405);
	static final Point3D mapPoint=new Point3D(mapCoordsStart.x()-mapCoordsEnd.x(),mapCoordsEnd.y()-mapCoordsStart.y());
	private int frameWidth;
	private int frameHeigth;
	
	public map(int Width,int Heigth) {
		frameWidth=Width;
		frameHeigth=Heigth;
	}
	/*
	 *  this function converts from coordinate to pixel
	 * we use this code - https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
	 */
  public pixel gpsTopixel(Point3D p){
	    // use offsets
		double longitude=p.y()-mapCoordsStart.y();
		double latitude=mapCoordsStart.x()-p.x();
		
	    // set x & y using conversion
	    int x = (int) (frameWidth*(longitude/mapPoint.y()));
	    int y = (int) (frameHeigth*(latitude/mapPoint.x()));

	    return new pixel(x, y);
	}
	
	/*
	 * this function converts from pixel to coordinate
	 */
	public Point3D pixelToGps(pixel p) {
		double x=mapCoordsStart.x()-((p.gety()*mapPoint.x())/this.frameHeigth);
		double y=((p.getx()*mapPoint.y())/this.frameWidth)+mapCoordsStart.y();
		return (new Point3D(x,y,0));
		
	}
	/*
	 * this function calculate the distance between two pixels
	 */
	public double pixelDistance (pixel pixelPoint1, pixel pixelPoint2) {

		MyCoords m = new MyCoords();

		Point3D gps1 = pixelToGps(pixelPoint1);
		Point3D gps2 = pixelToGps(pixelPoint2);

		double answer=m.distance3d(gps1,gps2);

		return answer;
	}
	/*
	 * this function calculate the angel between two pixels
	 */

	public double pixelangel (pixel pixelPoint1, pixel pixelPoint2) {

		MyCoords m = new MyCoords();

		Point3D gps1 = pixelToGps(pixelPoint1);
		Point3D gps2 = pixelToGps(pixelPoint2);

		double ans[]=m.azimuth_elevation_dist(gps1,gps2);
		double answer=ans[0];

		return answer;
	}
}