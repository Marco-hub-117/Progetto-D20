package it.unipv.ingsw.d20.util.persistence.beveragecatalog;

import java.util.ArrayList;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;

public interface IBvCatalogDao {
	
	/**
	 * Ottiene una ArrayList<BeveragePOJO> contenente il catalogo del tipo passato come parametro.
	 * @param type tipo di catalogo
	 * @return bvCatalogList lista di cataloghi
	 */
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByType(int type);
	
	/**
	 * Ottiene un'istanza di BeverageCatalog, contenente tutte le bevande appartenenti alla tipologia di catalogo specificata nel parametro type.
	 * @param type tipo di catalogo
	 * @return bvCatalog catalogo
	 */
	public BeverageCatalog getBeverageCatalog(int type);
	
}
