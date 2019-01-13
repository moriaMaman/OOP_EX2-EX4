package DataBase;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * this class will comper our avrage points to all other players.
 * we used the SQL
 * @author USER
 *
 */

public class comperData {
	public static void main(String[] args)
	{
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			
			
			Statement statement = connection.createStatement();
			
			//select data for example one
			System.out.println("*******************1********************");
			String allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = 2128259830";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.1");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = 2128259830";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.1");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for example two
			System.out.println("*******************2********************");
			allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = 1149748017";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.2");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = 1149748017";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the ang point to game num.2");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for example three
			System.out.println("*******************3********************");
			allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = 683317070";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.3");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = 683317070";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.3");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for example four
			System.out.println("*******************4********************");
			allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble =1193961129";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.4");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = 1193961129";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.4");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for example five
			System.out.println("*******************5********************");
			allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = 1577914705";
		    resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.5");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = 1577914705";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.5");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for ecample six
			System.out.println("*******************6********************");
			allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = -1315066918";
			 resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.6");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = -1315066918";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.6");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for example seven
			System.out.println("*******************7********************");
			allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = -1377331871";
		 resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.7");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = -1377331871";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.7");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for example 8
			System.out.println("*******************8********************");
			 allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = 306711633";
			 resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.8");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = 306711633";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.8");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			//select data for example 9
			System.out.println("*******************9********************");
			 allCustomersQuery = "SELECT avg(Point) FROM logs where FirstID = 207595505 and SomeDouble = 919248096";
			 resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("my avrage point to game num.9");
			while(resultSet.next())
			{
				System.out.println(resultSet.getDouble(1));
			}
			
			//select data for example 9
			allCustomersQuery = "SELECT avg(point) FROM logs where SomeDouble = 919248096";
			resultSet = statement.executeQuery(allCustomersQuery);
			System.out.println("the avg point to game num.9");
			while(resultSet.next())
			{
				System.out.println(	resultSet.getDouble(1) );
			}
			
			resultSet.close();		
			statement.close();		
			connection.close();		
		}
		
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
