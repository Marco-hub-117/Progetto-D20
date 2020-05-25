package it.unipv.ingsw.d20.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.gui.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ListenerA implements ActionListener{
	private int value;	
	private VendingMachine m;
	private CustomerGui gui;
	
	public ListenerA(int value, VendingMachine m, CustomerGui gui) {
		this.value=value;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(this.value) {
		case(10):
			gui.setPrice("10");
		break;
		case(-1):
			gui.setDisplay("");
		break;
		default:
			String s="";
			s=s+gui.getDisplay();
			gui.setDisplay(s+this.value);
		}
	}
}

