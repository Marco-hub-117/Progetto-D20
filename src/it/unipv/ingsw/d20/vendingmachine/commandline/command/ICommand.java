package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public interface ICommand {

	String execute(VendingMachine vm, String args);
	
}
