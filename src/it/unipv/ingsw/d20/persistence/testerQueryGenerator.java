package it.unipv.ingsw.d20.persistence;


import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.sale.SalePOJO;

public class testerQueryGenerator {

	public static void main(String[] args) {
		
		String query1 = QueryGenerator.getSelectFromQuery("*", "Vending");
		System.out.println("Query: " + query1);
		
		String query2 = QueryGenerator.getSelectFromWhereQuery("*", "Vending", "idVending = 'id1'");
		System.out.println("Query: " + query2);
		
		ArrayList<String> values = new ArrayList<>();
		values.add("ids1");
		values.add("id1");
		values.add("idbv1");
		values.add("2020-06-02");
		String query3 = QueryGenerator.getInsertIntoValuesQuery("Sale", values);
		System.out.println("Query: " + query3);
		

		SalePOJO sale = new SalePOJO("ids1","id1","idbv1","2020-06-02");
		ArrayList<String> values2 = new ArrayList<>();
		values2.add(sale.getIdSale()); // primo attributo nella table
		values2.add(sale.getIdVending()); // secondo attributo nella table
		values2.add(sale.getIdBeverage()); // terzo attributo nella table
		values2.add(sale.getDate()); // qurto attributo nella table
		String query4 = QueryGenerator.getInsertIntoValuesQuery("Sale", values2);	
		System.out.println("Query: " + query4);
	}

}
