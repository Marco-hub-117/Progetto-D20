package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public interface IPaymentStrategy {
	
	public String serialize(Object creditInfo) throws InvalidPaymentException;

	public boolean checkValidity(String serial);
	
	public double getAmount(String serial);
	
	public double checkCredit(double amount, double price) throws InsufficientCreditException;
	
	public void elaboratePayment(double price, Object creditInfo) throws InsufficientCreditException, InvalidPaymentException;

}
