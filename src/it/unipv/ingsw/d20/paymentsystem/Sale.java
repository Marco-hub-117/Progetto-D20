package it.unipv.ingsw.d20.paymentsystem;

import java.util.Date;

import it.unipv.ingsw.d20.beverage.Beverage;
import it.unipv.ingsw.d20.beverage.BeverageDescription;
import it.unipv.ingsw.d20.beverage.exception.InsufficientIngredientsException;
import it.unipv.ingsw.d20.paymentsystem.payment.Payment;
import it.unipv.ingsw.d20.paymentsystem.payment.exception.InvalidPaymentException;

/**
 * 
 * @author Nicolò Fasulo 
 *
 */
@SuppressWarnings("unused")
public class Sale {
	
	private Date date;
	private double amount;
	private double price;
	
	private double change;
	private boolean completed;
	
	public Sale(BeverageDescription beverageDescription, double amount) {
		this.amount = amount;
		date = new Date();
		price = beverageDescription.getPrice();
		
		try {
			Payment payment = new Payment(this.amount, price); //checks whether the payment was successful or not (InvalidPaymentException)
			change = payment.getChange();
			
			Beverage beverage = new Beverage(beverageDescription); //checks whether the beverage was correctly delivered or not (InsufficientIngredientsException)
			
			completed = true;			
		} catch (InvalidPaymentException | InsufficientIngredientsException e) {
			e.printStackTrace();
		}
	}

	public boolean isComplete() {
		return completed;
	}

}
