package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

public class WindowClosingListener extends WindowAdapter {
	
	private VendingMachine m;
	
	public WindowClosingListener(VendingMachine m) {
		this.m = m;
	}
	
	public void windowClosing(WindowEvent e) {
		//PersistenceFacade pf = PersistenceFacade.getInstance();
		//IVendingDao v = pf.getVendingDao();
		
		//v.updateVendingStatus(m.getId(), VendingMachineStatus.OFF);
		
		System.out.println("Status OFF");
		m.setStatus(VendingMachineStatus.OFF); //superfluo
	}

}
