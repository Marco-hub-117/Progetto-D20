package it.unipv.ingsw.d20.company.webapp;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {

	public InvalidPasswordException() {
		super("Invalid password entered");
	}

}
