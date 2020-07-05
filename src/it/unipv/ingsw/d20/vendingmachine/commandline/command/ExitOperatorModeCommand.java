package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ExitOperatorModeCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'exitopmode'");
			
			vm.exitOperatorMode();
		} catch (CommandFormatException e) {
			return e.getMessage();
		}
		
		return "Non sei più in modalità operatore";
	}

}
