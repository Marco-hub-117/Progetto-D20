package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;

@SuppressWarnings("serial")
public class UnrecognisedKeyException extends Exception {
	
	public UnrecognisedKeyException() {
		super("Chiavetta non riconosciuta");
	}

}
