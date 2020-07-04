package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Interfaccia che devono implementare tutte i comandi
 * eseguibili tramite terminale.
 *
 */
public interface ICommand {

	/**
	 * Esegue le azioni effettive del comando.
	 * @param vm istanza di VedingMachine
	 * @param args argomenti del comando
	 * @return result risultato del comando
	 */
	String execute(VendingMachine vm, String args);
	
}
