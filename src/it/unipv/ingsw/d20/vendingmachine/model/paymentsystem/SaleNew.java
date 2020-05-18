package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.Beverage;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.IPaymentStrategy;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class SaleNew {
	
	private Date date;
	private BeverageDescription beverageDescription;
	private double price;
	private IPaymentStrategy strategy;
	
	private double change;
	
	public SaleNew(BeverageDescription beverageDescription, Object creditInfo, IPaymentStrategy strategy) throws InvalidPaymentException, InsufficientCreditException, DeliveryFailedException {
		this.beverageDescription = beverageDescription;
		price = beverageDescription.getPrice();
		date = new Date();
		
		change=strategy.elaboratePayment(price, creditInfo);
		
		Beverage beverage = new Beverage(beverageDescription); //checks whether the beverage was correctly delivered or not (InsufficientIngredientsException)	
	}
	
	@Override
	public String toString() { //to modify in case other payment method are implemented
		StringBuilder saleInfo = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		saleInfo.append("Date: " + sdf.format(date) + "\n");
		
		saleInfo.append("Product: " + beverageDescription.getCode() + "\n");
		
		DecimalFormat df = new DecimalFormat("0.00");
		saleInfo.append("Payment method: �"+ strategy.toString() + "\n");
		saleInfo.append("Total: �" + df.format(price) + "\n");
		saleInfo.append("Change: �" + df.format(change) + "\n");
		
		return saleInfo.toString();
	}

}
