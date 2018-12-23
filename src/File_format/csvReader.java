package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.myGIS_element;
import GIS.myGIS_layer;
import GIS.myGIS_project;
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
	public static GIS_layer CSVreader(String path) {		
		String csvFile = path;
		String line = "";
		String cvsSplitBy = ",";
		GIS_layer csvlayer= new myGIS_layer();
		int i=0;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			GIS_project files= new myGIS_project(); 
			while ((line = br.readLine()) != null) 
			{
				if(i>0) {//The first line in the file is irrelevant
					String[] userInfo = line.split(cvsSplitBy);
					csvlayer.add(new myGIS_element(userInfo));
				}
				i++;
			}
			files.add(csvlayer);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return csvlayer;
	}


}
