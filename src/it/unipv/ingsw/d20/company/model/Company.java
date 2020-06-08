package it.unipv.ingsw.d20.company.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import it.unipv.ingsw.d20.company.model.net.CompanyServer;
import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.persistence.vending.VendingPOJO;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
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
	
	public static Map<String, Date> vendingMachineStatusList = new HashMap<>();
	
	
	public Company(String name) {
		this.name = name;
		vendingMachineList = new HashMap<>();
		vendingMachineStatusList = new HashMap<>();
		
		int port = 8888;
        CompanyServer server = new CompanyServer();
        try {
        	server.serverLoop(port);
        } catch (IOException e) {
        	e.printStackTrace();
        }    
        
        PersistenceFacade pf = PersistenceFacade.getInstance();
        IVendingDao v = pf.getVendingDao();
        ArrayList<VendingPOJO> vendingList = v.getAllVending();
        
        for (VendingPOJO vp : vendingList) { //riempie la mappa con tutte le vending machine già registrate nel database
        	vendingMachineStatusList.put(vp.getIdVending(), new Date());
        }
        
        Timer timer = new Timer();																   //ogni 10 minuti aggiorna lo status nel db di tutte le vending 
		timer.schedule(new RefreshVendingListStatus(), new Date(), TimeUnit.MINUTES.toMillis(10)); //machine, se necessario (se non ha ricevuto update) setta OFF)
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
		vendingMachineList.put(id, new VendingMachine(id));
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
	
	public static String registerNewVendingMachine() {
		String IDNumber = generateNewID();
		
		/*PersistenceFacade pf = PersistenceFacade.getInstance();
		IVendingDao ivd = pf.getVendingDao();
		ivd.addVending(new VendingPOJO(IDNumber, null));*/
		
		vendingMachineStatusList.put(IDNumber, new Date());
		
		return IDNumber;
	}
	
	private static String generateNewID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String vendingIDNumber = sdf.format(new Date());
		
		String fileName = "IDN" + vendingIDNumber;
		return fileName; 
	}
	
	// aggiungere getter per operatori passando come argomento id dell'operatore?
}
