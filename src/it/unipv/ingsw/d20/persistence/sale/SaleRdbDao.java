package it.unipv.ingsw.d20.persistence.sale;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
	public SalePOJO getSaleByKey(String id,String date) {
		return op.getSaleByKey(id, date);
	}

	@Override
	public ArrayList<SalePOJO> getAllSaleByIdVending(String idVending) {
		return op.getAllSaleByIdVending(idVending);
	}

}
