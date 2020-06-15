package it.unipv.ingsw.d20.util.persistence.sale;

import java.util.ArrayList;

public interface ISaleDao {

	/**
	 * Registra una nuova sale.
	 * @param sale passato come un salePOJO
	 */
	public void addSale(SalePOJO sale);
	
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
	public ArrayList<SalePOJO> getAllSaleByIdVending (String idVending);
	

	

	
}
