package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;

public class ToOperatorListener implements ActionListener {

	private VendingMachine m;
	private CustomerGui gui;
	
	public ToOperatorListener(VendingMachine m, CustomerGui userGui) {
		this.m = m;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 gui.dispose(); //chiudo l'interfaccia del cliente
		 OperatorGui gui= new OperatorGui(m.getTankNumber()); //istanzio una nuova interfaccia dell'operatore
		 new Controller(m, gui); //controller associato all'interfaccia dell'operatore
		 m.setCurrentAmount(0);
		 m.setStatus(VendingMachineStatus.REFILL);
		 gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 gui.setVisible(true);
	}

}
