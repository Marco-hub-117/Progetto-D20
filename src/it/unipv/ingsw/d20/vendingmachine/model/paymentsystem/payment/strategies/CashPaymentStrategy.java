package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

public class CashStrategy extends AbstractPaymentStrategy{
	
	double elaboratePayment(double amount, double price) throws InvalidPaymentException {
		
		double change=quickCheck(amount, price);
		
		//logica per gestire quali monete usare per il resto
		
		return change;
	
	}
	
}
