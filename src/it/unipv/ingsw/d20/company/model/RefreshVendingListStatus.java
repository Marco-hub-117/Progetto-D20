package it.unipv.ingsw.d20.company.model;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.vending.IVendingDao;

import java.util.TimerTask;

public class RefreshVendingListStatus extends TimerTask {

	@Override
	public void run() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		IVendingDao v = pf.getVendingDao();
		
		
		Date now = new Date();
		
		for (Entry<String, Date> entry : Company.vendingMachineStatusList.entrySet()) {
			Date lastUpdate = entry.getValue();
			
			if ((now.getTime() - lastUpdate.getTime()) > TimeUnit.MINUTES.toMillis(11)) { //se sono passati più di 11 minuti dall'ultimo update
				//mette a OFF la relativa vending machine nel DB
			}
		}

	}

}
