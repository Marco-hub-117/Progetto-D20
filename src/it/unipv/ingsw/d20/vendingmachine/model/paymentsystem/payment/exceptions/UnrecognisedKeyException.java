package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions;

@SuppressWarnings("serial")
public class UnrecognisedKeyException extends Exception {
	
	public UnrecognisedKeyException() {
		super("Key not recognised");
	}

}
