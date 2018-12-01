package Coords;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;

class MyCoordsTest {

	@Test
	void testAdd() {
		Point3D p1=new Point3D(32.103315,35.209039,670);
		Point3D p2=new Point3D(32.10635200000035,35.20522500000041,650.0);
		Point3D p3=new Point3D(337.6989921,-359.2492069,-20);
		MyCoords m=new MyCoords();
		if(!p2.equals(m.add(p1, p3))) {
			fail("this function doesnt work well");
		}
	}

	@Test
	void testDistance3d() {
		Point3D p1=new Point3D(32.103315,35.209039,670);
		Point3D p2=new Point3D(32.106352,35.205225,650);
		MyCoords m=new MyCoords();
		double dis=493.0523318324134;
		if((m.distance3d(p1, p2)) != dis) {
			fail("the 'Distance3d' function doesnt work well");
		}
	}

	@Test
	void testVector3D() {
		Point3D p1=new Point3D(32.103315,35.209039,670);
		Point3D p2=new Point3D(32.106352,35.205225,650);
		Point3D p3=new Point3D(337.69899206128815,-359.24920693881893,-20.0);
		MyCoords m=new MyCoords();
		if(!p3.equals(m.vector3D(p1, p2))) {
			fail("the 'testVector3D' function doesnt work well");
		}
	}

	@Test
	void testAzimuth_elevation_dist() {
		Point3D p1=new Point3D(32.103315,35.209039,670);
		Point3D p2=new Point3D(32.106352,35.205225,650);
		double [] p3= {313.2304203264989,-2.3247635173865278,493.0523318324134};
		MyCoords m=new MyCoords();
		if(!Arrays.equals(p3, m.azimuth_elevation_dist(p1, p2))) {
			fail("the 'Azimuth_elevation_dist' function doesnt work well");
		}
	}

	@Test
	void testIsValid_GPS_Point() {
		Point3D p1=new Point3D(32.103315,35.209039,670);
		MyCoords m=new MyCoords();
		if (!m.isValid_GPS_Point(p1))
		fail("the 'IsValid_GPS_Point' function doesnt work well");
	}

}
