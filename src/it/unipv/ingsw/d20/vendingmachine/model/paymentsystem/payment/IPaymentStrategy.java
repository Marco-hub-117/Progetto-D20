package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public interface IPaymentStrategy {

	public double elaboratePayment(double amount, double price) throws InsufficientCreditException, InvalidPaymentException;
	
}
