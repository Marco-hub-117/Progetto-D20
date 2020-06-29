package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.InsufficientIngredientsException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.NonExistentCodeException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;
import it.unipv.ingsw.d20.vendingmachine.view.customer.Beverage;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;

/**
 * Listener dei tasti che riguardano l'inserimento del codice da 
 * parte dell'utente.
 *
 */
public class CodeListener implements ActionListener {
	
	private double value;	
	private VendingMachine vm;
	private CustomerGui gui;
	
	/**
	 * Istanzia il valore del tasto annesso, la vending machine e la gui.
	 * @param value valore del tasto
	 * @param vm istanza di vending machine
	 * @param userGui gui dell'utente
	 */
	public CodeListener(double value, VendingMachine vm, CustomerGui userGui) {
		this.value = value;
		this.vm = vm;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int intValue = (int) value;
		if (intValue == 10) { //tasto Ok
			gui.setEnabled(false); //disabilita la gui durante l'erogazione
			try {
				String bevName = vm.insertCode(gui.getDisplay()); 
				
				Beverage bev = new Beverage(bevName); bev.start(); //eroga la bevanda
				System.out.println("Erogazione avvenuta con successo.");
			} catch (InsufficientCreditException | NonExistentCodeException | InsufficientIngredientsException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} finally {
				double credit = vm.getCredit();
				String creditToString = String.format("%.2f", credit);
				gui.setDisplay("E" + creditToString); //dopo che è stato premuto il tasto Ok viene sempre visualizzato il credito
				gui.setEnabled(true);
			}
		}
		else if (intValue == -1) { //tasto Canc
			StringBuilder displayBuilder = new StringBuilder(gui.getDisplay());
			displayBuilder.deleteCharAt(displayBuilder.length() - 1); //cancella l'ultimo carattere del display
			if (displayBuilder.toString().isEmpty() || displayBuilder.toString().startsWith("E")) { //se il display è vuoto o inizia per E (credito) mostra il credito
				Double credit = vm.getCredit();
				String creditToString = String.format("%.2f", credit);
				gui.setDisplay("E" + creditToString);
			} else { //altrimenti mostra il codice inserito rimasto dopo la cancellazione
				gui.setDisplay(displayBuilder.toString());
			}
		}
		else { //tasti numerici
			String display = "";
			if (!gui.getDisplay().startsWith("E")) { //se non era presente il credito a display
				display = gui.getDisplay() + intValue; //concatena il numero inserito con il precedente
			} else {
				display = String.valueOf(intValue); //altrimenti toglie il credito e mostra il numero inserito
			}
			gui.setDisplay(display);
		}
	}
}