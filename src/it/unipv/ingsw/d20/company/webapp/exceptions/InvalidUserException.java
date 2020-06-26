package it.unipv.ingsw.d20.company.webapp.exceptions;

/**
 * Eccezione sollevata quando lo username inserito non è quello di un utente registrato sul DB.
 *
 */
@SuppressWarnings("serial")
public class InvalidUserException extends Exception {

	public InvalidUserException() {
		super("Invalid user entered");
	}

}
