package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.KeyRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;

/**
 * Listener dei tasti che riguardano l'inserimento di una moneta
 * o l'erogazione del resto.
 *
 */
public class CoinListener implements ActionListener {
	
	private double value;	
	private VendingMachine vm;
	private CustomerGui gui;
	
	/**
	 * Istanzia il valore del tasto annesso, la vending machine e la gui.
	 * @param valore del tasto
	 * @param vm istanza di vending machine
	 * @param userGui gui dell'utente
	 */
	public CoinListener(double value, VendingMachine vm, CustomerGui userGui) {
		this.value = value;
		this.vm = vm;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (value == -1) { //se e' -1 restituisce il resto
			try {
				double buffCredit = vm.getCredit();
				vm.dispenseCash();
				JOptionPane.showMessageDialog(null, "Dispensed ï¿½" + String.format("%.2f", buffCredit));
			} catch (InsufficientCashForRestException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (KeyRestException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else { //altrimenti inserisce una moneta
			vm.insertCoin(this.value);
		}
		
		//aggiorna la grafica solo se non è stato inserito un codice
		if (gui.getDisplay().startsWith("E")) {
			double credit = vm.getCredit();
			String creditToString = String.format("%.2f", credit);
			gui.setDisplay("E" + creditToString);
		}
	}
	
}
