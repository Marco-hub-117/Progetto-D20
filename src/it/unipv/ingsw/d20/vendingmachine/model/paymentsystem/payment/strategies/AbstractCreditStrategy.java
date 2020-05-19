package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.ICreditStrategy;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public abstract class AbstractCreditStrategy implements ICreditStrategy {
	
	public double elaborateCredit(Object creditInfo) throws InvalidPaymentException {

		String serial=serialize(creditInfo);
		
		double amount=0;
		if (checkValidity(serial)) {
			amount=getAmount(serial);
		} else {
			throw new InvalidPaymentException();
		}

		return amount;
	}
	
}
