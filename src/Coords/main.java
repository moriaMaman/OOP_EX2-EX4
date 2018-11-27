package Coords;

import java.util.Arrays;

import Geom.Point3D;
 
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point3D p1=new Point3D(32.103315,35.209039,670);
		Point3D p3=new Point3D(32.106352,35.205225,650);
		Point3D p2=new Point3D(32.106352,35.205225,650);
		MyCoords m=new MyCoords();
		System.out.println(Arrays.toString(m.azimuth_elevation_dist(p1, p3)));
		
	}
	

}
