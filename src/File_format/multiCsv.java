package File_format;

import java.io.File;

import GIS.GIS_project;
import GIS.myGIS_layer;
import GIS.myGIS_project;

public class multiCsv {
/*
 * this function gets a path of a multiple CSV files- (in a similar format to the given example) 
 * and return an GIS_project that contains a set of files and the information in each file
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
