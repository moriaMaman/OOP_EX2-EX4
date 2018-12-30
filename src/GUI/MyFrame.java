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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algorithm.Path;
import Algorithm.Path2KML;
import Algorithm.ShortestPathAlgo;
import Algorithm.closestFruit;
import Coords.GeoBox;
import Coords.LatLonAlt;
import Coords.MyCoords;
import Coords.map;
import Coords.pixel;
import Geom.Point3D;
import Threads.packmanThread;
import gameElements.Fruit;
import gameElements.Game;
import gameElements.Ghost;
import gameElements.Packman;
import gameElements.Player;
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


	public MyFrame() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{
		this.runGame=false;
		this.getWidth();
		this.getHeight();

		games=new Game();
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("file"); 
		MenuItem item1 = new MenuItem("load");
		MenuItem item2 = new MenuItem("save");
		MenuItem item3 = new MenuItem("clear");
		MenuItem item4 = new MenuItem("saveToKml");
		
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});
		
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		
		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearGame();
				repaint();
			}
		});
		
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveToKml();
			}
		});
		
		
		

		Menu input = new Menu("input");
//		MenuItem input1 = new MenuItem("packman");
//		input.add(input1);
//		input1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				gamer=1;
//			}
//		});
//		MenuItem input2 = new MenuItem("fruit");
//		input.add(input2);
//		input2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				gamer=2;
//			}
//		});
		
//		MenuItem input4 = new MenuItem("ghost");
//		input.add(input4);
//		input4.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				gamer=3;
//			}
//		});

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
		menuBar.add(file);
		menuBar.add(input);

		file.add(item1);
		file.add(item2);
		file.add(item3);
		file.add(item4);

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
		g.drawImage(myImage, 0, 0,getWidth()-5,getHeight()-5, this);
		map m=new map(this.getWidth(),this.getHeight());
		if(this.runGame==true) {
			ShortestPathAlgo.pathAlgo(this.games);	
			Iterator <Packman> itp = this.games.packmens.iterator();
			while(itp.hasNext()) {
				Packman p=itp.next();
				int index=0;
				Iterator <Fruit> itf = p.eatList.iterator();
				while(itf.hasNext()) {
					Fruit f=itf.next();
					pixel c2pPackman=m.gpsTopixel(p.currentLocation());
					pixel c2pFruit=m.gpsTopixel(f.currentLocation());
					if(index==0) {
						g.setColor(Color.BLUE);
						g.drawLine(c2pPackman.getx(), c2pPackman.gety(), c2pFruit.getx(), c2pFruit.gety());		
					}
					else {
						pixel priviosP=m.gpsTopixel(p.eatList.get(index-1).currentLocation());
						g.setColor(Color.BLUE);
						g.drawLine(priviosP.getx(), priviosP.gety(), c2pFruit.getx(), c2pFruit.gety());	
					}
					index++;
				}
			}
		}
				
		Iterator <Packman> itP = this.games.packmens.iterator();
		Iterator <Fruit> itF = this.games.fruits.iterator();
		Iterator <GeoBox> itB = this.games.boxes.iterator();
		Iterator <Ghost> itG = this.games.ghosts.iterator();
		while(itP.hasNext()) {
			Packman pac=itP.next();
			g.setColor(Color.YELLOW);
			pixel pixelsP=m.gpsTopixel(pac.newLocation);
			g.fillOval((int)(pixelsP.getx()-10), pixelsP.gety()-10, 20, 20);

		} 
		//display the fruits
		while(itF.hasNext()) {
			Fruit fru=itF.next();
			g.setColor(Color.green);
			pixel pixelsF=m.gpsTopixel(fru.currentLocation());
			g.fillOval(pixelsF.getx()-5, pixelsF.gety()-5, 10, 10);
		}
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
		while(itG.hasNext()) {
			Ghost ghost=itG.next();
			g.setColor(Color.MAGENTA);
			pixel p=m.gpsTopixel(ghost.getCurrentLocation());
			g.fillOval((int)(p.getx()-10), p.gety()-10, 20, 20);
		}
		
		g.setColor(Color.pink);
		pixel p=m.gpsTopixel(this.games.player.getCurrentLocation());
		g.fillOval((int)(p.getx()-20), p.gety()-20, 40, 40);
		
	}
	@Override
	public void mouseClicked(MouseEvent arg) {
		x = arg.getX();
		y = arg.getY();
//		if(gamer==1){//packman
//			map m=m=new map(this.getWidth(),this.getHeight());
//			pixel pix=new pixel(x,y);
//			Point3D p2g=m.pixelToGps(pix);
//			pixel pi=m.gpsTopixel(new Point3D(32.10333584,35.20895676));
//			Packman p=new Packman(p2g,1,1);
//			games.packmens.add(p);
//		}
//		if(gamer==2){//fruit
//			map m=m=new map(this.getWidth(),this.getHeight());
//			pixel pix=new pixel(x,y);
//			Point3D p2g=m.pixelToGps(pix);
//			Fruit f=new Fruit(p2g,1);
//			games.fruits.add(f);
//		}
//		if(gamer==3) {//gohst
//			map m=m=new map(this.getWidth(),this.getHeight());
//			pixel pix=new pixel(x,y);
//			Point3D p2g=m.pixelToGps(pix);
//			Ghost g=new Ghost(p2g,1,1);
//			games.ghosts.add(g);
//			
//		}
		if(gamer==4) {//player
			map m=m=new map(this.getWidth(),this.getHeight());
			pixel pix=new pixel(x,y);
			Point3D p2g=m.pixelToGps(pix);
			Player p=new Player(1,1,p2g);
			games.player=p;
			
		}
		
		repaint();

	}
	//we use this code: https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java -for the load file
	public void loadFile() {
		 JFileChooser chooser = new JFileChooser();
	        int returnVal = chooser.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	games.fromCsvToGame(chooser.getSelectedFile().getAbsolutePath());
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
	public void saveFile() {
			gameNumberCsv++;
			String outPutFile="game"+String.valueOf(gameNumberCsv)+".csv";
			games.fromGameToCsv(outPutFile);
		
	}
	public void runOption() {
		ShortestPathAlgo.pathAlgo(this.games);	
		this.runGame=true;
		repaint();
		
		
		Iterator <Packman> itp = this.games.packmens.iterator();
		while(itp.hasNext()) {
			Packman p=itp.next();
			packmanThread pt=new packmanThread(p,this);
			pt.start();
		}
		runGame=false;
	
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
