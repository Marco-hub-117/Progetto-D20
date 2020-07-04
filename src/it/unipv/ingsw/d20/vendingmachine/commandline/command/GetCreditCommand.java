package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Comando che permette di ottenere il credito attuale nella macchinetta.
 *
 */
public class GetCreditCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		double credit;
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'getcredit'");
			
			credit = vm.getCredit();
		} catch (CommandFormatException e) {
			return e.getMessage();
		}
		
		return "Il credito corrente ammonta a €" + String.format("%.2f", credit);
	}

}
