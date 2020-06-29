package it.unipv.ingsw.d20.util.persistence.sale;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISaleDao {

	/**
	 * Registra una nuova sale.
	 * @param sale passato come un salePOJO
	 * @throws SQLException Eccezione di SQL
	 */
	public void addSale(SalePOJO sale) throws SQLException;
	
	/**
	 * Ottiene una sale data la sua key (composta dagli attributi idVending e date)
	 * @param idVending Id della sale.
	 * @param date data
	 * @return SalePOJO POJO della sale
	 */
	public SalePOJO getSaleByKey(String idVending, String date);
	
	/**
	 * Ottiene tutte le sale legate a una vending machine specifica, passando come argomento l'id della vending
	 * @param idVending id della vending machine
	 * @return ritorna un arrayList di SalePOJO.
	 */
	public ArrayList<SalePOJO> getAllSalesByIdVending (String idVending);
	

	

	
}
