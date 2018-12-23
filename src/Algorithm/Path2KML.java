package Algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.myGIS_element;
import Geom.Point3D;
import gameElements.Fruit;
import gameElements.Game;
import gameElements.Packman;

/**
 * this class convert a game to a kml file
 * The file shows the disappearance of the fruits depending on the time they were eaten
 *A yellow thumbtack will represent a packman
 *A red thumbtack will represent a fruit
 * @author Atara Zohar and Moria Maman
 *
 */

public class Path2KML {
	public static void writeFileKML(String output,Game myGame) {
		int idP=0;
		int idF=0;
		ArrayList<String> content = new ArrayList<String>();
		 Calendar now = Calendar.getInstance();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+
				"<Document>\n"+"<Style id=\"yellow\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>"
				+"</Icon></IconStyle></Style><Style id=\"red\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>"
				+ "</Icon></IconStyle></Style>";
		content.add(kmlstart);
		String kmlend = "</Document>\n"+"</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			//pw = new PrintWriter(new FileWriter(output));
			ShortestPathAlgo.pathAlgo(myGame);
			Iterator <Packman> itP = myGame.packmens.iterator();
			while(itP.hasNext()) {
				Packman p=itP.next();
				idP++;
				String kmlelement =	"<Placemark>\n"+
						"<description>\n"+
						"<type>"+"packman"+"</type>\n"+
						"<id>"+ "ID:"+ idP+"</id>\n"+
						"</description>\n"+
						"<styleUrl>#yellow</styleUrl>\n"+
						"<Point>\n" +
						"<coordinates>"+p.currentLocation().y()+","+p.currentLocation().x()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";
				content.add(kmlelement);
				Iterator <Fruit> itF= p.eatList.iterator();
				while(itF.hasNext()) {
					Fruit fru=itF.next();
					idF++;
					Time t1 = new Time (p.getUtc());
					Time t2 = new Time (p.getUtc()+(long)(fru.getTime()*1000));
				     kmlelement ="<Placemark>\n"+
				    		"<TimeSpan><begin>"+"2017-12-01T"+t1+"</begin>"
							+"<end>2017-12-01T"+t2+"</end>\n</TimeSpan>"+
							"<description>\n"+
							"<type>"+"F"+"\n"+"</type>\n"+
							"<id>"+"\n"+ "ID:"+"\n"+ idF+"</id>\n"+
							"</description>\n"+
							"<styleUrl>#red</styleUrl>\n"+
							"<Point>\n" +
							"<coordinates>"+fru.currentLocation().y()+","+fru.currentLocation().x()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelement);  
				}
				idF=0;
			}
				content.add(kmlend);
				String csv = content.toString().replace("[", "").replace("]", "");
				bw.write(csv);
				bw.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	

}