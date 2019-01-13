package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algorithm.Path;
import Algorithm.Path2KML;
import Algorithm.ShortestPathAlgo;
import Algorithm.boxElement;
import Algorithm.closestFruit;
import Algorithm.closestFruitAlgo;
import Algorithm.comperator;
import Algorithm.pathAlgo;
import Algorithm.sortComperator;
import Coords.Cords;
import Coords.GeoBox;
import Coords.LatLonAlt;
import Coords.MyCoords;
import Coords.map;
import Coords.pixel;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.myGIS_element;
import GIS.myGIS_layer;
import Geom.Point3D;
import Robot.Play;
import Threads.packmanThread;
import Threads.playerThreads;
import gameElements.Fruit;
import gameElements.Game;
import gameElements.Ghost;
import gameElements.Packman;
import gameElements.Player;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
import gameElements.Game;
import gameElements.Packman;


/**  
 * this class displays a game board
 * @author Atara Zohar & Moria Maman
 *
 */

public class MyFrame  extends JFrame implements MouseListener
{

	public static void main(String[] args)
	{
		
		MyFrame window = new MyFrame();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public BufferedImage myImage;
	private int gamer;
	private JPanel _panel;
	private Graphics _paper;
	private Game games;
	private map m;
	private int gameNumberCsv=0;
	private int gameNumberKml=0;
	private boolean runGame;
	private double angle=0;
	public Play play1;
	//private paintFrame paintF;
	private  Comparator <closestFruitAlgo> cmpByDist= new comperator();
	public ArrayList<Point3D> pointsPath=new ArrayList<Point3D>();

	public MyFrame() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{
		//		paintF=new paintFrame ();
		//		add(paintF);

		this.runGame=false;
		this.getWidth();
		this.getHeight();

		games=new Game();
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("file"); 
		MenuItem item1 = new MenuItem("load");
		MenuItem item3 = new MenuItem("clear");

		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});
	
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearGame();
				repaint();
			}
		});


		Menu input = new Menu("input");

		MenuItem input5 = new MenuItem("player");
		input.add(input5);
		input5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamer=4;
			}
		});
		MenuItem input3 = new MenuItem("run");
		input.add(input3);
		input3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				runOption();

			}
		});
		MenuItem input7 = new MenuItem("runAlgo");
		input.add(input7);
		input7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runAlgoOption();
			}
		});
		menuBar.add(file);
		menuBar.add(input);

		file.add(item1);
		file.add(item3);

		this.setMenuBar(menuBar);

		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	int x = -1;
	int y = -1;


	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0,getWidth(),getHeight(), this);
		map m=new map(this.getWidth(),this.getHeight());
		Iterator <Packman> itP = this.games.packmens.iterator();
		Iterator <Fruit> itF = this.games.fruits.iterator();
		Iterator <GeoBox> itB = this.games.boxes.iterator();
		Iterator <Ghost> itG = this.games.ghosts.iterator();
		synchronized(itP) {
			while(itP.hasNext()) {
				Packman pac=itP.next();
				g.setColor(Color.YELLOW);
				pixel pixelsP=m.gpsTopixel(pac.newLocation);
				g.fillOval((int)(pixelsP.getx()-10), pixelsP.gety()-10, 20, 20);

			} 
		}
		//display the fruits
		synchronized(itF) {
			while(itF.hasNext()) {
				Fruit fru=itF.next();
				g.setColor(Color.green);
				pixel pixelsF=m.gpsTopixel(fru.currentLocation());
				g.fillOval(pixelsF.getx()-5, pixelsF.gety()-5, 10, 10);
			}
		}
		synchronized(itB) {
			while(itB.hasNext()) {
				GeoBox box=itB.next();
				g.setColor(Color.black);
				Point3D min=(Point3D)box.getMin();
				Point3D max=(Point3D)box.getMax();
				pixel minP=m.gpsTopixel(min);
				pixel maxP=m.gpsTopixel(max);
				int RecHigth=minP.gety()-maxP.gety();
				int RecWidgh=maxP.getx()-minP.getx();
				pixel startRec=new pixel(minP.getx(),maxP.gety());
				g.fillRect(startRec.getx(), startRec.gety(), RecWidgh, RecHigth);
			}
		}
		synchronized(itG) {
			while(itG.hasNext()) {
				Ghost ghost=itG.next();
				g.setColor(Color.MAGENTA);
				pixel p=m.gpsTopixel(ghost.getCurrentLocation());
				g.fillOval((int)(p.getx()-10), p.gety()-10, 20, 20);
			}
		}

		g.setColor(Color.pink);
		pixel p=m.gpsTopixel(this.games.player.getCurrentLocation());
		g.fillOval((int)(p.getx()-12), p.gety()-12, 25, 25);
		if(gamer==5) {
			this.play1.rotate(this.angle);
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg) {
		x = arg.getX();
		y = arg.getY();
		if(gamer==4) {//player
			map m=new map(this.getWidth(),this.getHeight());
			pixel pix=new pixel(x,y);
			Point3D p2g=m.pixelToGps(pix);
			Player p=new Player(1,1,p2g);
			games.player=p;

		}
		if (gamer==5) {
			map m=new map(this.getWidth(),this.getHeight());
			pixel nowPress= new pixel(arg.getX(),arg.getY());
			pixel p=(m.gpsTopixel(games.player.getCurrentLocation()));
			double newAngle=m.pixelangel(p,nowPress); 
			this.angle=newAngle;
		}

		repaint();

	}
	//we use this code: https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java -for the load file
	public void loadFile() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			this.play1=new Play(chooser.getSelectedFile().getAbsolutePath());
			updateData();
			//games.fromCsvToGame(chooser.getSelectedFile().getAbsolutePath());
		}
		repaint();
	}

	public void updateData () {
		clearGame();
		ArrayList<String> data=this.play1.getBoard();
		GIS_layer csvlayer=new  myGIS_layer();
		for(int i=0;i<data.size();i++) {
			String[] userInfo = data.get(i).split(",");
			csvlayer.add(new myGIS_element(userInfo));
		}
		Iterator <GIS_element> it = csvlayer.iterator();
		int BoxIndex=0;
		while(it.hasNext()) {
			myGIS_element lay=(myGIS_element)it.next();
			if(lay.type().equals("M")) {//if its a packman
				Point3D coord=(Point3D)lay.getGeom();
				double speed=lay.speed();
				double radius=lay.radius();
				this.games.player.setCurrentLocation(coord);
			}
			if(lay.type().equals("P")) {//if its a packman
				Point3D coord=(Point3D)lay.getGeom();
				double speed=lay.speed();
				double radius=lay.radius();
				this.games.packmens.add(new Packman(coord,speed,radius));
			}
			if(lay.type().equals("G")) {//if this is ghost
				Point3D coord=(Point3D)lay.getGeom();
				double speed=lay.speed();
				double radius=lay.radius();
				this.games.ghosts.add(new Ghost(coord,radius,speed));

			}
			if(lay.type().equals("B")) {//if this is a box
				Point3D coord=(Point3D)lay.getGeom();
				Point3D coord2=(Point3D)lay.getGeom2();
				double weight=lay.weight(); 
				LatLonAlt L=new LatLonAlt(coord.x(),coord.y(),coord.z());
				LatLonAlt L2=new LatLonAlt(coord2.x(),coord2.y(),coord2.z());
				this.games.boxes.add(new GeoBox(L,L2));
				this.games.boxes.get(BoxIndex).setWeight(weight);
				BoxIndex++;
			}
			if(lay.type().equals("F")) {//if this is a fruit
				Point3D coord=(Point3D)lay.getGeom();
				double weight=lay.weight();
				this.games.fruits.add(new Fruit(coord,weight));
			}
		}

		repaint();
	}

	public void clearGame() {
		games.fruits.clear();
		games.packmens.clear();
		games.player.setCurrentLocation(new Point3D(0,0));
		games.boxes.clear();
		games.ghosts.clear();
		repaint();
	}

	public void runOption() {
		gamer=5;
		this.play1.setIDs(207595505, 318286150);
		this.play1.setInitLocation(games.player.getCurrentLocation().x(),games.player.getCurrentLocation().y());
		this.play1.start();
		Thread t=new Thread() {
			public void run() {
				while(play1.isRuning()) {
					updateData();
					try {
						Thread.sleep(100);
						repaint();
					}
					catch (Exception e){
					}

				}
			}
		};
		t.start();
	}
	public void runAlgoOption() {
		this.play1.setIDs(207595505, 318286150);
		this.play1.setInitLocation(games.player.getCurrentLocation().x(),games.player.getCurrentLocation().y());
		this.play1.start();
		playerThreads t=new playerThreads(this.games.player,this);
		t.start();
		
	}

	public void SaveToKml() {
		gameNumberKml++;
		String outPutFile="game"+String.valueOf(gameNumberKml)+".kml";
		Path2KML.writeFileKML(outPutFile,this.games);

	}

	public ArrayList<Point3D> packmanJump(Packman p){
		ArrayList<Point3D> steps=new ArrayList<Point3D>();
		double a;  
		double b;
		double dist;
		double stepsSize=0.5;
		Point3D startPoint=new Point3D(p.currentLocation());
		MyCoords m=new MyCoords();
		Iterator <Fruit> itF = p.eatList.iterator();
		while(itF.hasNext()) {
			Fruit fruit=itF.next();
			dist=m.distance3d(startPoint, fruit.currentLocation());
			a=(stepsSize/dist)*(fruit.currentLocation().x()-startPoint.x())+fruit.currentLocation().x();
			b=(stepsSize/dist)*(fruit.currentLocation().y()-startPoint.y())+fruit.currentLocation().y();
			startPoint=fruit.currentLocation();
			steps.add(new Point3D(a,b));
		}
		return steps;
	}

	public synchronized void threadMethod() {
		repaint();
	}
	/*
	 * this function help in a case of automatic game, and will calculate the shortest path for every player to get to a chosen fruit. 
	 */
	public void algo() {
		
		
		map m=new map(this.getWidth(),this.getHeight());	
		
		MyCoords coord=new MyCoords();
		ArrayList <GeoBox> box=new ArrayList <GeoBox>(this.games.boxes);//an arrayList of all the boxes
		ArrayList<Point3D> points=pathAlgo.getEdges(this.games.boxes,m);//an arrayList of the boxes vertices
		ArrayList<Line2D> lines= pathAlgo.boxLines(this.games.boxes,m);//an arrayList of all the boxes lines
		ArrayList <closestFruitAlgo> fruitList=new ArrayList <closestFruitAlgo>();

		
		Iterator <Fruit> it2 = this.games.fruits.iterator();
		while(it2.hasNext()) {//As long as there is fruits in the game
			Fruit f=it2.next();
			Iterator <Fruit> it = this.games.fruits.iterator();
			while(it.hasNext()) {//gets a priority list of the closest fruit for each player. 
				Fruit fruits=it.next();
				closestFruitAlgo closeF=new closestFruitAlgo(this.games.player,fruits);
				fruitList.add(closeF);
				fruitList.sort(this.cmpByDist);
			}
			
			Line2D seg=pathAlgo.getSegment(this.games.player.getCurrentLocation(), fruitList.get(0)._fruit.currentLocation(), m);
			if(pathAlgo.do_I_SeeYou(lines, seg)) {//check if the player can go directly to the fruit.
				double[] azimuth=coord.azimuth_elevation_dist(this.games.player.getCurrentLocation(), fruitList.get(0)._fruit.currentLocation());
				this.angle=azimuth[0];
				repaint();
			}
			else {//if the player can't go directly to the destination point
				Point3D sourcePoint=this.games.player.getCurrentLocation();
				Point3D destPoint=fruitList.get(0)._fruit.currentLocation();

				Graph G = new Graph(); 
				String source = "a";
				String target = "b";
				G.add(new Node(source)); // Node "a" (0)
				for(int i=1;i<points.size();i++) {//named the relevant points
					Node d = new Node(""+i);
					G.add(d);
				}
				G.add(new Node(target)); // Node "b" (15)
				
				
		//the player segments
		ArrayList<Line2D> segmentsPlayer= pathAlgo.segmentOfPoint(sourcePoint,lines,points,m);//get the player segments
		Iterator <Line2D> it3 = segmentsPlayer.iterator();
		while(it3.hasNext()) {
			Line2D segments=it3.next();
			boolean flag=true;
			for(int i=0;i<points.size()&&flag;i++) {//looking for the "name" of the second point of the segment
				Line2D line1=pathAlgo.getSegment(sourcePoint, points.get(i),m);
				Line2D line2=segments;
				//Check if the points are equal
				boolean ifCond=line1.getX1()==line2.getX1() && line1.getY1()==line2.getY1()&&line1.getX2()==line2.getX2() && line1.getY2()==line2.getY2();
				if(ifCond==true) {//if the points are equal
					Point3D segPoint=new Point3D(segments.getX2(),segments.getY2());
					G.addEdge("a",""+(i+1),sourcePoint.distance2D(segPoint));//add the segment to the graph
					flag=false;
				}
			}
		}
		
		
		//the box segments
		for(int i=0;i<points.size();i++) {
			ArrayList<Line2D> segmentsBox= pathAlgo.segmentOfPoint(points.get(i),lines,points,m);//get the box sements
			pathAlgo.boxSegment(segmentsBox, this.games.boxes, m);
			Iterator <Line2D> it4 = segmentsBox.iterator();
			while(it4.hasNext()) {
				boolean flag=true;
				Line2D segments=it4.next();
				for(int j=0;j<points.size()&&flag;j++) {//Search for the second point of the segment
					Point3D boxSegPoint=m.pixelToGps(new pixel((int)segments.getX2(),(int)segments.getY2()));
					pixel pixel1=m.gpsTopixel(points.get(j));
					pixel pixel2=new pixel((int)segments.getX2(),(int)segments.getY2());
					if(pixel1.getx()==pixel2.getx()&&pixel1.gety()==pixel2.gety()){//Check if the points are equal
						G.addEdge(""+(i+1),""+(j+1),points.get(i).distance2D(boxSegPoint));
						flag=false;
					}
				}
			}
			 
		}
		//the fruit segments
		ArrayList<Line2D> segmentsDest= pathAlgo.segmentOfPoint(destPoint,lines,points,m);//get the fruit segments
		Iterator <Line2D> itDest = segmentsDest.iterator();
		while(itDest.hasNext()) {
			Line2D segments=itDest.next();	
			for(int i=0;i<points.size();i++) {//search for the second point name 
				Line2D line1=pathAlgo.getSegment(destPoint, points.get(i),m);
				Line2D line2=segments;
				//check if the points are equal
				boolean ifCond=line1.getX1()==line2.getX1() && line1.getY1()==line2.getY1()&&line1.getX2()==line2.getX2() && line1.getY2()==line2.getY2();
				if(ifCond==true) {
					Point3D segPoint=new Point3D(segments.getX2(),segments.getY2());
					G.addEdge("b",""+(i+1),destPoint.distance2D(segPoint));//add the segments to the graph
				}
			}
		}
			Graph_Algo.dijkstra(G, source);
			Node b = G.getNodeByName(target);
			ArrayList<String> shortestPath = b.getPath();//get the shortest path by the "Dijkstra" algorithm calculate
			for(int i=0;i<shortestPath.size();i++) {
				if(i>0 && i<shortestPath.size()-1) {//if its not "a" or "b"
					int numPoint=Integer.parseInt(shortestPath.get(i));
				pointsPath.add(points.get(numPoint));
				}
				System.out.print(","+shortestPath.get(i));
			}
			}
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
