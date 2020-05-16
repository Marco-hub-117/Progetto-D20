package it.unipv.ingsw.d20.model.vendingmachine.exceptions;

public class RefillMachineException extends Exception {
	
	public RefillMachineException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}

}
