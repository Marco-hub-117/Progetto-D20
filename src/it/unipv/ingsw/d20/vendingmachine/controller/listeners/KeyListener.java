package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.KeyNotInsertedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;

/**
 * Listener dei tasti che riguardano l'inserimento e l'espulsione
 * di una chiavetta.
 *
 */
public class KeyListener implements ActionListener {
	
	private VendingMachine vm;
	private CustomerGui gui;
	private boolean value;
	
	/**
	 * Istanzia il valore del tasto annesso, la vending machine e la gui.
	 * @param value valore del tasto
	 * @param vm istanza di vending machine
	 * @param gui gui dell'utente
	 */
	public KeyListener(boolean value, VendingMachine vm, CustomerGui gui) {
		this.vm = vm;
		this.gui = gui;
		this.value = value;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (value) { //se value == true il listener tratta l'inserimento della chiavetta
			try {
				vm.insertKey();
				gui.getInsertKeyButton().setEnabled(false);
				gui.getEjectKeyButton().setEnabled(true);
			} catch (UnrecognisedKeyException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else { //se value == true il listener tratta l'espulsione della chiavetta
			try {
				vm.ejectKey();
				gui.getInsertKeyButton().setEnabled(true);
				gui.getEjectKeyButton().setEnabled(false);
			} catch (KeyNotInsertedException e) {
				e.printStackTrace();
			}
		}
		
		//aggiorna la grafica
		double credit = vm.getCredit();
		String creditToString = String.format("%.2f", credit);
		gui.setDisplay("E" + creditToString);
	}
}
