package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.*;

public class Sale {
	
	String vmId;
	private Date date;
	private BeverageDescription beverageDescription;
	private double price;
	private double rest;
	
	public Sale(String vmId, BeverageDescription beverageDescription, double credit) throws InsufficientCreditException {
		this.vmId = vmId;
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
		
		saleInfo.append(vmId + "	");
		
		saleInfo.append(beverageDescription.getName() + "	");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		saleInfo.append(sdf.format(date) + "	");
		
		//saleInfo.append(beverageDescription.getName() + "	");
		
		//DecimalFormat df = new DecimalFormat("0.00");
		//saleInfo.append(df.format(price) + "	");
		
		return saleInfo.toString();
	}
	
	public double getRest() {
		return rest;
	}
	
}
