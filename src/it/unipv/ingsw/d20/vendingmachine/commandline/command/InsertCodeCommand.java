package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.InsufficientIngredientsException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.NonExistentCodeException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;

public class InsertCodeCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		try {
			vm.insertCode(args);
		} catch (InsufficientCreditException | NonExistentCodeException | InsufficientIngredientsException e) {
			return e.getMessage();
		}
		
		return vm.getCatalog().getBeverageDesc(args).getName() + " erogata";
	}

}
