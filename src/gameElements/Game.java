package gameElements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import File_format.csvReader;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.myGIS_element;
import GIS.myGIS_layer;
import Geom.Point3D;

/**
 * this class represents a game object, which include a list of all the packmans and the fruits in a single game.
 * @author moria and atara 
 *
 */

public class Game {

	public ArrayList<Packman> packmens;
	public ArrayList<Fruit> fruits;

	public Game() {
		packmens=new ArrayList<Packman>();
		fruits=new ArrayList<Fruit>();
	}
/*
 * this function create a game from a csv file 
 */
	public void fromCsvToGame (String fileName) {
		GIS_layer oneGame=csvReader.CSVreader(fileName);
		Iterator <GIS_element> it = oneGame.iterator();
		while(it.hasNext()) {
			myGIS_element lay=(myGIS_element)it.next();
			if(lay.type().equals("P")) {//if its a packman
				Point3D coord=(Point3D)lay.getGeom();
				double speed=lay.speed();
				double radius=lay.radius();
				this.packmens.add(new Packman(coord,speed,radius));
			}
			else {//if this is a fruit
				Point3D coord=(Point3D)lay.getGeom();
				double weight=lay.weight();
				this.fruits.add(new Fruit(coord,weight));
			}
		}
			
		
	}
/*
 * this function create a csv file from a game
 */
	public void fromGameToCsv (String outPut) {
		//my code here
		String fileName = outPut;
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		int numOfP=this.packmens.size();
		int numOfF=this.fruits.size();
		StringBuilder sb = new StringBuilder();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
		sb.append(',');
		sb.append("Lat");
		sb.append(',');
		sb.append("Lon");
		sb.append(',');
		sb.append("Alt");
		sb.append(',');
		sb.append("Speed/Weight");
		sb.append(',');
		sb.append("Radius");
		sb.append(',');
		sb.append(numOfP);
		sb.append(',');
		sb.append(numOfF);
		sb.append('\n');
		
		for(int i=0;i<this.packmens.size();i++) {
			Point3D cord= this.packmens.get(i).currentLocation();
			sb.append("P");
			sb.append(',');

			sb.append(this.packmens.get(i).getId());
			sb.append(',');
			
			sb.append(cord.x());
			sb.append(',');
			
			sb.append(cord.y());
			sb.append(',');
			
			sb.append(cord.z());
			sb.append(',');
			
			sb.append(this.packmens.get(i).speed());
			sb.append(',');
			
			sb.append(this.packmens.get(i).radius());
			sb.append('\n');
		}
		for(int i=0;i<this.fruits.size();i++) {
			Point3D cord= this.fruits.get(i).currentLocation();
			sb.append("F");
			sb.append(',');

			sb.append(this.fruits.get(i).id());
			sb.append(',');
			
			sb.append(cord.x());
			sb.append(',');
			
			sb.append(cord.y());
			sb.append(',');
			
			sb.append(cord.z());
			sb.append(',');
			
			sb.append(this.fruits.get(i).weight());
			sb.append('\n');
		}	
			pw.write(sb.toString());
			pw.close();

		}

	}