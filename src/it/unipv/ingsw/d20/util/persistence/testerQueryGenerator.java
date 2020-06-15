package it.unipv.ingsw.d20.util.persistence;


import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.sale.SalePOJO;

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
		

	}

}
