package Coords;

import Geom.Point3D;
/**
 * this class implements a system of showing a object on a map picture,
 * but also to show each object to his correct coordinate on the map.
 * @author Atara Zohar and Moria Maman
 *
 */

public class map {

	static final Point3D mapCoordsStart=new Point3D(32.106046,35.202574);
	static final Point3D mapCoordsEnd=new Point3D(32.101858,35.212405);
	static final Point3D mapPoint=new Point3D(mapCoordsStart.x()-mapCoordsEnd.x(),mapCoordsEnd.y()-mapCoordsStart.y());
	private int frameWidth;
	private int frameHeigth;

	/**
	 * the constractor, get the Width and Heigth of the frame.
	 *  so we can Increase and decrease the gui window and the points will stay in place
	 * @param Width
	 * @param Heigth
	 */
	public map(int Width,int Heigth) {
		frameWidth=Width;
		frameHeigth=Heigth;
	}
	/**
	 * this function get a gps point and convert it to pixel of the picture
	 * we use this code - https://stackoverflow.com/questions/38748832/convert-longitude-and-latitude-coordinates-to-image-of-a-map-pixels-x-and-y-coor
	 * @param p
	 * @return
	 */
	public pixel gpsTopixel(Point3D p){
		// use offsets
		double longitude=p.y()-mapCoordsStart.y();

		// do inverse because the latitude increases as we go up but the y decreases as we go up.
		// if we didn't do the inverse then all the y values would be negative.
		double latitude=mapCoordsStart.x()-p.x();

		// set x & y using conversion
		int x = (int) (frameWidth*(longitude/mapPoint.y()));
		int y = (int) (frameHeigth*(latitude/mapPoint.x()));

		return new pixel(x, y);
	}

	/**
	 * this function get a pixel point and convert it to gps of the map
	 * @param p
	 * @return
	 */
	public Point3D pixelToGps(pixel p) {
		double x=mapCoordsStart.x()-((p.gety()*mapPoint.x())/this.frameHeigth);
		double y=((p.getx()*mapPoint.y())/this.frameWidth)+mapCoordsStart.y();
		return (new Point3D(x,y,0));

	}

	/**
	 * this function get two pixel point and calculate the distance between them
	 * @param pixelPoint1
	 * @param pixelPoint2
	 * @return
	 */
	public double pixelDistance (pixel pixelPoint1, pixel pixelPoint2) {

		MyCoords m = new MyCoords();

		Point3D gps1 = pixelToGps(pixelPoint1);
		Point3D gps2 = pixelToGps(pixelPoint2);

		double answer=m.distance3d(gps1,gps2);

		return answer;
	}
	
	/**
	 * this function get two pixel point and calculate angle between them
	 * @param pixelPoint1
	 * @param pixelPoint2
	 * @return
	 */
	public double pixelangel (pixel pixelPoint1, pixel pixelPoint2) {

		MyCoords m = new MyCoords();

		Point3D gps1 = pixelToGps(pixelPoint1);
		Point3D gps2 = pixelToGps(pixelPoint2);

		double ans[]=m.azimuth_elevation_dist(gps1,gps2);
		double answer=ans[1];

		return answer;
	}
}