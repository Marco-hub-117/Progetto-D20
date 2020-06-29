package it.unipv.ingsw.d20.company;

import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

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
	 * update di ciascuna macchinetta: se è superiore a 2 minuti ne imposta lo status
	 * su DISCONNECTED. Però, se lo status era OFF, non succede nulla.
	 */
	@Override
	public void run() {
		Date now = new Date();
		
		for (Entry<String, VendingMachineInfo> entry : Company.vendingMachineInfoList.entrySet()) {
			VendingMachineInfo info = entry.getValue();
			Date lastUpdate = info.getLastUpdate();
			VendingMachineStatus currentStatus = info.getStatus();
			
			if ((currentStatus != VendingMachineStatus.OFF) && //se lo status � diverso da OFF
					(now.getTime() - lastUpdate.getTime()) > TimeUnit.MINUTES.toMillis(2)) { //e sono passati più di 2 minuti dall'ultimo update
						info.setStatus(VendingMachineStatus.DISCONNECTED); //imposta lo status della relativa macchinetta nel database su DISCONNECTED
			}
		}

	} 

}
