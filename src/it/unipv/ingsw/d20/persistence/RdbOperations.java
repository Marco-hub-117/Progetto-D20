package it.unipv.ingsw.d20.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingsw.d20.persistence.beveragecatalog.BvCatalogPOJO;
import it.unipv.ingsw.d20.persistence.beveragedescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.persistence.ingredientrecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.persistence.sale.SalePOJO;
import it.unipv.ingsw.d20.persistence.vending.VendingPOJO;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

/**
 * Classe che implementa la connessione con il database. Implementa tutte le possibili operazioni con esso.
 *
 */
public class RdbOperations {
	
	private Connection con;
	
	public RdbOperations () {
		this.con = null;
	}

	public Connection startConnection(Connection conn) { 
		
		String DbDriver = null;
		String DbUrl=null;
		String username=null;
		String password = null;
		
		DbDriver = "com.mysql.cj.jdbc.Driver";
		DbUrl = "jdbc:mysql://34.65.222.216:3306/prova"; // � possibile implementare una connessione a uno schema particolare, in questo caso usiamo di default lo schema "prova"
		username = "root";
		password = "";
		if (isOpen (conn)) // se la connessione al database � gi� aperta viene chiusa, per poi riaprirne una nuova
			closeConnection(conn); // conn viene chiusa.
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DbUrl,username,password); // apertura della connessione
			//nasce per un problema relativo all'ora	///ingsw20?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
			}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}  				
		return conn; // se tutto va a buon fine la connessione viene aperta e restituita.
	}
	
	public boolean isOpen(Connection conn) {
		if (conn == null) 
			return false;
		else 
			return true;
	}
	
	public Connection closeConnection (Connection conn) {
		if ( !isOpen(conn) ) // se la connessione � gi� chiusa.
			return null;
		try {
			conn.close(); // se la chiusura della connessione va a buon fine,
			conn = null;  // conn viene posto a null. Viene poi restituito alla fine del blocco try catch.
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn; // se tutto va a buon fine, la connessione viene chiusa e viene restituito il valore null.
	}
	
	// DI SEGUITO CI SARANNO LE QUERY RELATIVA ALLA TABLE VENDING
	
	public VendingMachineStatus getVendingStatusById(String Id) {
		
		VendingMachineStatus result = null;
		String query = QueryGenerator.getSelectFromQuery("Status", "Vending");
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = VendingMachineStatus.valueOf(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;

	}
	
	public ArrayList<VendingPOJO> getAllVending () {
		ArrayList<VendingPOJO> result = new ArrayList<>();
		String query = "SELECT * FROM Vending";
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				VendingPOJO res = new VendingPOJO(rs.getString(1),VendingMachineStatus.valueOf(rs.getString(1)));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	public void addVending(VendingPOJO vending) {	
		ArrayList<String> values = new ArrayList<>();
		values.add(vending.getIdVending());
		values.add(vending.getStringStatus());
		String query = QueryGenerator.getInsertIntoValuesQuery("Vending", values);
		
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); // usato per eseguire una query di inserimento.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
		
	}

	public void updateVendingStatus(String idVending,VendingMachineStatus newStatus) {
		
		String set = "Status = '"+newStatus+"'";
		String whereStatement = "idVending = '"+idVending+"'";
		String query = QueryGenerator.getUpdateSetQuery("Vending", set, whereStatement);
		System.out.println(query);
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); // usato per eseguire una query di inserimento.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
	}
	
	// DI SEGUITO CI SARANNO LE QUERY RELATIVE ALLA SALE
	
	public void addSale(SalePOJO sale) {
		
		ArrayList<String> values = new ArrayList<>();
		values.add(sale.getIdVending()); // primo attributo nella table
		values.add(sale.getIdBeverage()); // secondo attributo nella table
		values.add(sale.getDate()); // terzo attributo nella table
		
		String query = QueryGenerator.getInsertIntoValuesQuery("Sale", values);	
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); // usato per eseguire una query di inserimento.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
	}

	public SalePOJO getSaleByKey (String idVending,String date) {
		SalePOJO result = null;
		String whereStatement = "idVending = '"+idVending+"' and Date = '"+date+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", "Sale", whereStatement);
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = new SalePOJO (rs.getString(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		// Fare un controllo if result = null e lanciare una eccezione?
		return result;
		
	}

	public ArrayList<SalePOJO> getAllSaleByIdVending (String idVending) {
		ArrayList<SalePOJO> result = new ArrayList<>();
		String whereStatement = "idVending = '" +idVending+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", "Sale", whereStatement);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				SalePOJO res =  new SalePOJO (rs.getString(1),rs.getString(2),rs.getString(3));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		
		return result;
	}

	// DI SEGUITO CI SARANNO LE QUERY RELATIVE ALLA TABLE BvCatalog
	
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type) {
		ArrayList<BvCatalogPOJO> result = new ArrayList<>();
		String whereStatement = "type = '" +type +"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", "BvCatalog", whereStatement);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;	
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				BvCatalogPOJO res = new BvCatalogPOJO (rs.getString(1),rs.getInt(2));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
 
	// DI SEGUITO CI SRANNO LE QUERY RELATIVE ALLA TABLE BeverageDescription
	
	public double getPriceByBevName(String bevName) {
		double result = 0;
		String whereStatement = "BevName = '"+bevName+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("price", "BeverageDescriptions", whereStatement);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
	
	public void addBeverageDescription(BeverageDescriptionPOJO bv) {
		ArrayList<String> values = new ArrayList<>();
		values.add(bv.getBvName()); // primo attributo nella table
		values.add(String.valueOf(bv.getPrice())); // secondo attributo nella table
		values.add(bv.getIdRecipe()); // terzo attributo nella table

		String query = QueryGenerator.getInsertIntoValuesQuery("BeverageDescriptions", values);	

		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); // usato per eseguire una query di inserimento.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
	}
	
	/**
	 * L'idRecipe della beverage descriptione deve essere uguale ad almeno un idRecipe dell'arraylist ingredient recipe.
	 * @param bv
	 * @param ingr
	 */
	public void addBeverageDescription(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr) {
		addIngredientRecipe(ingr);
		addBeverageDescription(bv);
	}
	
	public BeverageDescriptionPOJO getBeverageDescriptionByBevName(String BevName) {
		BeverageDescriptionPOJO result = null;
		String whereStatement = "BevName = '"+BevName+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", "BeverageDescriptions", whereStatement);
		System.out.println(query);
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = new BeverageDescriptionPOJO (rs.getString(1),rs.getDouble(2),rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		// Fare un controllo if result = null e lanciare una eccezione?
		return result;
	}
	
	// DI SEGUITO CI SRANNO LE QUERY RELATIVE ALLA TABLE IngredientRecipe
	
	/**
	 * Gli ingredienti possono essere aggiunti senza aggiungere subito una BeverageDescription.
	 * @param ingr
	 */
	public void addIngredientRecipe(IngredientRecipePOJO ingr) {
		
		ArrayList<String> values = new ArrayList<>();
		values.add(ingr.getIdRecipe()); // primo attributo nella table
		values.add(ingr.getIngredientName()); // secondo attributo nella table
		values.add(String.valueOf(ingr.getQuantity())); // terzo attributo nella table
		String query = QueryGenerator.getInsertIntoValuesQuery("IngredientRecipe", values);	
		
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); // usato per eseguire una query di inserimento.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
		
	}
	
	public void addIngredientRecipe(ArrayList<IngredientRecipePOJO> ingr) {
		for (IngredientRecipePOJO ir : ingr) {
			addIngredientRecipe(ir);
		}
	}
	
	public ArrayList<IngredientRecipePOJO> getAllIngredientRecipeByIdRecipe(String idRecipe) {
		ArrayList<IngredientRecipePOJO> result = new ArrayList<>();
		String whereStatement = "idRecipe = '"+idRecipe+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", "IngredientRecipe", whereStatement);
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result.add(new IngredientRecipePOJO(rs.getString(1),rs.getString(2),rs.getDouble(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
	
}
