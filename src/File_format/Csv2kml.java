package File_format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//import Algorithm.multiCsv;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.myGIS_element;
import Geom.Point3D;
import Robot.Play;
/**
 * This class converts a one or multiple CSV files to KML file
 * @author Atara Zohar & Moria Maman
 *
 */
public class Csv2kml {

	/**
	 * this function get a name of a CSV file and converts him to kml
	 * @param path
	 */
	public static void Csv2kml(String path,String output) {
		File fileEntry=new File(path);
		if(fileEntry.isDirectory())//if there is more then one file 
		{
			//GIS_project files=multiCsv.multiCsv(fileEntry);
			//p2kml(files,output);

		}
		else {//if there is only one csv file
			Play play=new Play(path);
			GIS_layer csvlayer= csvReader.CSVreader(play);
			writeFileKML(csvlayer,output);	
		}
	}
	/**
	 * this function gets a GIS_layer which contains information from a CSV file and an output file name,and make a new KML 
	 * file that represents this data
	 * @param csvlayer
	 * @param output
	 */
	public static void writeFileKML(GIS_layer csvlayer, String output) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+ "<Document>\n";
		content.add(kmlstart);
		String kmlend = "</Document>\n"+"</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<GIS_element> it = csvlayer.iterator();
			while(it.hasNext()) {
				myGIS_element element =(myGIS_element) it.next();
				Point3D cord=(Point3D) element.getGeom();
				if(element.type().equals("p")) {
					String kmlelement =	"<Placemark>\n"+
							"<type>"+"packman"+"</type>\n"+
							"<id>"+ "ID:"+ element.id()+
							"speed:"+ element.speed()+
							"radius:"+element.radius()+
							"</description>\n"+
							"<Point>\n" +
							"<coordinates>"+cord.x()+","+cord.y()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelement);
				}
				else {
					String kmlelement =	"<Placemark>\n"+
							"<type>"+"fruit"+"</type>\n"+
							"<id>"+ "ID:"+ element.id()+
							"weigth:"+ element.weight()+
							"</description>\n"+
							"<Point>\n" +
							"<coordinates>"+cord.x()+","+cord.y()+"</coordinates>" +
							"</Point>\n" +
							"</Placemark>\n";
					content.add(kmlelement);
				}
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
	/**
	 * this function gets a GIS_project which contains information from multiple CSV files and an output file name,
	 * and make a new KML file that represents all this files data
	 * @param p
	 * @param output
	 */
	public  static void p2kml(GIS_project p,String output) {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+ "<Document>\n";
		content.add(kmlstart);
		String kmlend = "</Document>\n"+"</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator <GIS_layer> it1 = p.iterator();
			while(it1.hasNext())//run over the GIS project by iterator
			{
				GIS_layer lay=it1.next();
				Iterator<GIS_element> it2 = lay.iterator();
				while(it2.hasNext()) {////run over the GIS layer by iterator
					GIS_element el=it2.next();
					myGIS_element element =(myGIS_element) el;
					Point3D cord=(Point3D) element.getGeom();
					if(element.type().equals("p")) {
						String kmlelement =	"<Placemark>\n"+
								"<type>"+"packman"+"</type>\n"+
								"<id>"+ "ID:"+ element.id()+
								"speed:"+ element.speed()+
								"radius:"+element.radius()+
								"</description>\n"+
								"<Point>\n" +
								"<coordinates>"+cord.x()+","+cord.y()+"</coordinates>" +
								"</Point>\n" +
								"</Placemark>\n";
						content.add(kmlelement);
					}
					else {
						String kmlelement =	"<Placemark>\n"+
								"<type>"+"fruit"+"</type>\n"+
								"<id>"+ "ID:"+ element.id()+
								"weigth:"+ element.weight()+
								"</description>\n"+
								"<Point>\n" +
								"<coordinates>"+cord.x()+","+cord.y()+"</coordinates>" +
								"</Point>\n" +
								"</Placemark>\n";
						content.add(kmlelement);
					}
				}
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
