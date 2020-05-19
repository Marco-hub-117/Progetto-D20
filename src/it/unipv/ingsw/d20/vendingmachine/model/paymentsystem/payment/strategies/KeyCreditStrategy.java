package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class KeyCreditStrategy extends AbstractCreditStrategy {
	
	public double elaborateCredit(Object creditInfo) throws InvalidPaymentException {

		String serial=serialize(creditInfo);
		
		double amount=0;
		if (checkValidity(serial)) {
			amount=getAmount(serial);
		}
		
		return amount;
	}
	
	public String serialize(Object creditInfo) throws InvalidPaymentException {
		String id;
		try {
			id=(String)creditInfo;
		} catch (ClassCastException e) {
			throw new InvalidPaymentException();
		}
		return id;
	}
	
	public boolean checkValidity(String serial) {
		
		//va a controllare che il seriale della chiavetta sia presente sul DB, se tutto ok ritorna true
		
		return true;
	}
	
	public double getAmount(String serial) {
		
		//va a prendere l'ammontare dal DB
		double amount=0;
		
		return amount;
	}
	
	public double completeSale(double change) {
		
		//va a scrivere l'ammontare rimasto sul DB
		
		return change; 
	}
	
	@Override
	public String toString() {
		return ("Key");
	}

}
