package it.unipv.ingsw.d20.model.vendingmachine.exceptions;

public class WithdrawAmountException extends Exception{
	
	/**
	 * Eccezione se si cerca di ritirare l'importo minore di 10€
	 * @param message
	 */
	
	public WithdrawAmountException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}
	

}
