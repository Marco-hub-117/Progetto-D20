package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;

public class ListenerA implements ActionListener{
	private double value;	
	private VendingMachine m;
	private CustomerGui gui;
	
	public ListenerA(double value, VendingMachine m, CustomerGui gui) {
		this.value=value;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int x=(int)this.value;
		switch(x) {
		case(10): //okay
			m.insertCode(gui.getDisplay());
		break;
		case(-1): //canc
			gui.setDisplay("");
		break;
		default:
			String s="";
			s=s+gui.getDisplay();
			gui.setDisplay(s+x);
		}
	}
}

