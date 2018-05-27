package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static void initConnection() {
		
		
		 try
	        {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            System.out.println("Driver Loading Success!!");
	 
	        }catch(
	        ClassNotFoundException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	

	public static Connection getConnection() {
	
	Connection conn = null;
	
	try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
//		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.48:1521:xe", "hr", "hr");
		
		System.out.println("DB Connection Success");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return conn;
}

}
