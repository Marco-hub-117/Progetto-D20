package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;

/**
 * Famiglia di eccezioni che vengono lanciate quando il pagamento
 * viene rifiutato.
 *
 */
@SuppressWarnings("serial")
public class InvalidPaymentException extends Exception {
	
	public InvalidPaymentException(String message) {
		super(message);
	}

}
