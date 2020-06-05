package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ListenerB implements ActionListener{
	private double value;	
	private VendingMachine m;
	private CustomerGui gui;
	
	public ListenerB(double value, VendingMachine m, CustomerGui gui) {
		this.value=value;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.insertCoin(this.value);
		Double p=m.getCurrentAmount();
		String y;
		y= String.format ("%.2f", p);
		gui.setAmount(y);
	}
	
}
