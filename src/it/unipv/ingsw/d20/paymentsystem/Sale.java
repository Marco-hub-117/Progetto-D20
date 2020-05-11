package it.unipv.ingsw.d20.paymentsystem;

import java.util.Date;

import it.unipv.ingsw.d20.beverage.Beverage;
import it.unipv.ingsw.d20.beverage.BeverageDescription;

/**
 * 
 * @author 
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class Sale {
	
	private Date date;
	private BeverageDescription beverageDescription;
	private double amount;
	private boolean isComplete = false;
	
	public Sale(BeverageDescription beverageDescription, double amount) {
		this.beverageDescription = beverageDescription;
		this.amount = amount;
		date = new Date();
		
		Payment payment = new Payment(amount, beverageDescription.getPrice());
		if (payment.isAccepted()) {
			Beverage beverage = new Beverage(beverageDescription);
			if (beverage.hasBeenDelivered()) {
				isComplete = true;
			}
		}
	}

	public boolean isComplete() {
		return isComplete;
	}

}
