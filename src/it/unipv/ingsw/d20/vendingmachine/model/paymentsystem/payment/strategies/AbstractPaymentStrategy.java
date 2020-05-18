package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.IPaymentStrategy;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public abstract class AbstractPaymentStrategy implements IPaymentStrategy {
	
	public double quickCheck(double amount, double price) throws InsufficientCreditException {
		if (amount >= price) {
			return amount-price;
		} else { 
			throw new InsufficientCreditException();
		}		
	}
	
}
