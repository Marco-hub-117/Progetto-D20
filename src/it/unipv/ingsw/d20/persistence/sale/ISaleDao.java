package it.unipv.ingsw.d20.persistence.sale;

import java.util.ArrayList;

public interface ISaleDao {

	/**
	 * Registra una nuova sale.
	 * @param sale passato come un salePOJO
	 */
	public void addSale(SalePOJO sale);
	
	/**
	 * Ottiene una sale datao il suo id
	 * @param id della sale.
	 * @return SalePOJO
	 */
	public SalePOJO getSaleById(String id);
	
	/**
	 * Ottiene tutte le sale legate a una vending machine specifica, passando come argomento l'id della vending
	 * @param idVending
	 * @return ritorna un arrayList di SalePOJO.
	 */
	public ArrayList<SalePOJO> getAllSaleByIdVending (String idVending);
	
}
