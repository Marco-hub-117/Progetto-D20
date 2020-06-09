package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;

public class ListenerKey implements ActionListener {
	private VendingMachine m;
	private CustomerGui gui;
	private boolean value;
	public ListenerKey (boolean value, VendingMachine m, CustomerGui gui) {
		this.m=m;
		this.gui=gui;
		this.value=value;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (value) {
			try {
				m.insertKey();
				gui.getIn().setEnabled(false);
				gui.getOut().setEnabled(true);
				
			} catch (UnrecognisedKeyException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		if (!value) {
			m.ejectKey();
			gui.getIn().setEnabled(true);
			gui.getOut().setEnabled(false);
		}
		Double p=m.getCurrentAmount();
		String y= String.format ("%.2f", p);
		gui.setAmount(y);
	}

}
