package it.unipv.ingsw.d20.company;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import it.unipv.ingsw.d20.company.net.CompanyServer;
import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.AddingOperatorException;

public class Company {
	
	@SuppressWarnings("unused")
	private String name;
	
	public static Map<String, Date> vendingMachineStatusList = new HashMap<>();
	
	
	public Company(String name) {
		this.name = name;

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
        ArrayList<VendingPOJO> vendingList = v.getAllVendings();
        
        for (VendingPOJO vp : vendingList) { //riempie la mappa con tutte le vending machine già registrate nel database
        	vendingMachineStatusList.put(vp.getIdVending(), new Date());
        }
        
        Timer timer = new Timer();																   //ogni 10 minuti aggiorna lo status nel db di tutte le vending 
		timer.schedule(new RefreshVendingListStatus(), new Date(), TimeUnit.MINUTES.toMillis(10)); //machine, se necessario (se non ha ricevuto update) setta OFF)
	}
	
	public VendingMachine selectVendingMachine(String id) {
		return null;
		
	}
	
	public void addOperator(String id) throws AddingOperatorException {
		//da usare per il database
	}
	
	public void addRemoteOperator (String id) throws AddingOperatorException {
		//da usare per il database
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
	
}
