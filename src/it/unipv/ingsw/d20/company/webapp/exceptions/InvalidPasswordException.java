package it.unipv.ingsw.d20.company.webapp.exceptions;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {

	public InvalidPasswordException() {
		super("Invalid password entered");
	}

}
