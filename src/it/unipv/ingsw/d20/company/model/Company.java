package it.unipv.ingsw.d20.company.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.DBMS.IVendingDao;
import it.unipv.ingsw.d20.DBMS.PersistentFacade;
import it.unipv.ingsw.d20.company.model.net.CompanyServer;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.AddingMachineException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.AddingOperatorException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.VendingMachineAbsentException;
import it.unipv.ingsw.d20.company.model.net.CompanyServer;

public class Company {
	
	@SuppressWarnings("unused")
	private String name;
	private Map<String,VendingMachine> vendingMachineList;
	private Map<String,Operator> operatorList;
	private Map<String,RemoteOperator> remoteOperatorList;
	
	
	public Company(String name) {
		this.name = name;
		vendingMachineList = new HashMap<>();
		
		int port = 8888;
        CompanyServer server = new CompanyServer();
        try {
        	server.serverLoop(port);
        } catch (IOException e) {
        	e.printStackTrace();
        }    	
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
		/*PersistentFacade fc = null;
		fc.getInstance();
		IVendingDao a = fc.getVendingDao();*/
		
		if(vendingMachineList.containsKey(id)) {
			throw new AddingMachineException("ID già presente");
		}
		vendingMachineList.put(id, new VendingMachine(id, totalAmount));
	}
	
	public void addOperator(String id) throws AddingOperatorException {
		if (operatorList.containsKey(id))
			throw new AddingOperatorException("ID gi� utilizzato");
		operatorList.put(id, new Operator(id));
	}
	
	public void addRemoteOperator (String id) throws AddingOperatorException {
		if (remoteOperatorList.containsKey(id))
			throw new AddingOperatorException("ID gi� utilizzato");
		remoteOperatorList.put(id, new RemoteOperator(id));
	}
	
	// aggiungere getter per operatori passando come argomento id dell'operatore?
}
