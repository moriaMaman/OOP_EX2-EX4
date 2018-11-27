package File_format;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="C:\\Users\\USER\\Desktop\\WigleWifi_20171203085618.csv";
		ArrayList<String[] > s = new ArrayList<String[] >();
		csvReader ad = new csvReader();
		s = ad.csvReader(path);
		String[] sa = s.get(3);
		System.out.println(sa[1]);
	}

}
