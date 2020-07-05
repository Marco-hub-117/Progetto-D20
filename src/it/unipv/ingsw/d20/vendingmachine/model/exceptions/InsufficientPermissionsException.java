package it.unipv.ingsw.d20.vendingmachine.model.exceptions;

@SuppressWarnings("serial")
public class InsufficientPermissionsException extends Exception {
	
	public InsufficientPermissionsException(String msg) {
		super(msg);
	}

}
