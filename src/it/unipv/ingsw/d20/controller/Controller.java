package it.unipv.ingsw.d20.controller;

import it.unipv.ingsw.d20.gui.CustomerGui;
import it.unipv.ingsw.d20.gui.Pulsante;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class Controller {
	private VendingMachine m;
	private CustomerGui gui;


	public Controller(VendingMachine m,CustomerGui gui) {
		this.gui=gui;
		this.m=m;
		setText();
		addListenerA();
		addListenerB();
	}
	private void setText() {
		gui.setText(m.getCatalog().toString());
	}
	private void addListenerA() {
		for(Pulsante puls:gui.getLA()) {
			puls.addActionListener(new ListenerA(puls.getValue(), m, gui)); 
		}
	}
	private void addListenerB() {
		for(Pulsante puls:gui.getLB()) {
			puls.addActionListener(new ListenerB(puls.getValue(), m, gui));
		}
	}
	

}


