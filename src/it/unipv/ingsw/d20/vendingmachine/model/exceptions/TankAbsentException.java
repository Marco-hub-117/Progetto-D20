package it.unipv.ingsw.d20.vendingmachine.model.exceptions;

@SuppressWarnings("serial")
public class TankAbsentException extends Exception{
	
	public TankAbsentException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}

}
