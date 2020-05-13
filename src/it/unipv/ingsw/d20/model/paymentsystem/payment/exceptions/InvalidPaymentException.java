package it.unipv.ingsw.d20.model.paymentsystem.payment.exception;

/**
 * @author Luigi Zaccaria Del Pio
 *
 */
@SuppressWarnings("serial")
public class InvalidPaymentException extends Exception {
	
	public InvalidPaymentException() {
		super("Payment rejected");
	}

}
