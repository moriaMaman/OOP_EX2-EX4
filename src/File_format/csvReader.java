package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class csvReader {
	
	
	public ArrayList<String []> csvReader(String path)
	{
		ArrayList<String []> csvLine=new ArrayList<String []>();
    	String csvFile = path;
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				csvLine.add(userInfo);
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return csvLine;
	}
	

}
