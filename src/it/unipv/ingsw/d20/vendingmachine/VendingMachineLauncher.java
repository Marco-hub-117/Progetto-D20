package it.unipv.ingsw.d20.vendingmachine;

import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.ingsw.d20.util.persistence.PersistenceFactory;
import it.unipv.ingsw.d20.util.persistence.beveragecatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;
import it.unipv.ingsw.d20.vendingmachine.view.MalfunctionGeneratorFrame;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;
/**
 * Classe di inizializzazione di un distributore automatico
 *
 */
public class VendingMachineLauncher {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		VendingMachine vm = new VendingMachine(IDNumber);
		CustomerGui gui= new CustomerGui();
		
		new Controller(vm,gui);
		
		new MalfunctionGeneratorFrame(IDNumber); //crea il frame per la terminazione incontrollata della vending machine
	}

}
