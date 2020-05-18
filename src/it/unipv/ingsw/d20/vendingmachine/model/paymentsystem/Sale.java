package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.Beverage;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.CashPayment;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.IPaymentStrategy;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

@SuppressWarnings("unused")
public class Sale {
	
	private Date date;
	private BeverageDescription beverageDescription;
	private double amount;
	private double price;
	
	private double change;
	
	public Sale(BeverageDescription beverageDescription, double amount) throws InvalidPaymentException, DeliveryFailedException {
		this.beverageDescription = beverageDescription;
		this.amount = amount;
		date = new Date();
		price = beverageDescription.getPrice();
		
		CashPayment payment = new CashPayment(this.amount, price); //checks whether the payment was successful or not (InvalidPaymentException)
		change = payment.getChange();
		
		//elaboratePayment();
			
		Beverage beverage = new Beverage(beverageDescription); //checks whether the beverage was correctly delivered or not (InsufficientIngredientsException)	
	}
	
	@Override
	public String toString() { //to modify in case other payment method are implemented
		StringBuilder saleInfo = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		saleInfo.append("Date: " + sdf.format(date) + "\n");
		
		saleInfo.append("Product: " + beverageDescription.getCode() + "\n");
		
		DecimalFormat df = new DecimalFormat("0.00");
		saleInfo.append("Total: �" + df.format(price) + "\n");
		saleInfo.append("Cash: �" + df.format(amount) + "\n");
		saleInfo.append("Change: �" + df.format(change));
		
		return saleInfo.toString();
	}
	
	/*
	void elaboratePayment(IPaymentStrategy strategy){
		strategy.elaborate();
	}*/
	
}
