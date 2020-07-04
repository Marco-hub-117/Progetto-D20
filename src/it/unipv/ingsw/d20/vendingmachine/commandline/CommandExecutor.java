package it.unipv.ingsw.d20.vendingmachine.commandline;

import it.unipv.ingsw.d20.vendingmachine.commandline.command.ICommand;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Classe che manda effettivamente in esecuzione il comando.
 *
 */
public class CommandExecutor {
	
	private VendingMachine vendingMachine;

	public CommandExecutor(VendingMachine vm) {
		vendingMachine = vm;
	}
	
	/**
	 * Esegue il comando passato come parametro con gli argomenti, anch'essi
	 * ricevuti dal chiamante.
	 * @param command comando da eseguire
	 * @param args argomenti del comando
	 * @return result risultato del comando
	 */
	public String executeCommand(ICommand command, String args) {
        return command.execute(vendingMachine, args);
    }

}
