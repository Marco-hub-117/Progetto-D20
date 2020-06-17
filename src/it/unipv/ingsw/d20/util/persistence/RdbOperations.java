package it.unipv.ingsw.d20.util.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.beveragecatalog.BvCatalogPOJO;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.util.persistence.key.KeyPOJO;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorPOJO;
import it.unipv.ingsw.d20.util.persistence.sale.SalePOJO;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;
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
	
	public ArrayList<VendingPOJO> getAllVendings () {
		ArrayList<VendingPOJO> result = new ArrayList<>();
		String query = "SELECT * FROM Vending";
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				VendingPOJO res = new VendingPOJO(rs.getString("idVending"),VendingMachineStatus.valueOf(rs.getString("Status")));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	public VendingPOJO getVending (String idVending) {
		VendingPOJO result=null;
		String query = QueryGenerator.getSelectFromWhereQuery("*","Vending","idVending="+idVending);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			result = new VendingPOJO(rs.getString("idVending"),VendingMachineStatus.valueOf(rs.getString("Status")));
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
				result = new SalePOJO (rs.getString("idVending"),rs.getString("idBeverage"),rs.getString("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		// Fare un controllo if result = null e lanciare una eccezione?
		return result;
		
	}

	public ArrayList<SalePOJO> getAllSalesByIdVending (String idVending) {
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
				SalePOJO res =  new SalePOJO (rs.getString("idVending"),rs.getString("idBeverage"),rs.getString("date"));
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
				BvCatalogPOJO res = new BvCatalogPOJO (rs.getString("idBevDesc"),rs.getInt("type"));
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
				result = rs.getDouble("price");
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
	
	public ArrayList<BeverageDescriptionPOJO> getAllBeverageDescriptions () {
		ArrayList<BeverageDescriptionPOJO> result = new ArrayList<>();
		String query = "SELECT * FROM BeverageDescription";
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				BeverageDescriptionPOJO res = new BeverageDescriptionPOJO(rs.getString("BevName"),rs.getDouble("price"), rs.getString("idRecipe"));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	public BeverageDescriptionPOJO getBeverageDescriptionByBevName(String bevName) {
		BeverageDescriptionPOJO result = null;
		String whereStatement = "BevName = '"+bevName+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", "BeverageDescriptions", whereStatement);
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = new BeverageDescriptionPOJO (rs.getString("BevName"),rs.getDouble("price"), rs.getString("idRecipe"));
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
				result.add(new IngredientRecipePOJO(rs.getString("idRecipe"),rs.getString("IngredientName"),rs.getDouble("Quantity")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
	
	public void updateIngredientRecipe(String idRecipe, String ingredientName, double newQuantity) {
		String query = QueryGenerator.getUpdateSetQuery("IngredientRecipe", "Quantity = '"+newQuantity+"'", "idRecipe = '"+idRecipe+"'"+ "and "+ "IngredientName = '"+ingredientName+"'");
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
	}
	
	
	
	// DI SEGUITO LE QUERY RELATIVE ALLA TABLE Operator
	
	public ArrayList<OperatorPOJO> getAllOperators () {
		ArrayList<OperatorPOJO> result = new ArrayList<>();
		String query = "SELECT * FROM Operator";
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				OperatorPOJO res = new OperatorPOJO(rs.getString("code"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("type"));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	public OperatorPOJO getOperator (String code) {
		OperatorPOJO result=null;
		String query = QueryGenerator.getSelectFromWhereQuery("*","Operator","code= '"+code+"'");
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			result = new OperatorPOJO(rs.getString("code"), rs.getString("name"), rs.getString("username"), rs.getString("password"), rs.getString("type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	public void addOperator(OperatorPOJO operator) {
		
		ArrayList<String> values = new ArrayList<>();
		values.add(operator.getCode()); 
		values.add(operator.getName()); 
		values.add(operator.getUsername()); 
		values.add(operator.getPassword());
		values.add(operator.getType());
		String query = QueryGenerator.getInsertIntoValuesQuery("Operator", values);	
		
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
	}
	
	// DI SEGUITO LE QUERY RELATIVE ALLA TABLE Key
	
	public ArrayList<KeyPOJO> getAllKeys() {
		
		ArrayList<KeyPOJO> result = new ArrayList<>();
		String query = "SELECT * FROM Key";
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				KeyPOJO res = new KeyPOJO(rs.getString("idKey"), rs.getDouble("credit"));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	public KeyPOJO getKey (String serialCode) {
		KeyPOJO result=null;
		String query = QueryGenerator.getSelectFromWhereQuery("*","Key","serialCode="+serialCode);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			result = new KeyPOJO(rs.getString("idKey"), rs.getDouble("credit"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	public void addKey(KeyPOJO key) {
		ArrayList<String> values = new ArrayList<>();
		values.add(key.getSerialCode()); 
		values.add((String.valueOf(key.getCredit()))); 
		String query = QueryGenerator.getInsertIntoValuesQuery("Key", values);	
		
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
	}
	
	public void updateKeyCredit(String serialCode, double newCredit) {
		String query = QueryGenerator.getUpdateSetQuery("Key", "credit = '"+newCredit+"'", "serialCode = '"+serialCode+"'");
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
	}
	
	public double getKeyCredit(String serialCode) {
		double result = 0; 
		String query = QueryGenerator.getSelectFromWhereQuery("credit", "Key", "serialCode = '"+serialCode+"'");
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			result = rs.getDouble("credit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
	
	public void deactivateKey(String serialCode) {
		String query = QueryGenerator.getDeleteWhereQuery("Key", "serialCode = '"+serialCode+"'");
		
		con = this.startConnection(con);
		Statement st;	
		try {
			st = con.createStatement();
			st.executeUpdate(query); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection(con);
	}
	
}
