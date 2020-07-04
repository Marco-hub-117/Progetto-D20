package it.unipv.ingsw.d20.vendingmachine;

import java.io.IOException;

import it.unipv.ingsw.d20.util.persistence.PersistenceFactory;
import it.unipv.ingsw.d20.util.persistence.beveragecatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.commandline.CommandReceiver;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

public class CommandLineLauncher {
	
	public static void main(String[] args) {		
		PersistenceFactory pf = PersistenceFactory.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingIDFromLocal();
		int type = Integer.parseInt(v.getVendingTypeFromLocal());
		
		try {
			if (IDNumber.equals("")) {
				VendingMachineClient client = new VendingMachineClient();
				
			    IDNumber = client.firstConnectionToServer(String.valueOf(type)); //manda al DB il tipo e riceve un ID
				v.saveVendingIDIntoLocal(IDNumber);
				System.out.println("Registration completed.");
				
				//inizializzazione del catalogo
				IBvCatalogDao bv = pf.getBvCatalogDao(); 
				v.saveCatalogIntoLocal(bv.getBeverageCatalog(type));				
			} 
		
			VendingMachine vm = new VendingMachine(IDNumber);
			new CommandReceiver(vm); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
