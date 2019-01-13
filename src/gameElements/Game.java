package gameElements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.GeoBox;
import Coords.LatLonAlt;
import File_format.csvReader;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.myGIS_element;
import GIS.myGIS_layer;
import Geom.Point3D;
import Robot.Play;

/**
 * this class represents a game object, which include a list of all the packmans and the fruits in a single game.
 * @author moria and atara 
 *
 */

public class Game {

	public ArrayList<Packman> packmens;
	public ArrayList<Fruit> fruits;
	public ArrayList<Ghost> ghosts;
	public ArrayList<GeoBox> boxes;
	public Player player;
	private int BoxIndex;
	public Play play;

	public Game() {
		this.packmens=new ArrayList<Packman>();
		this.fruits=new ArrayList<Fruit>();
		this.ghosts=new ArrayList<Ghost>();
		this.boxes=new ArrayList<GeoBox>();
		this.player=new Player();
		BoxIndex=0;
		this.play=new Play();
	}

	}