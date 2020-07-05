package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Comando che permette di visualizzare tutta la lista dei comandi
 * disponibili, con una breve descrizione.
 *
 */
public class HelpCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		String helpResult;
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'help'");
			
			helpResult = buildHelpResult();
		} catch (CommandFormatException e) {
			return e.getMessage();
		}
		
		return helpResult;
	}

	private String buildHelpResult() {
		StringBuilder helpResultBuilder = new StringBuilder("I comandi disponibili sono i seguenti (se è presente "
				+ "un asterisco significa che bisogna avere i permessi da operatore):\n");
		helpResultBuilder.append("addcoin {coin_value} -> Inserisce una moneta nella macchinetta. L'argomento "
				+ "{coin_value} corrisponde al valore della moneta. I valori ammessi sono: 0.05, 0.1, 0.2, 0.5, 1 e 2.\n");
		helpResultBuilder.append("insertcode {code} -> Inserisce il codice della bevanda. I valori ammessi per {code} "
				+ "possono essere visionati tramite il catalogo.\n");
		helpResultBuilder.append("getcatalog -> Permette di visionare il catalogo.\n");
		helpResultBuilder.append("dispenserest -> Eroga il resto.\n");
		helpResultBuilder.append("getcredit -> -> Permette di controllare il credito corrente.\n");
		helpResultBuilder.append("insertkey -> Inserisce una chiavetta.\n");
		helpResultBuilder.append("ejectkey -> Espelle una chiavetta.\n");
		helpResultBuilder.append("enteropmode {key} -> Entra in modalità operatore se la chiave {key} è corretta.\n");
		helpResultBuilder.append("exitopmode -> Esce dalla modalità operatore.\n");
		helpResultBuilder.append("*refilltank {tank_id} -> Riempie un tank, indicato tramite {tank_id}. I valori "
				+ "ammessi per l'argomento dipendono dal tipo della macchinetta.\n");
		helpResultBuilder.append("*withdrawcash -> Permette di prelevare i contanti dalla macchinetta.\n");
		helpResultBuilder.append("stop -> Termina il programma.\n");
		return helpResultBuilder.toString();
	}

}
