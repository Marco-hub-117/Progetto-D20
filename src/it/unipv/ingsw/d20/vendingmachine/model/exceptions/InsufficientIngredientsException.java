package it.unipv.ingsw.d20.vendingmachine.model.exceptions;

public class InsufficientIngredientsException extends Exception { //se l'utente chiede una bevanda ma non bastano gli ingredienti

	public InsufficientIngredientsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


}
