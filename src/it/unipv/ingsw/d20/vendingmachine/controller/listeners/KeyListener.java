package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;

public class KeyListener implements ActionListener {
	
	private VendingMachine vm;
	private CustomerGui gui;
	private boolean value;
	
	public KeyListener(boolean value, VendingMachine vm, CustomerGui gui) {
		this.vm = vm;
		this.gui = gui;
		this.value = value;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (value) {
			try {
				vm.insertKey();
				gui.getInsertKeyButton().setEnabled(false);
				gui.getEjectKeyButton().setEnabled(true);
			} catch (UnrecognisedKeyException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			vm.ejectKey();
			gui.getInsertKeyButton().setEnabled(true);
			gui.getEjectKeyButton().setEnabled(false);
		}
		Double credit = vm.getCredit();
		String creditToString = String.format("%.2f", credit);
		gui.setDisplay("E" + creditToString);
	}
}
