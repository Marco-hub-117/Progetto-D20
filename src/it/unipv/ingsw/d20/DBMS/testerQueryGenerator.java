package it.unipv.ingsw.d20.DBMS;

import java.util.ArrayList;

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
		values.add("02/06/2020");
		String query3 = QueryGenerator.getInsertIntoValuesQuery("Sale", values);
		System.out.println("Query: " + query3);
		
		SalePOJO sale = new SalePOJO("ids1","id1","idbv1","02/06/2020");
		ArrayList<String> values2 = new ArrayList<>();
		values2.add(sale.getIdSale()); // primo attributo nella table
		values2.add(sale.getIdVending()); // secondo attributo nella table
		values2.add(sale.getIdBeverage()); // terzo attributo nella table
		values2.add(sale.getDate()); // qurto attributo nella table
		String query4 = QueryGenerator.getInsertIntoValuesQuery("Sale", values2);	
		System.out.println("Query: " + query4);
	}

}
