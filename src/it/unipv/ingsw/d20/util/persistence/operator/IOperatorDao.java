package it.unipv.ingsw.d20.util.persistence.operator;

import java.util.ArrayList;

/**
 * Interfaccia per Accedere a un OperatorDao. � possibile utilizzarla per ottenere le informazioni necessarie.
 *
 */
public interface IOperatorDao {
	
	/**
	 * Ottiene tutti gli operatori salvati.
	 * @return
	 */
	public ArrayList<OperatorPOJO> getAllOperators();
	
	/**
	 * Ottiene un operatore specifico.
	 * @param code
	 * @return
	 */
	public OperatorPOJO getOperator(String code);
	
	/**
	 * Salva un nuovo operatore.
	 * @param code
	 * @param name
	 * @param password
	 * @param type
	 */
	public void addOperator(String code, String name, String password, String type);

}
