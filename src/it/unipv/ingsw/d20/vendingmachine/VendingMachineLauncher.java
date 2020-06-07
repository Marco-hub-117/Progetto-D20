package it.unipv.ingsw.d20.vendingmachine;

import java.io.IOException;
import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.BvCatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.persistence.LocalIOHandler.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.CashContainer;

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
				
				//Inizializzazione dei file locali
				IBvCatalogDao bv = pf.getBvCatalogDao(); //inizializza il catalogo recuperandolo dal DB
				v.saveCatalogIntoLocal(bv.getBeverageCatalog());				
			} else {
				System.out.println(client.connectToServer(IDNumber));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//fare partire la vending machine new VendingMachine();

	}

}
