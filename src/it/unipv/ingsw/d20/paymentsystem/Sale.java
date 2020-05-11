package it.unipv.ingsw.d20.paymentsystem;

import java.util.Date;

import it.unipv.ingsw.d20.beverage.Beverage;
import it.unipv.ingsw.d20.beverage.BeverageDescription;
import it.unipv.ingsw.d20.paymentsystem.payment.Payment;
import it.unipv.ingsw.d20.paymentsystem.payment.exception.InvalidPaymentException;

/**
 * 
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
@SuppressWarnings("unused")
public class Sale {
	
	private Date date;
	private double amount;
	private double price;
	
	private double change;
	private boolean completed = false;
	
	public Sale(BeverageDescription beverageDescription, double amount) {
		this.amount = amount;
		date = new Date();
		price = beverageDescription.getPrice();
		
		try {
			Payment payment = new Payment(this.amount, price); //checks whether the payment was successful or not 
			change = payment.getChange();
			
			Beverage beverage = new Beverage(beverageDescription);
			if (beverage.hasBeenDelivered()) { //checks whether the beverage was correctly delivered or not
				completed = true;
			}
		} catch (InvalidPaymentException e) {
			e.printStackTrace();
		}
	}

	public boolean isComplete() {
		return completed;
	}

}
