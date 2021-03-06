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
import it.unipv.ingsw.d20.util.persistence.operator.OperatorPOJO;
import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyPOJO;
import it.unipv.ingsw.d20.util.persistence.sale.SalePOJO;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

/**
 * Classe che implementa la connessione con il database. Implementa tutte le possibili operazioni con esso.
 *
 */
public class RdbOperations {
	
	private Connection con;
	/**
	 * Costruttore della classe RdbOperations
	 *
	 */
	public RdbOperations () {
		this.con = null;
	}
	
	/**
	 * Apre la connessione con il DB
	 * @param conn Connessione
	 * @return la connessione aperta
	 */
	@SuppressWarnings("unused")
	public Connection startConnection(Connection conn) { 
		
		String DbDriver = null;
		String DbUrl=null;
		String username=null;
		String password = null;
		
		DbDriver = "com.mysql.cj.jdbc.Driver";
		
		//connessione con DB su GCP
		/*DbUrl = "jdbc:mysql://34.65.222.216:3306/prova"; // � possibile implementare una connessione a uno schema particolare, in questo caso usiamo di default lo schema "prova"
		username = "root";
		password = "";*/
		
		//connessione con DB su freemysqlhosting.net
		DbUrl = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7351837";
		username = "sql7351837";
		password = "8nEuLTKJNk";
		
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
	
	/**
	 * Verifica che la connessione sia aperta
	 * @param conn Connessione
	 * 
	 */
	public boolean isOpen(Connection conn) {
		if (conn == null) 
			return false;
		else 
			return true;
	}
	
	/**
	 * Chiude La connessione con il DB
	 * @param conn Connessione
	 * 
	 */
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
	
	/**
	 * Recupera tutte le VendingMachine dal DB
	 * @return un ArrayList<VendingPOJO>
	 */
	public ArrayList<VendingPOJO> getAllVendings () {
		ArrayList<VendingPOJO> result = new ArrayList<>();
		String query = QueryGenerator.getSelectFromQuery("*", TablesConstants.vendingTable);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				VendingPOJO res = new VendingPOJO(rs.getString(TablesConstants.idVendingAttr),rs.getString(TablesConstants.locationAttr),rs.getString(TablesConstants.typeAttr));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Ottine una VendingMachine prendendola dal DB
	 * @param idVending Id della Vending da recuperare
	 *
	 */
	public VendingPOJO getVending (String idVending) {
		VendingPOJO result=null;
		String query = QueryGenerator.getSelectFromWhereQuery("*",TablesConstants.vendingTable,TablesConstants.idVendingAttr +" = '"+idVending+"'");
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = new VendingPOJO(rs.getString(TablesConstants.idVendingAttr),rs.getString(TablesConstants.locationAttr),rs.getString(TablesConstants.typeAttr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Aggiunge una VendingMachine sul DB
	 * @param vending 
	 */
	public void addVending(VendingPOJO vending) {	
		ArrayList<String> values = new ArrayList<>();
		values.add(vending.getId());
		values.add(vending.getLocation());
		values.add(vending.getType());
		String query = QueryGenerator.getInsertIntoValuesQuery(TablesConstants.vendingTable, values);
		
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
	 * OBSOLETO.
	 * Aggiorna il saldo della VendingMachine sul DB
	 * @param idVending Id della vending da aggiornare
	 * @param amount saldo aggiornato
	 */
	public void updateVendingAmount(String idVending,double amount) {
		String set = "Amount = '"+String.valueOf(amount)+"'";
		String whereStatement = "idVending = '"+idVending+"'";
		String query = QueryGenerator.getUpdateSetQuery(TablesConstants.vendingTable, set, whereStatement);
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
	
	/**
	 * Aggiunge una nuova Sale sul DB
	 * @param sale Sale da aggiungere
	 * @throws SQLException
	 */
	public void addSale(SalePOJO sale) throws SQLException {
		
		ArrayList<String> values = new ArrayList<>();
		values.add(sale.getIdVending()); // primo attributo nella table
		values.add(sale.getIdBeverage()); // secondo attributo nella table
		values.add(sale.getDate()); // terzo attributo nella table
		
		String query = QueryGenerator.getInsertIntoValuesQuery(TablesConstants.saleTable, values);	
		con = this.startConnection(con);
		Statement st;	
		//try {
			st = con.createStatement();
			st.executeUpdate(query); // usato per eseguire una query di inserimento.
			
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		this.closeConnection(con);
	}
	
	/**
	 * Ottiene una sale recuperandola dal DB
	 * @param idVending Id della Vending
	 * @param date Data nel formato yyyy/MM/dd hh:mm:ss
	 * 
	 */
	public SalePOJO getSaleByKey (String idVending,String date) {
		SalePOJO result = null;
		String whereStatement = TablesConstants.idVendingSaleAttr +" = '"+idVending+"' and "+ TablesConstants.dateAttr +" = '"+date+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", TablesConstants.saleTable, whereStatement);
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = new SalePOJO (rs.getString(TablesConstants.idVendingSaleAttr),rs.getString(TablesConstants.idBeverageSaleAttr),rs.getString(TablesConstants.dateAttr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		// Fare un controllo if result = null e lanciare una eccezione?
		return result;
		
	}
	
	/**
	 * Ottiene tutte le sale di una VendingMachine
	 * @param idVending Id della vending di interesse
	 * 
	 */
	public ArrayList<SalePOJO> getAllSalesByIdVending (String idVending) {
		ArrayList<SalePOJO> result = new ArrayList<>();
		String whereStatement = TablesConstants.idVendingSaleAttr +" = '" +idVending+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", TablesConstants.saleTable, whereStatement);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				SalePOJO res =  new SalePOJO (rs.getString(TablesConstants.idVendingSaleAttr),rs.getString(TablesConstants.idBeverageSaleAttr),rs.getString(TablesConstants.dateAttr));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		
		return result;
	}

	// DI SEGUITO CI SARANNO LE QUERY RELATIVE ALLA TABLE BvCatalog
	
	/**
	 * Ottiene il catalogo dato il tipo.
	 * @param type tipo del catalogo (INVERNALE O ESTIVO)
	 * 
	 */
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type) {
		ArrayList<BvCatalogPOJO> result = new ArrayList<>();
		String whereStatement = TablesConstants.typeCatAttr+" = '" +type +"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", TablesConstants.beverageCatalogTable, whereStatement);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;	
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				BvCatalogPOJO res = new BvCatalogPOJO (rs.getString(TablesConstants.idBvDescAttr),rs.getInt(TablesConstants.typeCatAttr));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
 
	// DI SEGUITO CI SRANNO LE QUERY RELATIVE ALLA TABLE BeverageDescription
	
	/**
	 * Ottiene il prezzo di una bevanda
	 * @param bevName nome della bevanda
	 * @return
	 */
	public double getPriceByBevName(String bevName) {
		double result = 0;
		String whereStatement = TablesConstants.bevNameAttr+" = '"+bevName+"'";
		String query = QueryGenerator.getSelectFromWhereQuery(TablesConstants.priceAttr, TablesConstants.beverageDescriptionTable, whereStatement);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = rs.getDouble(TablesConstants.priceAttr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Aggiunge una nuova bevanda.
	 * @param bv Bevanda da aggiungere
	 */
	public void addBeverageDescription(BeverageDescriptionPOJO bv) {
		ArrayList<String> values = new ArrayList<>();
		values.add(bv.getBvName()); // primo attributo nella table
		values.add(String.valueOf(bv.getPrice())); // secondo attributo nella table
		values.add(bv.getIdRecipe()); // terzo attributo nella table

		String query = QueryGenerator.getInsertIntoValuesQuery(TablesConstants.beverageDescriptionTable, values);	

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
	 * Aggiunge una nuova bevanda anche con i suoi ingredienti
	 * L'idRecipe della beverage description deve essere uguale ad almeno un idRecipe dell'arraylist ingredient recipe.
	 * @param bv Bevanda da aggiungere
	 * @param ingr Lista di ingredienti
	 */
	public void addBeverageDescription(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr) {
		addIngredientRecipe(ingr);
		addBeverageDescription(bv);
	}
	
	/**
	 * Ottiene tutte le BeverageDescription
	 * 
	 */
	public ArrayList<BeverageDescriptionPOJO> getAllBeverageDescriptions () {
		ArrayList<BeverageDescriptionPOJO> result = new ArrayList<>();
		String query = "SELECT * FROM "+TablesConstants.beverageDescriptionTable;
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				BeverageDescriptionPOJO res = new BeverageDescriptionPOJO(rs.getString(TablesConstants.bevNameAttr),rs.getDouble(TablesConstants.priceAttr), rs.getString(TablesConstants.idRecipeDescAttr));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Ottiene la descrizione di una bevanda dato il suo nome.
	 * @param bevName Nome della bevanda
	 * 
	 */
	public BeverageDescriptionPOJO getBeverageDescriptionByBevName(String bevName) {
		BeverageDescriptionPOJO result = null;
		String whereStatement = TablesConstants.bevNameAttr+" = '"+bevName+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", TablesConstants.beverageDescriptionTable, whereStatement);
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = new BeverageDescriptionPOJO (rs.getString(TablesConstants.bevNameAttr),rs.getDouble(TablesConstants.priceAttr), rs.getString(TablesConstants.idRecipeDescAttr));
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
	 * Aggiunge un nuovo ingrediente nel DB.
	 * Gli ingredienti possono essere aggiunti senza aggiungere subito una BeverageDescription.
	 * @param ingr ingrediente da aggiungere
	 */
	public void addIngredientRecipe(IngredientRecipePOJO ingr) {
		
		ArrayList<String> values = new ArrayList<>();
		values.add(ingr.getIdRecipe()); // primo attributo nella table
		values.add(ingr.getIngredientName()); // secondo attributo nella table
		values.add(String.valueOf(ingr.getQuantity())); // terzo attributo nella table
		String query = QueryGenerator.getInsertIntoValuesQuery(TablesConstants.ingredientRecipeTable, values);	
		
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
	 * Aggiunge una serie di nuovi ingredienti sul DB.
	 * @param ingr lista di ingredienti da aggiungere
	 */
	public void addIngredientRecipe(ArrayList<IngredientRecipePOJO> ingr) {
		for (IngredientRecipePOJO ir : ingr) {
			addIngredientRecipe(ir);
		}
	}
	
	/**
	 * Ottiene tutti gli ingredienti con campo IdRecipe uguale a quello passato come parametro.
	 * @param idRecipe
	 * 
	 */
	public ArrayList<IngredientRecipePOJO> getAllIngredientRecipeByIdRecipe(String idRecipe) {
		ArrayList<IngredientRecipePOJO> result = new ArrayList<>();
		String whereStatement = TablesConstants.idRecipeAttr+" = '"+idRecipe+"'";
		String query = QueryGenerator.getSelectFromWhereQuery("*", TablesConstants.ingredientRecipeTable, whereStatement);
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result.add(new IngredientRecipePOJO(rs.getString(TablesConstants.idRecipeAttr),rs.getString(TablesConstants.ingredientNameAttr),rs.getDouble(TablesConstants.quantityAttr)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Aggiorna la composizione di un ingrediente, modificando la quantita'.
	 * @param idRecipe id ricetta
	 * @param ingredientName nome ingrediente
	 * @param newQuantity nuova quantit�
	 */
	public void updateIngredientRecipe(String idRecipe, String ingredientName, double newQuantity) {
		String query = QueryGenerator.getUpdateSetQuery(TablesConstants.ingredientRecipeTable, TablesConstants.quantityAttr+" = '"+newQuantity+"'", TablesConstants.idRecipeAttr+" = '"+idRecipe+"'"+ "and "+ TablesConstants.ingredientNameAttr+ " = '"+ingredientName+"'");
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
	
	/**
	 * Ottiene tutti gli operatori registrati sul DB.
	 * @return operatorList lista di tutti gli operatori
	 */
	public ArrayList<OperatorPOJO> getAllOperators () {
		ArrayList<OperatorPOJO> result = new ArrayList<>();
		String query = QueryGenerator.getSelectFromQuery("*", TablesConstants.operatorTable);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				OperatorPOJO res = new OperatorPOJO(rs.getString(TablesConstants.cfAttr), rs.getString(TablesConstants.nameAttr), rs.getString(TablesConstants.passwordAttr), rs.getString(TablesConstants.typeOpAttr));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Ottiene uno specifico operatore.
	 * @param code codice dell'operatore
	 * @return operator operatore
	 */
	public OperatorPOJO getOperator (String code) {
		OperatorPOJO result=null;
		String query = QueryGenerator.getSelectFromWhereQuery("*",TablesConstants.operatorTable,TablesConstants.cfAttr+"= '"+code+"'");
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = new OperatorPOJO(rs.getString(TablesConstants.cfAttr), rs.getString(TablesConstants.nameAttr), rs.getString(TablesConstants.passwordAttr), rs.getString(TablesConstants.typeOpAttr));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Aggiunge un operatore sul DB.
	 * @param operator operatore
	 */
	public void addOperator(OperatorPOJO operator) {
		
		ArrayList<String> values = new ArrayList<>();
		values.add(operator.getCode()); 
		values.add(operator.getName()); 
		values.add(operator.getPassword());
		values.add(operator.getType());
		String query = QueryGenerator.getInsertIntoValuesQuery(TablesConstants.operatorTable, values);	
		
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
	
	// DI SEGUITO LE QUERY RELATIVE ALLA TABLE PaymentKey
	
	/**
	 * Ottiene tutte le chiavette salvate sul DB.
	 * @return keyList lista di tutte le chiavette
	 */
	public ArrayList<KeyPOJO> getAllKeys() {
		ArrayList<KeyPOJO> result = new ArrayList<>();
		String query = QueryGenerator.getSelectFromQuery("*", TablesConstants.paymentKeyTable);
		
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				KeyPOJO res = new KeyPOJO(rs.getInt(TablesConstants.codeAttr), rs.getDouble(TablesConstants.creditAttr));
				result.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Ottiene una specifica chiave dal DB
	 * @param serialCode Codice della chiave
	 * @return key chiavetta
	 */
	public KeyPOJO getKey (String serialCode) {
		KeyPOJO result=null;
		String query = QueryGenerator.getSelectFromWhereQuery("*",TablesConstants.paymentKeyTable,TablesConstants.codeAttr +"="+serialCode);
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				result = new KeyPOJO(rs.getInt(TablesConstants.codeAttr), rs.getDouble(TablesConstants.creditAttr));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Salva una chiave sul DB.
	 * @param key Chiave da salvare
	 */
	public void addKey(KeyPOJO key) {
		ArrayList<String> values = new ArrayList<>();
		values.add((String.valueOf(key.getSerialCode()))); 
		values.add((String.valueOf(key.getCredit()))); 
		String query = QueryGenerator.getInsertIntoValuesQuery(TablesConstants.paymentKeyTable, values);	
		
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
	
	/**
	 * Aggiorna il credito di una chiave salvata sul DB.
	 * @param serialCode Codice della chiavetta
	 * @param newCredit nuovo credito
	 */
	public void updateKeyCredit(String serialCode, double newCredit) {
		String query = QueryGenerator.getUpdateSetQuery(TablesConstants.paymentKeyTable, TablesConstants.creditAttr+" = '"+newCredit+"'", TablesConstants.codeAttr+" = '"+serialCode+"'");
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
	
	/**
	 * Ottiene il credito di una chiave.
	 * @param serialCode Codice della chiavetta
	 * @return keyCredit credito sulla chiavetta
	 */
	public double getKeyCredit(String serialCode) {
		double result = 0; 
		String query = QueryGenerator.getSelectFromWhereQuery(TablesConstants.creditAttr, TablesConstants.paymentKeyTable, TablesConstants.codeAttr+" = '"+serialCode+"'");
		
		con = this.startConnection(con);
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				result = rs.getDouble(TablesConstants.creditAttr);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.closeConnection(con);
		return result;
	}
	
	/**
	 * Disattiva una chiave sul DB.
	 * @param serialCode codice della chiave
	 */
	public void deactivateKey(String serialCode) {
		String query = QueryGenerator.getDeleteWhereQuery(TablesConstants.paymentKeyTable, TablesConstants.codeAttr+" = '"+serialCode+"'");
		
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
