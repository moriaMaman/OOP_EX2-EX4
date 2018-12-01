package File_format;

import java.io.File;

import GIS.GIS_project;
import GIS.myGIS_layer;
import GIS.myGIS_project;
/**
 * this class has a function thet need to run over a folder of files and fint the scv one and translate it to a GIS project object.
 * @author Atara Zohar & Moria Maman
 *
 */

public class multiCsv {
/**
 * this function gets a path of a multiple CSV files- (in a similar format to the given example) 
 * @param folder
 * @return GIS_project that contains a set of files and the information in each file
 */
	public static GIS_project multiCsv(final File folder) {
		GIS_project files =new myGIS_project();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				multiCsv(fileEntry);
			} 
			else {
				String name=fileEntry.getName();
				if(fileEntry.getName().endsWith(".csv")) {//"read" only the CSV tape files
					files.add((csvReader.CSVreader(fileEntry.getPath())));
				}
			}
		}
		return files;
	}
}
