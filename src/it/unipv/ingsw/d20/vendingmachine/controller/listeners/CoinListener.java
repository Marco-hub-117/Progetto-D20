package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.KeyRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;

public class CoinListener implements ActionListener{
	
	private double value;	
	private VendingMachine vm;
	private CustomerGui gui;
	
	public CoinListener(double value, VendingMachine vm, CustomerGui userGui) {
		this.value = value;
		this.vm = vm;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (value == -1) { //se e' -1 restituisce il resto
			try {
				double buffCredit = vm.getCurrentAmount();
				vm.dispenseCash();
				JOptionPane.showMessageDialog(null, "Dispensed �" + String.format("%.2f", buffCredit));
			} catch (InsufficientCashForRestException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (KeyRestException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else { //altrimenti inserisce una moneta
			vm.insertCoin(this.value);
		}
		
		//aggiorno grafica
		Double credit = vm.getCurrentAmount();
		String creditToString = String.format("%.2f", credit);
		gui.setDisplay("E" + creditToString);
	}
	
}
