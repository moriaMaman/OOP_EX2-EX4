package Algorithm;

import Coords.GeoBox;
import Coords.map;
import Coords.pixel;
import Geom.Point3D;

public class boxElement {
	/*
	 * this function gets two points that represents a rectangle and one point, and check if the point is in the rectangle
	 */
	public static boolean isInRect (int minX,int minY,int maxX,int maxY,int x,int y) {
		if(maxX>x && minX<x && maxY<y && minY>y) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * this function gets a box and return all the box vertices 
	 */
	public static Point3D[] getBoxVertices(GeoBox g) {
		Point3D[] ans=new Point3D[4];
		ans[0]=new Point3D(g.getMin().x(),g.getMax().y());
		ans[1]=new Point3D(g.getMax().x(),g.getMax().y());
		ans[2]=new Point3D(g.getMax().x(),g.getMin().y());
		ans[3]=new Point3D(g.getMin().x(),g.getMin().y());
		
		return ans;
		
	}
	/*  this function gets two points that represents a rectangle and a two points that represents a segment and check if the segment is in the rectangle     
	 */
	 public static boolean SagmentIsInRect(Point3D min,Point3D max,pixel seg1,pixel seg2,map m) {
		 pixel minP=m.gpsTopixel(min);
		 pixel maxP=m.gpsTopixel(max);
		 boolean seg1IsInRect=isInRect(minP.getx(),minP.gety(),maxP.getx(),maxP.gety(),seg1.getx(),seg1.gety());
		 boolean seg2IsInRect=isInRect(minP.getx(),minP.gety(),maxP.getx(),maxP.gety(),seg2.getx(),seg2.gety());
		 if(seg1IsInRect|| seg2IsInRect) {
			 return true;
		 }
		 else {
			 return false;
		 }
			
		}

	
}
