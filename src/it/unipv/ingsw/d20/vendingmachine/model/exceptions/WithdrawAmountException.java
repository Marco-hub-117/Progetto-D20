package it.unipv.ingsw.d20.vendingmachine.model.exceptions;

@SuppressWarnings("serial")
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
