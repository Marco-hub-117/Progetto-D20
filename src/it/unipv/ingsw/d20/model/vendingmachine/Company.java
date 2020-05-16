package it.unipv.ingsw.d20.model.vendingmachine;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.model.vendingmachine.exceptions.AddingMachineException;

public class Company {
	
	private String name;
	private Map<String,VendingMachine> vendingMachineList;
	
	//private Map<String,Operator> operatorList;
	//private Map<String,RemoteOperator> remoteOperatorList;
	
	public Company(String name) {
		this.name = name;
		vendingMachineList = new HashMap<>();
	}
	
	public void sendAllInfo() {
		
	}
	
	public void selectVendingMachine(String id) {
		
	}
	
	public void addVendingMachine (String id, double totalAmount) throws AddingMachineException {
		if(vendingMachineList.containsKey(id) == true) {
			throw new AddingMachineException("ID già presente");
		}
		vendingMachineList.put(id, new VendingMachine(id, totalAmount));
	}
	
	public void addOperator (String id) {
		//aggiungere eccezione se operator non viene aggiunto?
	}
	
	public void addRemoteOperator (String id) {
		//aggiungere eccezione se remote operator non viene aggiunto?
	}
	
	
	
	// aggiungere getter per operatori passando come argomento id dell'operatore?
}
