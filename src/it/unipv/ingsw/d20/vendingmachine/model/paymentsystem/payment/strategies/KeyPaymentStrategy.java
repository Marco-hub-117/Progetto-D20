package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class KeyPaymentStrategy extends AbstractPaymentStrategy {

	public double elaboratePayment(double amount, double price) throws InsufficientCreditException, InvalidPaymentException {
		
		//controllo di validità: se chiavetta non riconosciuta solleva InvalidPaymentException
		
		double change=quickCheck(amount, price);
		
		return change;
	}
	
}
