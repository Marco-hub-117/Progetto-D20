package it.unipv.ingsw.d20.model.vendingmachine.exceptions;

public class AddingMachineException extends Exception{
	
	private String message;
	
	public AddingMachineException(String message) {
		this.message = message;
	}
	
	public void printMessage() {
		System.out.println(message);

	}
	
	

}
