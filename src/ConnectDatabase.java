import java.sql.*;

import javax.swing.JOptionPane;
public class ConnectDatabase {
	Connection conn=null;
	public static Connection Databaseconnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:studentgr.sqlite");
			//System.out.print("Success");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
