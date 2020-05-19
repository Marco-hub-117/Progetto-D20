package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions;

@SuppressWarnings("serial")
public class InsufficientCashForRestException extends Exception {
	
	public InsufficientCashForRestException() {
		super("There isn't enough cash to dispense rest");
	}

}
