package GIS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;

public class projectMeta_data implements Meta_data {

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
		//return 0;
	}
	public long getTime() {
		return getUTC();
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}


}
