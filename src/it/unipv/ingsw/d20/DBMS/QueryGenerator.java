package it.unipv.ingsw.d20.DBMS;

import java.util.ArrayList;

public class QueryGenerator {
	
	public QueryGenerator() {
		
	}
	/**
	 * Restituisce una query "select from" sottoforma di stringa: 
	 * SELECT attributes
	 * FROM tableName
	 * @param attributes attributes attributi della "tabelName" desiderati.
	 * @param tableName tabbella da cui estrapolare le informazioni
	 * @return Stringa formattata per generare una query di questo tipo.
	 */
	public static String getSelectFromQuery(String attributes,String tableName) {
		String query = null;
		query = "SELECT "+attributes+" FROM "+tableName;
		return query;
	}
	
	
	
	/**
	 * Restituisce una query "select from where" sottoforma di stringa: 
	 * SELECT attributes
	 * FROM tableName
	 * WHERE whereStatement
	 * @param attributes attributi della "tabelName" desiderati.
	 * @param tableName tabbella da cui estrapolare le informazioni
	 * @param whereStatement clausola da inserire nel where
	 * @return
	 */
	public static String getSelectFromWhereQuery(String attributes,String tableName,String whereStatement) {
		String query = null;
		query = "SELECT "+attributes+" FROM "+tableName+" WHERE "+whereStatement;
		return query;
	}
	
	
	/**
	 * Crea una query "Insert into table values" sottoforma di stringa.
	 * @param tableName Stringa che contiene il nome della table in cui inserire le informazioni
	 * @param values arraylist di stringhe che contiene gli attributi del record da inserire.
	 * @return
	 */
	public static String getInsertIntoValuesQuery(String tableName, ArrayList<String> values) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ");
		query.append(tableName);
		query.append(" VALUES (");
		
		boolean firstIteration = true;
		for (String s : values) {
			if (firstIteration) {
				query.append("'");
				query.append(s);
				query.append("'");
				firstIteration = false;
			} else {
				query.append(", '");
				query.append(s);
				query.append("'");
			}
		}
		
		query.append(")");
		
		return query.toString();
	}
}
