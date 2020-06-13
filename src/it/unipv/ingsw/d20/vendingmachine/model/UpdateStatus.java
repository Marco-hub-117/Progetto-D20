package it.unipv.ingsw.d20.vendingmachine.model;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.persistence.sale.ISaleDao;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;

public class UpdateStatus extends TimerTask {

	@Override
	public void run() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingIDFromLocal();
		
		try {
			VendingMachineClient vmc = new VendingMachineClient();
			
			vmc.connectToServer(IDNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Sale> saleList = v.getSaleFromLocal();
		ISaleDao saleDao = pf.getSaleDao();
		
		for (Sale s : saleList) {
			//aggiungere la sale al database
		}
		
		v.emptyLocalSale(); //svuota il file locale con le sale, sono ormai nel database

	}

}