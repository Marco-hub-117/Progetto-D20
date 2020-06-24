package it.unipv.ingsw.d20.vendingmachine.model;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.util.persistence.sale.ISaleDao;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;

public class UpdateStatus extends TimerTask {

	@Override
	public void run() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		//VendingLocalIO v = pf.getVendingLocalIO();
		
		try {
			VendingMachineClient vmc = new VendingMachineClient();
			vmc.connectToServer(VendingMachine.info);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Sale> saleList = null; //TODO = v.getSaleFromLocal();
		ISaleDao saleDao = pf.getSaleDao();
		
		for (Sale s : saleList) {
			//aggiungere la sale al database
		}
		
		//TODO v.emptyLocalSale(); 
		//svuota il file locale con le sale, sono ormai nel database

	}

}