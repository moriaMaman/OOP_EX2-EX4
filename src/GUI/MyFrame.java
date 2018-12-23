package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import Coords.map;
import Coords.pixel;
import Geom.Point3D;
import gameElements.Fruit;
import gameElements.Game;
import gameElements.Packman;
import gameElements.Game;
import gameElements.Packman;

/**  
 * this class Contains graphic to our game, the print of the game object, the path of the game he can draw a path of a game,
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
	private int gameNumber=0;
	private boolean runGame;
	private int gameNumberKml=0;


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
		MenuBar menuBar = new MenuBar();//the manu in the top of the frame
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
		MenuItem input1 = new MenuItem("packman");
		input.add(input1);
		input1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamer=1;
			}
		});
		MenuItem input2 = new MenuItem("fruit");
		input.add(input2);
		input2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamer=2;
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
			myImage = ImageIO.read(new File("Ariel1.jpg"));
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
		if(this.runGame==true) {//we run the game and print the path on the screen
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
			runGame=false;
		}
				
		Iterator <Packman> itP = this.games.packmens.iterator();
		Iterator <Fruit> itF = this.games.fruits.iterator();
		while(itP.hasNext()) {//print the packman ob the screen
			Packman pac=itP.next();
			g.setColor(Color.YELLOW);
			pixel pixelsP=m.gpsTopixel(pac.currentLocation());
			g.fillOval((int)(pixelsP.getx()-10), pixelsP.gety()-10, 20, 20);

		} 
		while(itF.hasNext()) {//print the fruits on the screen
			Fruit fru=itF.next();
			g.setColor(Color.green);
			pixel pixelsF=m.gpsTopixel(fru.currentLocation());
			g.fillOval(pixelsF.getx()-5, pixelsF.gety()-5, 10, 10);
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg) {
		x = arg.getX();
		y = arg.getY();
		if(gamer==1){//press packman
			map m=new map(this.getWidth(),this.getHeight());
			pixel pix=new pixel(x,y);
			Point3D p2g=m.pixelToGps(pix);
			System.out.println(this.getWidth());
			Packman p=new Packman(p2g,1,1);
			games.packmens.add(p);
		}
		if(gamer==2){//press fruit
			map m=new map(this.getWidth(),this.getHeight());
			pixel pix=new pixel(x,y);
			Point3D p2g=m.pixelToGps(pix);
			Packman p=new Packman(p2g,1,1);
			Fruit f=new Fruit(p2g,1);
			games.fruits.add(f);
		}
		
		repaint();

	}
	
	/**
	 * the function made with help from 
	 * https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java -for the load file
	 */
	public void loadFile() {
		 JFileChooser chooser = new JFileChooser();
	        int returnVal = chooser.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	games.fromCsvToGame(chooser.getSelectedFile().getAbsolutePath());
	        }
	        repaint();
	}
	
	/**
	 * clean the screen from all packman and fruits
	 */
	public void clearGame() {
		games.fruits.clear();
		games.packmens.clear();
		repaint();
	}
	/**
	 * save the game in scv file in the project folder
	 */
	public void saveFile() {
			gameNumber++;
			String outPutFile="game"+String.valueOf(gameNumber)+".csv";
			games.fromGameToCsv(outPutFile);
			System.out.println(gameNumber);
		
	}
	
	/**
	 * draw path from packman to the fruits he eats
	 */
	public void runOption() {
		this.runGame=true;
		repaint();
	
}
	/**
	 * save the game to KML to the project folder and show how the fruits despair
	 */
	public void SaveToKml() {
		gameNumberKml++;
		String outPutFile="game"+String.valueOf(gameNumberKml)+".kml";
		Path2KML.writeFileKML(outPutFile,this.games);
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
