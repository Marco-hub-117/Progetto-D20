package it.unipv.ingsw.d20.util.persistence;

import java.util.ArrayList;

/**
 * Classe utilizzata per generare le stringhe relative alle query.
 * 
 */
public class QueryGenerator {
	
	public QueryGenerator() {}
	
	/**
	 * Restituisce una query "select from" sottoforma di stringa: 
	 * SELECT attributes
	 * FROM tableName
	 * @param attributes attributes attributi della "tabelName" desiderati.
	 * @param tableName tabella da cui estrapolare le informazioni
	 * @return Stringa formattata per generare una query di questo tipo.
	 */
	public static String getSelectFromQuery(String attributes, String tableName) {
		String query = null;
		query = "SELECT " + attributes + " FROM " + tableName;
		return query;
	}
	
	/**
	 * Restituisce una query "select from where" sottoforma di stringa: 
	 * SELECT attributes
	 * FROM tableName
	 * WHERE whereStatement
	 * @param attributes attributi della "tabelName" desiderati.
	 * @param tableName tabella da cui estrapolare le informazioni
	 * @param whereStatement clausola da inserire nel where
	 * @return query
	 */
	public static String getSelectFromWhereQuery(String attributes, String tableName, String whereStatement) {
		String query = null;
		query = "SELECT " + attributes + " FROM " + tableName + " WHERE " + whereStatement;
		return query;
	}
	
	/**
	 * Crea una query "Insert into table values" sottoforma di stringa.
	 * @param tableName Stringa che contiene il nome della table in cui inserire le informazioni
	 * @param values arraylist di stringhe che contiene gli attributi del record da inserire.
	 * @return query
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
	
	/**
	 * Crea una query Update Set e la restituisce sottoforma di stringa
	 * @param tableName nome tabella
	 * @param set nuova stringa
	 * @param whereStatement condizione
	 * @return
	 */
	public static String getUpdateSetQuery(String tableName,String set,String whereStatement) {
		String query = null;
		query = "UPDATE "+tableName+" SET "+set+ " WHERE "+whereStatement;
		return query;
	}
	
	/**
	 * Crea una query Delete from e la restituisce sottoforma di stringa.
	 * @param tableName Nome della tabella
	 * @param whereStatement condizione
	 * @return
	 */
	public static String getDeleteWhereQuery(String tableName, String whereStatement) {
		return "DELETE FROM "+tableName+" WHERE "+whereStatement;
	}
	
}
