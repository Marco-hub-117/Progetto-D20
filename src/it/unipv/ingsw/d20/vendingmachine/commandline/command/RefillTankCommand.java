package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.InsufficientPermissionsException;

/**
 * Comando che permette di riempire il serbatoio il cui ID viene
 * passato come argomento.
 *
 */
public class RefillTankCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		try {
			if (args == null)
				throw new CommandFormatException("Argomento non valido per il comando 'refilltank'");
			
			vm.refillTank(args);
		} catch (CommandFormatException | InsufficientPermissionsException e) {
			return e.getMessage();
		} catch (IllegalArgumentException e) {
			return "Il tank " + args + " non esiste";
		}
		
		return "Il tank " + args + " è stato ricaricato";
	}

}
