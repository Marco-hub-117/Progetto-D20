package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ToOperator implements ActionListener{

	private VendingMachine m;
	private CustomerGui gui;
	
	public ToOperator(VendingMachine m, CustomerGui gui) {
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 gui.dispose(); //chiudo l'interfaccia del cliente
		 OperatorGui gui= new OperatorGui(m.getTankNumber()); //istanzio una nuova interfaccia dell'operatore
		 Controller c=new Controller(m, gui); //controller associato all'interfaccia dell'operatore
		 gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 gui.setVisible(true);
	}

}
