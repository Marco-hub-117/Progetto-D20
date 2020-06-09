package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
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
			if(m.getCurrentAmount()==0) {
				JOptionPane.showMessageDialog(null, "Credito insufficiente!");
			}else{
				if(gui.getDisplay().equals("000")) {
					JOptionPane.showMessageDialog(null, "Devi selezionare una bevanda");
				}else {
					try {
						m.insertCode(gui.getDisplay());
					} catch (InsufficientCreditException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NonExistentCodeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gui.setDisplay("000");
					Double p=m.getCurrentAmount();
					String y;
					y= String.format ("%.2f", p);
					gui.setAmount(y);
					
				}
			}
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

