package it.unipv.ingsw.d20.DBMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RdbOperations {
	
	Connection con;
	Statement st;
	ResultSet rs;

	public Connection connect() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://34.65.222.216:3306/prova","root",""); 
		
			//nasce per un problema relativo all'ora	///ingsw20?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
		}catch(Exception e){ System.out.println(e);}  			// TODO Auto-generated catch block
	
	return con;
	}  
	
	
	public String getAddressById(String Id) {
		
		this.connect();
		String query = "SELECT Address FROM Vending WHERE idVending = '" + Id + "'";
		String result = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}
	
	public void addVending(String Id, String Address) {
		this.connect();
		String query = "INSERT INTO Vending values ('" + Id +"','"+ Address + "')";
		try {
			st = con.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
