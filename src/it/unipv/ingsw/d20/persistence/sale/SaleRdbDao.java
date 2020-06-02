package it.unipv.ingsw.d20.persistence.sale;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.RdbOperations;

public class SaleRdbDao implements ISaleDao{
	
	private RdbOperations op;
	
	public SaleRdbDao() {
		op = new RdbOperations();
	}

	@Override
	public void addSale(SalePOJO sale) {
		op.addSale(sale);
	}

	@Override
	public SalePOJO getSaleById(String id) {
		return op.getSaleById(id);
	}

	@Override
	public ArrayList<SalePOJO> getAllSaleByIdVending(String idVending) {
		return op.getAllSaleByIdVending(idVending);
	}



}
