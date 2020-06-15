package it.unipv.ingsw.d20.company;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

import java.util.TimerTask;

public class RefreshVendingListStatus extends TimerTask {

	@Override
	public void run() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		IVendingDao v = pf.getVendingDao();
		
		Date now = new Date();
		
		for (Entry<String, Date> entry : Company.vendingMachineStatusList.entrySet()) {
			Date lastUpdate = entry.getValue();
			
			if ((v.getVendingStatusById(entry.getKey()) != VendingMachineStatus.OFF) && (now.getTime() - lastUpdate.getTime()) > TimeUnit.MINUTES.toMillis(11)) { //se sono passati più di 11 minuti dall'ultimo update
				v.updateVendingStatus(entry.getKey(), VendingMachineStatus.DISCONNECTED);
			}
		}

	}

}
