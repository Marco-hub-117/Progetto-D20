package it.unipv.ingsw.d20.util.persistence.operator;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.RdbOperations;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

/**
 * Implementazione dell'interfaccia IOperatorDao. Implementa il DAO relativo al database relazionale.
 *
 */
public class OperatorRdbDao implements IOperatorDao{
	
	private RdbOperations op;
	
	public OperatorRdbDao() {
		op = new RdbOperations();
	}

	@Override
	public ArrayList<OperatorPOJO> getAllOperators() {
		return op.getAllOperators();
	}

	@Override
	public OperatorPOJO getOperator(String code) {
		return op.getOperator(code);
	}

	@Override
	public void addOperator(String code, String name, String username, String password, String type) {
		OperatorPOJO operator= new OperatorPOJO(code, name, username, password, type);
		op.addOperator(operator);
	}

}
