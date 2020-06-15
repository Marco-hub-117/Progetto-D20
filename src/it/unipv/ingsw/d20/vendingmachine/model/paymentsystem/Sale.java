package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.*;

public class Sale {
	
	private Date date;
	private BeverageDescription beverageDescription;
	private double price;
	private double rest;
	
	public Sale(BeverageDescription beverageDescription, double credit) throws InsufficientCreditException {
		this.beverageDescription = beverageDescription;
		date = new Date();
		price = beverageDescription.getPrice();
		
		rest = checkCredit(credit, price);
	}
	
	public double checkCredit(double credit, double price) throws InsufficientCreditException {
		if (credit >= price) {
			return credit - price;
		} else { 
			throw new InsufficientCreditException("Il credito inserito non è sufficiente");
		}		
	}

	@Override
	public String toString() { 
		StringBuilder saleInfo = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		saleInfo.append("Date: " + sdf.format(date) + "\n");
		
		saleInfo.append("Product: " + beverageDescription.getCode() + "\n");
		
		DecimalFormat df = new DecimalFormat("0.00");
		saleInfo.append("Total: �" + df.format(price) + "\n");
		
		return saleInfo.toString();
	}
	
	public double getRest() {
		return rest;
	}
	
}
