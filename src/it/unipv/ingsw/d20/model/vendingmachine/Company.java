package it.unipv.ingsw.d20.model.vendingmachine;

import java.util.Map;

public class Company {
	
	private String name;
	private Map<String,VendingMachine> vendingMachineList;
	//private Map<String,Operator> operatorList;
	//private Map<String,RemoteOperator> remoteOperatorList;
	
	public Company(String name) {
		this.name = name;
	}
	
	public void sendAllInfo() {
		
	}
	
	public void selectVendinMachine(String id) {
		
	}
	
	public void addVendingMachine (String id) {
		//aggiungere eccezione se la vending machine non viene creata?
	}
	
	public void addOperator (String id) {
		//aggiungere eccezione se operator non viene aggiunto?
	}
	
	public void addRemoteOperator (String id) {
		//aggiungere eccezione se remote operator non viene aggiunto?
	}
	
	// aggiungere getter per operatori passando come argomento id dell'operatore?
}
