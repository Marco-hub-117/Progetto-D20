package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.KeyRestException;
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
		if(value==-1) { //se e' -1 si tratta del resto
			try {
				m.dispenseCash();
			} catch (InsufficientCashForRestException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (KeyRestException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}else{ //altrimenti di un inserimento di una moneta
			m.insertCoin(this.value);
		}
		//aggiorno grafica
		Double p=m.getCurrentAmount();
		String y= String.format("%.2f", p);
		gui.setAmount(y);
	}
	
}
