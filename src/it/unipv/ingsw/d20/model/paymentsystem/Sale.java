package it.unipv.ingsw.d20.model.paymentsystem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unipv.ingsw.d20.model.beverage.Beverage;
import it.unipv.ingsw.d20.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.model.beverage.exception.InsufficientIngredientsException;
import it.unipv.ingsw.d20.model.paymentsystem.payment.Payment;
import it.unipv.ingsw.d20.model.paymentsystem.payment.exception.InvalidPaymentException;

/**
 * 
 * @author Nicol√≤ Fasulo 
 *
 */
@SuppressWarnings("unused")
public class Sale {
	
	private Date date;
	private BeverageDescription beverageDescription;
	private double amount;
	private double price;
	
	private double change;
	private boolean completed;
	
	public Sale(BeverageDescription beverageDescription, double amount) {
		this.beverageDescription = beverageDescription;
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
	
	@Override
	public String toString() { //to modify in case other payment method are implemented
		String saleInfo = "";
		
		if (!completed) { //returns an empty string in case the sale wasn't successful
			return saleInfo;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		saleInfo = "Date: " + sdf.format(date) + "\n";
		
		saleInfo += "Product: " + beverageDescription.getCode() + "\n";
		
		DecimalFormat df = new DecimalFormat("0.00");
		saleInfo += "Total: Ä" + df.format(price) + "\n";
		saleInfo += "Cash: Ä" + df.format(amount) + "\n";
		saleInfo += "Change: Ä" + df.format(change);
		
		return saleInfo;
	}

}
