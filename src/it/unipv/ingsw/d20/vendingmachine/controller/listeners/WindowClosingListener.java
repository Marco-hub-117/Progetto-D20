package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

/**
 * Listener che aggiorna la company un'ultima volta prima di terminare
 * il programma.
 *
 */
public class WindowClosingListener extends WindowAdapter {
	
	private VendingMachine vm;
	
	/**
	 * Costruttore che istanzia la vending machine.
	 * @param vm vending machine
	 */
	public WindowClosingListener(VendingMachine vm) {
		this.vm = vm;
	}
	
	/**
	 * Imposta lo status della macchinetta su OFF e notifica la company
	 * che è stato uno spegnimento regolare.
	 */
	public void windowClosing(WindowEvent e) {
		vm.setStatus(VendingMachineStatus.OFF);
		
		try {
			VendingMachineClient vmc = new VendingMachineClient();
			vmc.connectToServer(vm.getInfo());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
