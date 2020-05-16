package it.unipv.ingsw.d20.model.vendingmachine.exceptions;

public class VendingMachineAbsentException extends Exception {
	
	public VendingMachineAbsentException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}
}
