package it.unipv.ingsw.d20.vendingmachine.model.exceptions;

public class NonExistentCodeException extends Exception { //viene lanciata se l'utente digita una bevanda non corretta, è catturata nel controller
	public NonExistentCodeException(String message) {
		super(message);
	}
}
