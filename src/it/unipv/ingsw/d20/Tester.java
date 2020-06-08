package it.unipv.ingsw.d20;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * 
 * For testing purposes only, leave this class empty!
 *
 */
public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingID();
		
		VendingMachine vm = new VendingMachine(IDNumber);
		
		CustomerGui gui= new CustomerGui();
		Controller c=new Controller (vm,gui);

		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);

	}
}
