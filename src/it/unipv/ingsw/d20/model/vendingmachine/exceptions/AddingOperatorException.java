package it.unipv.ingsw.d20.model.vendingmachine.exceptions;

public class AddingOperatorException extends Exception {

	public AddingOperatorException (String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}
}
