package it.unipv.ingsw.d20.vendingmachine;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.beveragecatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.UpdateStatus;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

public class VendingMachineLauncher {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingIDFromLocal();
		
		VendingMachine vm = new VendingMachine(IDNumber);
		CustomerGui gui= new CustomerGui();
		
		new Controller(vm,gui);
		
		/*try {
			VendingMachineClient client = new VendingMachineClient();
		
			if (IDNumber.equals("")) {
				IDNumber = client.firstConnectionToServer();
				v.saveVendingIDIntoLocal(IDNumber);
				System.out.println("IDNumber printed");
				
				//Inizializzazione dei file locali (catalogo preso dal db)
				IBvCatalogDao bv = pf.getBvCatalogDao(); 
				v.saveCatalogIntoLocal(bv.getBeverageCatalog(Integer.parseInt(v.getVendingTypeFromLocal())));				
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Timer timer = new Timer();
		timer.schedule(new UpdateStatus(), new Date(), TimeUnit.MINUTES.toMillis(10)); //ogni 10 minuti viene notificata la company */
	}

}
