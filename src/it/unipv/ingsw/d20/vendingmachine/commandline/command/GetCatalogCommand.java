package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Comando che permette di visualizzare il catalogo della macchinetta.
 *
 */
public class GetCatalogCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		String catalog;
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'getcatalog'");
			
			catalog = vm.getCatalog().toString();
		} catch (CommandFormatException e) {
			return e.getMessage();
		} 
		
		return catalog;
	}

}
