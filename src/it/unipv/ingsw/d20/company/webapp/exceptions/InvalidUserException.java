package it.unipv.ingsw.d20.company.webapp.exceptions;

@SuppressWarnings("serial")
public class InvalidUserException extends Exception {

	public InvalidUserException() {
		super("Invalid user entered");
	}

}
