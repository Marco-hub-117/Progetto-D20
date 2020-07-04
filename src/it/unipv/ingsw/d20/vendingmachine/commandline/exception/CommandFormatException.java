package it.unipv.ingsw.d20.vendingmachine.commandline.exception;

/**
 * Eccezione che viene lanciata quando il formato del comando non è corretto.
 *
 */
@SuppressWarnings("serial")
public class CommandFormatException extends Exception {

	public CommandFormatException(String msg) {
		super(msg);
	}
	
}
