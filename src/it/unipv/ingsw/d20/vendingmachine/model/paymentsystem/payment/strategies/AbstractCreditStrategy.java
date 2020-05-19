package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.ICreditStrategy;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public abstract class AbstractCreditStrategy implements ICreditStrategy {
	
	public double checkCredit(double amount, double price) throws InsufficientCreditException {
		if (amount >= price) {
			return amount-price;
		} else { 
			throw new InsufficientCreditException();
		}		
	}
	
}
