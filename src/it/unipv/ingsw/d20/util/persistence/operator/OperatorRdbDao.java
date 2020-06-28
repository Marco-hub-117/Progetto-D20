package it.unipv.ingsw.d20.util.persistence.operator;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.RdbOperations;

/**
 * Implementazione dell'interfaccia IOperatorDao. Implementa il DAO relativo al database relazionale.
 *
 */
public class OperatorRdbDao implements IOperatorDao{
	
	private RdbOperations op;
	
	public OperatorRdbDao(RdbOperations op) {
		this.op = op;
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
	public void addOperator(String code, String name, String password, String type) {
		OperatorPOJO operator= new OperatorPOJO(code, name, password, type);
		op.addOperator(operator);
	}

}
