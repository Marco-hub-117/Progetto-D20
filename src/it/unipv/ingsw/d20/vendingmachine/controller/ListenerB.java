package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;

public class ListenerB implements ActionListener{
	private double value;	
	private VendingMachine m;
	private CustomerGui gui;
	
	public ListenerB(double value, VendingMachine m, CustomerGui gui) {
		this.value=value;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(value==-1) { //eroga il resto
			try {
				m.dispenseCash();
			} catch (InsufficientCashForRestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//m.setCurrentAmount(0.0);
		}else{
			m.insertCoin(this.value);
		}
		//aggiorno grafica
		Double p=m.getCurrentAmount();
		String y= String.format ("%.2f", p);
		gui.setAmount(y);
	}
	
}
