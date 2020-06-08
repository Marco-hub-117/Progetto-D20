package it.unipv.ingsw.d20.vendingmachine;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.beveragecatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.UpdateStatus;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

public class VendingMachineLauncher {

	public static void main(String[] args) {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingID();
		
		try {
			VendingMachineClient client = new VendingMachineClient();
		
			if (IDNumber.equals("")) {
				IDNumber = client.firstConnectionToServer();
				v.setVendingID(IDNumber);
				System.out.println("IDNumber printed");
				
				//Inizializzazione dei file locali (catalogo preso dal db)
				IBvCatalogDao bv = pf.getBvCatalogDao(); 
				v.saveCatalogIntoLocal(bv.getBeverageCatalog(Integer.parseInt(v.getVendingType())));				
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Timer timer = new Timer();
		timer.schedule(new UpdateStatus(), new Date(), TimeUnit.MINUTES.toMillis(10)); //ogni 10 minuti viene notificata la company
		
		//fare partire la vending machine + gui -- new VendingMachine();

	}

}
