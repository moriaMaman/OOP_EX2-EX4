package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.myGIS_element;
import GIS.myGIS_layer;
import GIS.myGIS_project;
import Robot.Play;
/**
 * this class remove a CSV file and translate it into a GIS layer, also every line in the CSV file is a GIS element. 
 * @author Atara Zohar & Moria Maman
 *
 */

public class csvReader {

/**
 * this function gets a path of a CSV file- (in a similar format to the given example) 
 * and return an GIS_layer that contains the information in the file
 * @param path
 * @return
 */
	public static GIS_layer CSVreader(Play play1) {
		GIS_layer csvlayer= new myGIS_layer();
		GIS_project files= new myGIS_project(); 
		ArrayList<String> board_data = play1.getBoard();
		for(int i=0;i<board_data.size();i++) {
			String[] userInfo = board_data.get(i).split(",");
			csvlayer.add(new myGIS_element(userInfo));
		}
		files.add(csvlayer);
		return csvlayer;
	}

}
