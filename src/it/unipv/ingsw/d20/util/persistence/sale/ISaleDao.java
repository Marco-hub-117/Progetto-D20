package it.unipv.ingsw.d20.util.persistence.sale;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISaleDao {

	/**
	 * Registra una nuova sale.
	 * @param sale passato come un salePOJO
	 * @throws SQLException 
	 */
	public void addSale(SalePOJO sale) throws SQLException;
	
	/**
	 * Ottiene una sale data la sua key ( composta dagli attributi idVending e date)
	 * @param id della sale.
	 * @return SalePOJO
	 */
	public SalePOJO getSaleByKey(String idVending,String date);
	
	/**
	 * Ottiene tutte le sale legate a una vending machine specifica, passando come argomento l'id della vending
	 * @param idVending
	 * @return ritorna un arrayList di SalePOJO.
	 */
	public ArrayList<SalePOJO> getAllSalesByIdVending (String idVending);
	

	

	
}
