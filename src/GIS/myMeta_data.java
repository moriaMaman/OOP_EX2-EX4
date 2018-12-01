package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class myMeta_data implements Meta_data {
	public static void main(String[] args) {
		 
	}
	String [] line;
    String MAC;
    String SSID;
    String AuthMode;
    int Channel;
    String firstSeen;
    String type;
    int RSSI;
    int AccuracyMeters;
    public myMeta_data(String [] l)
    {
    	line = l;
    	this.SSID=line[1];
    	this.firstSeen=line[3];
    	this.type=line[10];
    	this.MAC=line[0];
    	this.AuthMode=line[2];
    	this.Channel=Integer.parseInt(line[4]);
    	this.RSSI=Integer.parseInt(line[5]);
    	this.AccuracyMeters=Integer.parseInt(line[9]);
    } 
    /**
     * this function return a date and time into a milliseconds.
     * we use https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
     * for help.
     */
	@Override 
	public long getUTC() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(firstSeen);
			long timeInMillis = date.getTime();
			 return timeInMillis;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}

}
