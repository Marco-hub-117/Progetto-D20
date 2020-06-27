package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;

/**
 * Famiglia di eccezioni per sviluppi futuri sui metodi di pagamento
 *
 */
@SuppressWarnings("serial")
public class InvalidPaymentException extends Exception {
	
	public InvalidPaymentException() {
		super("Payment rejected");
	}
	public InvalidPaymentException(String message) {
		super(message);
	}

}
