package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.InsufficientIngredientsException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.NonExistentCodeException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;

public class ListenerA implements ActionListener{
	private double value;	
	private VendingMachine m;
	private CustomerGui gui;
	
	public ListenerA(double value, VendingMachine m, CustomerGui gui) {
		this.value=value;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int x=(int)this.value;
		switch(x) {
		case(10): //okay
			gui.setEnabled(false);
			try {
				m.insertCode(gui.getDisplay());
				Double p=m.getCurrentAmount();
				String y= String.format ("%.2f", p);
				gui.setAmount(y);
			} catch (InsufficientCreditException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (NonExistentCodeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (InsufficientIngredientsException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			gui.setDisplay("000"); 
			gui.setEnabled(true);
		break;
		case(-1): //canc
			gui.setDisplay("000");
		break;
		default:
			if(gui.getDisplay().equals("000")) gui.setDisplay("");
			String s="";
			s=s+gui.getDisplay();
			gui.setDisplay(s+x);
		}
	}
}

