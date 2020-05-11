package it.unipv.ingsw.d20.paymentsystem.payment;

/**
 * 
 * @author 
 * @author Luigi Zaccaria Del Pio
 *
 */
@SuppressWarnings("serial")
public class PaymentNotValidException extends Exception {
	
	PaymentNotValidException (){
		super("Payment not valid");
	}

}
