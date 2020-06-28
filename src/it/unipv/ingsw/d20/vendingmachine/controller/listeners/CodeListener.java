package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.InsufficientIngredientsException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.NonExistentCodeException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;

public class CodeListener implements ActionListener {
	
	private double value;	
	private VendingMachine vm;
	private CustomerGui gui;
	
	public CodeListener(double value, VendingMachine vm, CustomerGui userGui) {
		this.value = value;
		this.vm = vm;
		this.gui = userGui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int intValue = (int) value;
		if (intValue == 10) { //Ok button
			gui.setEnabled(false);
			try {
				vm.insertCode(gui.getDisplay());
			} catch (InsufficientCreditException | NonExistentCodeException | InsufficientIngredientsException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} finally {
				double credit = vm.getCredit();
				String creditToString = String.format("%.2f", credit);
				gui.setDisplay("E" + creditToString);
				gui.setEnabled(true);
			}
		}
		else if (intValue == -1) { //Canc button
			StringBuilder sbDisp = new StringBuilder(gui.getDisplay());
			sbDisp.deleteCharAt(sbDisp.length() - 1);
			if (sbDisp.toString().isEmpty() || sbDisp.toString().startsWith("E")) {
				Double credit = vm.getCredit();
				String creditToString = String.format("%.2f", credit);
				gui.setDisplay("E" + creditToString);
			} else {
				gui.setDisplay(sbDisp.toString());
			}
		}
		else {
			String display = "";
			if (!gui.getDisplay().startsWith("E")) {
				display = gui.getDisplay() + intValue;
			} else {
				display = String.valueOf(intValue);
			}
			gui.setDisplay(display);
		}
	}
}