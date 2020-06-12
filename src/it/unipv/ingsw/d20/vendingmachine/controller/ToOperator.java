package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import guitest.MainWindow;
import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

public class ToOperator implements ActionListener {

	private VendingMachine m;
	private MainWindow gui;
	
	public ToOperator(VendingMachine m, MainWindow userGui) {
		this.m = m;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 gui.dispose(); //chiudo l'interfaccia del cliente
		 OperatorGui gui= new OperatorGui(m.getTankNumber()); //istanzio una nuova interfaccia dell'operatore
		 new guitest.Controller(m, gui); //controller associato all'interfaccia dell'operatore
		 m.setCurrentAmount(0);
		 m.setStatus(VendingMachineStatus.REFILL);
		 gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 gui.setVisible(true);
	}

}
