package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class CashPaymentStrategy extends AbstractPaymentStrategy {
	
	public double elaboratePayment(double amount, double price) throws InsufficientCreditException, InvalidPaymentException {
		
		//controllo di validità: se monete non riconosciute solleva InvalidPaymentException: 
		//Vending non farà partire la Sale e restituirà le monete.
		
		double change=quickCheck(amount, price);
		
		//logica per gestire quali monete usare per il resto
		
		return change;
	}
	
}
