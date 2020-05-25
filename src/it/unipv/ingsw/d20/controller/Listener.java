package it.unipv.ingsw.d20.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.gui.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class Listener implements ActionListener{
	private double value;
	private VendingMachine m;
	private CustomerGui gui;
	
	public Listener(double value, VendingMachine c, CustomerGui gui) {
		this.value=value;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("ciao");
	}
	
}

