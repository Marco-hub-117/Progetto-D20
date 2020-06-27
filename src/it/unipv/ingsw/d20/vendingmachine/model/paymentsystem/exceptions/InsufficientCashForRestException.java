package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;
/**
 * L'eccezione viene lanciata quando non è possibile erogare il resto
 * 
 */
@SuppressWarnings("serial")
public class InsufficientCashForRestException extends Exception {
	
	public InsufficientCashForRestException() {
		super("Erogazione resto non possibile, ci dispiace");
	}

}
