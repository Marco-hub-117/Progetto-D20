package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

public class AbstractPaymentStrategy {
	
	public double quickCheck(double amount, double price) throws InvalidPaymentException {
		
		if (amount >= price) {
			return amount-price;
		} else { 
			throw new InvalidPaymentException();
		}			
		
	}
	
}
