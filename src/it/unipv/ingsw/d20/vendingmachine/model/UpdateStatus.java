package it.unipv.ingsw.d20.vendingmachine.model;

import java.io.IOException;
import java.util.TimerTask;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

public class UpdateStatus extends TimerTask {

	@Override
	public void run() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingID();
		
		try {
			VendingMachineClient vmc = new VendingMachineClient();
			
			vmc.connectToServer(IDNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}