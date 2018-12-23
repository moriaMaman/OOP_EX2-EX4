  package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

class mapTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Point3D p1= new Point3D(32.106046,35.202574);
		pixel pi1= new pixel (0,0);
		map m= new map(1433,642);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void gpsTopixelTest() {
		map m= new map(1433,642);
		Point3D p1= new Point3D(32.106046,35.202574);
		pixel pi1= new pixel (0,0);
		pixel ans= new pixel(m.gpsTopixel(p1).getx(),m.gpsTopixel(p1).gety());
		if(ans.getx()!=pi1.getx()||ans.gety()!=pi1.gety()) {
			fail("this gpsTopixel isen working well!!" );
		}
	}
	
	@Test
	void pixelToGpsTest() {
		map m= new map(1433,642);
		Point3D p1= new Point3D(32.106046,35.202574);
		pixel pi1= new pixel (0,0);
		Point3D ans= new Point3D(m.pixelToGps(pi1).x(),m.pixelToGps(pi1).y());
		if(ans.x()!=p1.x()||ans.y()!=p1.y()) {
			fail("this gpsTopixel isen working well!!" );
		}
	}

}
