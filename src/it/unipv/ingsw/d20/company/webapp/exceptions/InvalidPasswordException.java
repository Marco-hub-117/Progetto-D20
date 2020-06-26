package it.unipv.ingsw.d20.company.webapp.exceptions;

/**
 * Eccezione sollevata quando la password non corrisponde allo username inserito.
 *
 */
@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {

	public InvalidPasswordException() {
		super("Invalid password entered");
	}

}
