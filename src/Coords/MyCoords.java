package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {
	public final double radius =6371000;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// TODO Auto-generated method stub
		double LonNorm=Math.cos(gps.x()*Math.PI/180);
		double xToRad=Point3D.d2r(gps.x());
		double yToRad=Point3D.d2r(gps.y());
		double xMeterToRad=Math.asin(local_vector_in_meter.x()/radius);
		double yMeterToRad=Math.asin(local_vector_in_meter.y()/(radius*LonNorm));
		double newX=xToRad+ xMeterToRad;
		double newY=yToRad+yMeterToRad;
		double newZ=gps.z()+local_vector_in_meter.z();
		Point3D newPoint=new Point3D(Point3D.r2d(newX),Point3D.r2d(newY),newZ);
		return(newPoint);
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		double LonNorm=Math.cos(gps0.x()*Math.PI/180);
		double diff = gps1.x()-gps0.x();
		double radian = (diff*Math.PI)/180;
		double tometer1 = Math.sin(radian)*radius;
		double diff2 = gps1.y()-gps0.y();
		double radian2 = (diff2*Math.PI)/180;
		double tometer2 = Math.sin(radian2)*radius*LonNorm;

		return Math.sqrt((tometer1*tometer1) + (tometer2*tometer2));
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		double LonNorm=Math.cos(gps0.x()*Math.PI/180);
		double diff = gps1.x()-gps0.x();
		double radian = (diff*Math.PI)/180;
		double tometer1 = Math.sin(radian)*radius;
		double diff2 = gps1.y()-gps0.y();
		double radian2 = (diff2*Math.PI)/180;
		double tometer2 = Math.sin(radian2)*radius*LonNorm;
		double diff3 = gps1.z()-gps0.z();

		Point3D vector3d=new Point3D (tometer1 , tometer2, diff3);

		return vector3d;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub

		double[] ans = new double [3];
		//elevation
		double Zdiff=gps1.z()-gps0.z();
		ans[1]= Point3D.r2d(Math.asin(Zdiff/distance3d(gps0, gps1)));
		//azimuth
		double lon1 = gps0.y();
		double lon2 = gps1.y();
		double lat1Rad = Math.toRadians(gps0.x());
		double lat2Rad = Math.toRadians(gps1.x());
		double longDiff= Math.toRadians(lon2-lon1);
		double y= Math.sin(longDiff)*Math.cos(lat2Rad);
		double x=Math.cos(lat1Rad)*Math.sin(lat2Rad)-Math.sin(lat1Rad)*Math.cos(lat2Rad)*Math.cos(longDiff);
		ans[0]= (Math.toDegrees(Math.atan2(y, x))+360)%360;
		//dist
		ans[2]=distance3d(gps0, gps1);

		return ans;
	}


	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		double x=p.x();
		double y=p.y();
		double z=p.z();

		if(y>180 || y<-180 || x>90 || x<-90 || z<-450)
			return false;
		else 
			return true;
	}

}
