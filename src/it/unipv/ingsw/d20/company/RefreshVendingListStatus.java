package it.unipv.ingsw.d20.company;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

import java.util.TimerTask;

/**
 * Classe che contiene un task da eseguire periodicamente che si occupa
 * di controllare che tutte le macchinette siano correttamente funzionanti.
 *
 */
public class RefreshVendingListStatus extends TimerTask {

	/**
	 * Questo metodo si occupa di controllare la quantità di tempo passata dall'ultimo
	 * update di ciascuna macchinetta: se è superiore a 11 minuti ne impost alo status
	 * sul data base su DISCONNECTED. Però, se lo status era OFF non succede nulla.
	 */
	@Override
	public void run() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		IVendingDao v = pf.getVendingDao();
		
		Date now = new Date();
		
		for (Entry<String, Date> entry : Company.vendingMachineStatusList.entrySet()) {
			Date lastUpdate = entry.getValue();
			
			if ((v.getVendingStatusById(entry.getKey()) != VendingMachineStatus.OFF) && 
					(now.getTime() - lastUpdate.getTime()) > TimeUnit.MINUTES.toMillis(11)) { //se sono passati più di 11 minuti dall'ultimo update
				v.updateVendingStatus(entry.getKey(), VendingMachineStatus.DISCONNECTED); //imposta lo status della relativa macchinetta nel database su DISCONNECTED
			}
		}

	}

}
