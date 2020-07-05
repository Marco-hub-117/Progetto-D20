package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorLoginFrame;

/**
 * Listener del tasto che permette di passare alla modalita' operatore: alla pressione,
 * prima viene richiesta la chiave della macchinetta, dopodiche', se giusta, si passa
 * alla modalita' operatore vera e propria.
 *
 */
public class ToOperatorListener implements ActionListener {

	private VendingMachine vm;
	private CustomerGui gui;
	
	private OperatorLoginFrame opLogin;
	
	/**
	 * Istanzia la vending machine e la gui.
	 * @param vm istanza di vending machine
	 * @param userGui gui dell'utente
	 */
	public ToOperatorListener(VendingMachine vm, CustomerGui userGui) {
		this.vm = vm;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		opLogin = new OperatorLoginFrame(); //apre il frame per l'inserimento della chiave
		opLogin.getEnterButton().addActionListener(al -> { //aggiunge il listener al bottone del frame appena aperto
			if (vm.enterOperatorMode(opLogin.getInsertedKey())) { //controlla che la chiave inserita corrisponda all'id della macchina
				opLogin.dispose();
				openOperatorGui(); //se è vero entra in modalità operatore
			} else {
				opLogin.setWrongKeyWarning();
			}
		});		
	}
	
	private void openOperatorGui() {
		gui.dispose(); //chiudo l'interfaccia del cliente
		OperatorGui gui = new OperatorGui(vm.getTankNumber()); //istanzio una nuova interfaccia dell'operatore
		new Controller(vm, gui); //controller associato all'interfaccia dell'operatore
		vm.resetCredit();
		vm.setStatus(VendingMachineStatus.REFILL);
	}

}
