package it.unipv.ingsw.d20.vendingmachine.model.exceptions;

@SuppressWarnings("serial")
public class RefillMachineException extends Exception {
	
	public RefillMachineException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}

}
