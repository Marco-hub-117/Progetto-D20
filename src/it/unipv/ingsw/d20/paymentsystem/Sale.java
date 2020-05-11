package it.unipv.ingsw.d20.paymentsystem;

import java.util.Date;

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
	private double price;
	private boolean isComplete;
	
	public Sale(BeverageDescription beverageDescription, double price) {
		this.beverageDescription = beverageDescription;
		this.price = price;
		date = new Date();
		isComplete = false;
	}

	public double getPrice() {
		return price;
	}

	public boolean isComplete() {
		return isComplete;
	}

}
