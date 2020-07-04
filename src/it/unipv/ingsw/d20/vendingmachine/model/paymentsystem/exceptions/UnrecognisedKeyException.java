package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;

/**
 * L'eccezione viene lanciata quando non si riesce a leggere una chiavetta inserita
 */
@SuppressWarnings("serial")
public class UnrecognisedKeyException extends Exception {
	
	public UnrecognisedKeyException() {
		super("Chiavetta non riconosciuta");
	}

}
