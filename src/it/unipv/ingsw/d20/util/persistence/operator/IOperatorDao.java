package it.unipv.ingsw.d20.util.persistence.operator;

import java.util.ArrayList;

/**
 * Interfaccia per Accedere a un OperatorDao. � possibile utilizzarla per ottenere le informazioni necessarie.
 *
 */
public interface IOperatorDao {
	
	public ArrayList<OperatorPOJO> getAllOperators();
	
	public OperatorPOJO getOperator(String code);
	
	public void addOperator(String code, String name, String username, String password, String type);

}
