package it.unipv.ingsw.d20.vendingmachine.model.exceptions;
/**
 * L'eccezione viene generata se si cerca di ritirare l'importo minore di 10€
 *
 */
@SuppressWarnings("serial")
public class WithdrawAmountException extends Exception{

	public WithdrawAmountException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}
	

}
