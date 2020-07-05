package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Classe che permette di entrare in modalità operatore, a patto
 * che venga inserita la chiave corretta.
 *
 */
public class EnterOperatorModeCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		String result;
		try {
			if (args == null)
				throw new CommandFormatException("Argomento non valido per il comando 'enteropmode'");
			
			if (vm.enterOperatorMode(args))
				result = "Sei ora in modalità operatore";
			else
				result = "La chiave inserita è errata";
		} catch (CommandFormatException e) {
			return e.getMessage();
		}
		
			return result;
	}

}
