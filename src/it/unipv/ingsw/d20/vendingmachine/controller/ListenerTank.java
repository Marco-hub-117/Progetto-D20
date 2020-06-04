package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.Pulsante;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.RefillMachineException;

public class ListenerTank implements ActionListener{
	private VendingMachine m;
	private OperatorGui gui;
	private int pos;
	
	public ListenerTank(int pos, VendingMachine m, OperatorGui gui) {
		this.pos=pos;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Pulsante p=gui.getPulsanti()[pos];
		try {
			m.setTankLevel(p.getIdTank());
		} catch (RefillMachineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ciao");
	}

}
