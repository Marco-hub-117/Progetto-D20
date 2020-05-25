package it.unipv.ingsw.d20.controller;

import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.gui.CustomerGui;
import it.unipv.ingsw.d20.gui.Pulsante;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class Controller {
	private VendingMachine m;
	private CustomerGui gui;


	public Controller(VendingMachine m,CustomerGui gui) {
		this.gui=gui;
		this.m=m;
		
		addListenerA();
		addListenerB();
	}

	private void addListenerA() {
		for(Pulsante puls:gui.getLA()) {
			puls.addActionListener(new Listener(puls.getValue(), m, gui)); 
		}
	}
	private void addListenerB() {
		for(Pulsante puls:gui.getLB()) {
			puls.addActionListener(new Listener(puls.getValue(), m, gui));
		}
	}
	

}


