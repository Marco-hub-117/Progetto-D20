package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.KeyNotInsertedException;

public class EjectKeyCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'ejectkey'");
			
			vm.ejectKey();
		} catch (CommandFormatException | KeyNotInsertedException e) {
			return e.getMessage();
		}
		
		return "Chiavetta rimossa correttamente";
	}

}
