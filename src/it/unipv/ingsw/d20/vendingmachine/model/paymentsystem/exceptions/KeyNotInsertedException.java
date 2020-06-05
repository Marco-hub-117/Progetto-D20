package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;

@SuppressWarnings("serial")
public class KeyNotInsertedException extends Exception {
	
	public KeyNotInsertedException() {
		super("There isn't a key inserted");
	}

}
