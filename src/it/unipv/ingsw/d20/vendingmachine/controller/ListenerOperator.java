package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ListenerOperator implements ActionListener{

	private VendingMachine m;
	private CustomerGui gui;
	
	public ListenerOperator(VendingMachine m, CustomerGui gui) {
	
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 gui.dispose();
		 OperatorGui gui= new OperatorGui(m.nTank());
		 //OperatorGui gui= new OperatorGui(5);
		 Controller c=new Controller(this.m, gui);
		 gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 gui.setVisible(true);
	}

}
