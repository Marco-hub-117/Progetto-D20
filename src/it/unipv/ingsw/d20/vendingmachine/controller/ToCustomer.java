package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ToCustomer implements ActionListener{

	private VendingMachine m;
	private OperatorGui gui;
	
	public ToCustomer(VendingMachine m, OperatorGui gui) {
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 gui.dispose(); //chiudo l'interfaccia dell'operatore
		 CustomerGui gui= new CustomerGui(); //istanzio una nuova interfaccia del cliente
		 Controller c=new Controller(m, gui); //controller associato all'interfaccia del cliente
		 gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 gui.setVisible(true);
	}

}
