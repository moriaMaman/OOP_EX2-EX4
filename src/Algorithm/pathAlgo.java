package Algorithm;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;

import Coords.GeoBox;
import Coords.map;
import Coords.pixel;
import Geom.Point3D;
import gameElements.Game;

public class pathAlgo {

	/*
	 * this function gets an ArrayList of boxes and a map object and return an ArrayList of all the relevant vertices (the ones that dose'nt in a box)
	 */ 
	public static ArrayList<Point3D> getEdges(ArrayList<GeoBox> boxes,map m) {
		ArrayList<Point3D> edges=new ArrayList<Point3D>();// open a new array list the one i return un the function
		Iterator <GeoBox> it = boxes.iterator();//run over all the boxes in the game
		while(it.hasNext()) {
			GeoBox box=it.next();
			Point3D[] ver=boxElement.getBoxVertices(box);//gets the box vertices
			for(int i=0;i<ver.length;i++) {//add all the vertices to the ArrayList
				edges.add(ver[i]);
				}
		}
		int index=0;//the box index
		
		int edgIndex=1;//the vertices index
		ArrayList<Point3D> removeList=new ArrayList<Point3D>();
		Iterator <Point3D> itE = edges.iterator();
		while(itE.hasNext()) {
			Point3D edg=itE.next();
			for(int i=0;i<boxes.size();i++) {
				if(i!=index) {//if the vertices is not in this box
					Point3D gpsMinPoint=new Point3D(boxes.get(i).getMin().x(),boxes.get(i).getMin().y());
					pixel rectMinPixel=m.gpsTopixel(gpsMinPoint);

					Point3D gpsMaxPoint=new Point3D(boxes.get(i).getMax().x(),boxes.get(i).getMax().y());
					pixel rectMaxPixel=m.gpsTopixel(gpsMaxPoint);

					Point3D gpsEdgPoint=new Point3D(edg.x(),edg.y());
					pixel edgPixelPoint=m.gpsTopixel(gpsEdgPoint);
					//check if the point is in a rectangle
					boolean ans=boxElement.isInRect(rectMinPixel.getx(),rectMinPixel.gety(),rectMaxPixel.getx(),rectMaxPixel.gety(),edgPixelPoint.getx(),edgPixelPoint.gety());
					if(ans==true) {//add this point to a remove list.
						removeList.add(edg);
					}
				}
			}
			if(edgIndex<4) {//checking if i'm still in the same box 
				edgIndex++;
			}
			else {//if this is a different box
				edgIndex=1;//Changing the vertices counter to be 1 
				index++;
			}

		}
		edges.removeAll(removeList);//remove all the irrelevant  vertices from the list
		return edges;

	}

	/*
	 * this function gets a point, an ArrayList of lines and a list of all the relevant vertices,and return a list of all the segments of the point
	 */
	public static ArrayList<Line2D> segmentOfPoint(Point3D a,ArrayList<Line2D> lines,ArrayList<Point3D> edges,map m){
		boolean flag=true;
		ArrayList<Line2D> ans=new ArrayList<Line2D>();
		for(int i=0;i<edges.size();i++) {
			Line2D seg=getSegment(a, edges.get(i),m);
			for(int j=0;j<lines.size()&&flag;j++) {
				if(doWeHaveTheSamePoint(seg,lines.get(j))==false) {
				if(seg.intersectsLine(lines.get(j))) {//check if the segment intersects with one of the lines
					flag=false;
				}
				}
				
			}
			if(flag) {//if the segment doesn't intersects with any line, i will add it to the segments list.
				
				ans.add(seg);
			}
			flag=true;
		}
		return ans;
	}

	/*
	 * this function gets an arrayList of boxes and return all the  boxes lines 
	 */
	public static ArrayList<Line2D> boxLines(ArrayList<GeoBox> g,map m){
		ArrayList<Line2D> ans=new ArrayList<Line2D>();
		Iterator <GeoBox> it = g.iterator();
		while(it.hasNext()) {
			GeoBox box=it.next();
			Point3D[] ver=boxElement.getBoxVertices(box);//get all the vertices of a box
			//the edges calculate
			ans.add(getSegment(ver[0], ver[1],m));
			ans.add(getSegment(ver[0], ver[3],m));
			ans.add(getSegment(ver[1], ver[2],m));
			ans.add(getSegment(ver[2], ver[3],m));
			//the diagonal calculate
			ans.add(getSegment(ver[0], ver[2],m));
			ans.add(getSegment(ver[1], ver[3],m));
		}
		return ans;
	}

	/*
	 * this function remove all the boxes thats inside a rectangle
	 */
	public static void boxSegment (ArrayList<Line2D> boxSegLine, ArrayList<GeoBox> boxes,map m){
		boolean IsInRectFlag=false; 
		for(int i=0;i<boxSegLine.size();i++) {
		Iterator <GeoBox> it2 = boxes.iterator();
		while(it2.hasNext()&&IsInRectFlag==false) {
			GeoBox rect=it2.next();
			pixel startSegPoint=new pixel((int)boxSegLine.get(i).getX1(),(int)boxSegLine.get(i).getY1());
			pixel endSegPoint=new pixel((int)boxSegLine.get(i).getX2(),(int)boxSegLine.get(i).getY2());
			if(boxElement.SagmentIsInRect(rect.getMin(), rect.getMax(),startSegPoint , endSegPoint, m)) {
				//if the segment is in a rectangle
				IsInRectFlag=true;
		}
		}
		if(IsInRectFlag) {//if the segment is in a rectangle i will remove it from the list
			boxSegLine.remove(boxSegLine.get(i));
		}
		}
	}
	/*
	 * this function check if a segment is intersects with any box line
	 */
	public static Boolean do_I_SeeYou(ArrayList<Line2D> boxLine,Line2D segment) {
		boolean flag=true;
		for(int i=0;i<boxLine.size()&&flag;i++) {
			if(segment.intersectsLine(boxLine.get(i))) {
				flag=false;
			}
		}
		return flag;
	}
	/*
	 * this function gets two GPS coordinate and return the segment between them
	 */
	public static Line2D getSegment(Point3D point1,Point3D point2,map m){
		pixel pixPoint1=m.gpsTopixel(point1);
		pixel pixPoint2=m.gpsTopixel(point2);
		return (new Line2D.Double((double)pixPoint1.getx(),(double)pixPoint1.gety(),(double)pixPoint2.getx(),(double)pixPoint2.gety()));

	} 
	/*
	 * this function gets two segments and check if the segments has the same points 
	 */
	public static boolean doWeHaveTheSamePoint (Line2D line1,Line2D line2) {
		if (line1.getX1()==line2.getX1() && line1.getY1()==line2.getY1()||line1.getX2()==line2.getX2() && line1.getY2()==line2.getY2() ||line1.getX1()==line2.getX2() && line1.getY1()==line2.getY2()||line1.getX2()==line2.getX1() && line1.getY2()==line2.getY1()) {
			return true;
		}
		return false;
	}
	
   
}
