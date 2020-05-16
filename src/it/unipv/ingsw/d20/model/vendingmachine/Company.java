package it.unipv.ingsw.d20.model.vendingmachine;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.model.vendingmachine.exceptions.AddingMachineException;
import it.unipv.ingsw.d20.model.vendingmachine.exceptions.AddingOperatorException;
import it.unipv.ingsw.d20.model.vendingmachine.exceptions.VendingMachineAbsentException;

public class Company {
	
	private String name;
	private Map<String,VendingMachine> vendingMachineList;
	private Map<String,Operator> operatorList;
	private Map<String,RemoteOperator> remoteOperatorList;
	
	public Company(String name) {
		this.name = name;
		vendingMachineList = new HashMap<>();
	}
	
	public void sendAllInfo() {
		/*
		 * non sappiamo quali info inviare.
		 */
	}
	
	public VendingMachine selectVendingMachine(String id) throws VendingMachineAbsentException {
		if (!(vendingMachineList.containsKey(id))) 
			throw new VendingMachineAbsentException("La vending machine non esiste");
		return this.vendingMachineList.get(id);
	}
	
	public void addVendingMachine (String id, double totalAmount) throws AddingMachineException {
		if(vendingMachineList.containsKey(id)) {
			throw new AddingMachineException("ID gi√† presente");
		}
		vendingMachineList.put(id, new VendingMachine(id, totalAmount));
	}
	
	public void addOperator(String id) throws AddingOperatorException {
		if (operatorList.containsKey(id))
			throw new AddingOperatorException("ID gi‡ utilizzato");
		operatorList.put(id, new Operator(id));
	}
	
	public void addRemoteOperator (String id) throws AddingOperatorException {
		if (remoteOperatorList.containsKey(id))
			throw new AddingOperatorException("ID gi‡ utilizzato");
		remoteOperatorList.put(id, new RemoteOperator(id));
	}
	
	// aggiungere getter per operatori passando come argomento id dell'operatore?
}
