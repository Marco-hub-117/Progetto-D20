package it.unipv.ingsw.d20.model.paymentsystem.payment;

import it.unipv.ingsw.d20.model.paymentsystem.payment.exceptions.InvalidPaymentException;

/**
 *
 * @author Luigi Zaccaria Del Pio
 *
 */
public class Payment {
	
	private double amount;
	private double price;
	private double change;
	
	public Payment(double amount, double price) throws InvalidPaymentException {
		this.amount = amount;
		this.price = price;
		
		if (checkPayment()) {
			setChange(amount, price);
		} else { 
			throw new InvalidPaymentException();
		}			
	}
	
	public boolean checkPayment() {
		if (amount >= price) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getChange() {
		return change;
	}

	public void setChange(double amount, double price) {
		this.change = amount - price;
	}	

}
