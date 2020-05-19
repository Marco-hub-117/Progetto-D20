package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public interface ICreditStrategy {
	
	public String serialize(Object creditInfo) throws InvalidPaymentException;

	public boolean checkValidity(String serial);
	
	public double getAmount(String serial);
	
	public double completeSale(double change); 
	
	public double elaborateCredit(Object creditInfo) throws InvalidPaymentException;

}
