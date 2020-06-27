package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorLoginFrame;

public class ToOperatorListener implements ActionListener {

	private VendingMachine vm;
	private CustomerGui gui;
	
	private OperatorLoginFrame opLogin;
	
	public ToOperatorListener(VendingMachine vm, CustomerGui userGui) {
		this.vm = vm;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		opLogin = new OperatorLoginFrame();
		opLogin.getEnterButton().addActionListener(al -> {
			if (vm.isCorrectId(opLogin.getInsertedKey())) { //controlla che la chiave inserita corrisponda all'id della macchina
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
