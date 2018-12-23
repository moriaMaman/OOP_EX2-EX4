package GIS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class elementMeta_data implements Meta_data {
	public static void main(String[] args) {
		 
	}
	String [] line;
    String name;
    
    public elementMeta_data(String [] l)
    {
    	line = l;
    	this.name=line[1];
    	
    } 
    /**
     * this function return a date and time into a milliseconds.
     * we use https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
     * for help.
     */
	@Override 
	public long getUTC() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try {
			date = dateFormat.parse(dateFormat.format(date));
			long timeInMillis = date.getTime();
			 return timeInMillis;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public long getTime() {
		return getUTC();
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}

}
