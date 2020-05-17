package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

public class KeyStrategy extends AbstractPaymentStrategy{

	double elaboratePayment(double amount, double price) throws InvalidPaymentException {
		
		//logica per vedere se la chiavetta è valida
		
		double change=quickCheck(amount, price);
		
		return change;
		
		
	}
}
