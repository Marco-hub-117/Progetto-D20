package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class AppPaymentStrategy extends AbstractPaymentStrategy {
	
	public double elaboratePayment(double amount, double price) throws InsufficientCreditException, InvalidPaymentException {
		
		//controllo di validità: se accesso con app non riconosciuto solleva InvalidPaymentException
		
		//controllo sul credito
		
		return 0;
	}
	
}
