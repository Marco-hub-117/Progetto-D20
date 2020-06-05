package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ListenerKey implements ActionListener {
	private VendingMachine m;
	private CustomerGui gui;
	private boolean value;
	public ListenerKey (boolean value, VendingMachine m, CustomerGui gui) {
		this.m=m;
		this.gui=gui;
		this.value=value;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (value) m.insertKey("");
		if (!value) m.ejectKey(0);
		
	}

}
