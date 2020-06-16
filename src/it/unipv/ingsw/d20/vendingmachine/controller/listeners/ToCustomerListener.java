package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;

public class ToCustomerListener implements ActionListener{

	private VendingMachine m;
	private OperatorGui gui;
	
	public ToCustomerListener(VendingMachine m, OperatorGui gui) {
		this.m = m;
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 gui.dispose(); //chiudo l'interfaccia dell'operatore
		 CustomerGui gui= new CustomerGui(); //istanzio una nuova interfaccia del cliente
		 new Controller(m, gui); //controller associato all'interfaccia del cliente
		 m.setStatus(VendingMachineStatus.ONLINE);
	}

}
