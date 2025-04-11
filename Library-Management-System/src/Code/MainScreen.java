package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class MainScreen{

  public void listBook(){

  }
  public void showUser(){

  }
  public static void main(String[] args) {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarysql", "root", "abcd*123");
			Statement myStat = myConn.createStatement();
			ResultSet myRs = myStat.executeQuery("select * from book");
			while(myRs.next()) {
				System.out.println(myRs.getString("ISBN"));
			}
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}