package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.KeyRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;

/**
 * Comando che permette di ricevere il resto.
 *
 */
public class DispenseRestCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		double rest = vm.getCredit();
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'dispenserest'");
			
			vm.dispenseCash();
		} catch (InsufficientCashForRestException | KeyRestException | CommandFormatException e) {
			return e.getMessage();
		}
		
		return "Sono stati restituiti €" + String.format("%.2f", rest);
	}

}
