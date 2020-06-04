package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ListenerTank implements ActionListener{
	private VendingMachine m;
	private OperatorGui gui;
	
	public ListenerTank(VendingMachine m, OperatorGui gui) {
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//m.setTankLevel(id, level);
		System.out.println("ciao");
	}

}
