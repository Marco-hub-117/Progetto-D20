package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidPaymentException;

public class CashHandler {
	
	private int CENTS_5;
	private int CENTS_10;
	private int CENTS_20;
	private int CENTS_50;
	private int EUROS_1;
	private int EUROS_2;
	
	private double totalAmount;
	
	public CashHandler(int cents5, int cents10, int cents20, int cents50, int euros1, int euros2) {
		CENTS_5 = cents5;
		CENTS_10 = cents10;
		CENTS_20 = cents20;
		CENTS_50 = cents50;
		EUROS_1 = euros1;
		EUROS_2 = euros2;
		
		totalAmount = 0;
	}
	
	public void addCoin(double coin) throws InvalidPaymentException {
		if (Math.random() < 0.05) {
			throw new InvalidPaymentException(); //5% chance that the coin is not valid
		}
		
		String coinString = String.valueOf(coin);
		
		switch (coinString) {
		case "0.05":
			CENTS_5++;
			break;
		case "0.1":
			CENTS_10++;
			break;
		case "0.2":
			CENTS_20++;
			break;
		case "0.5":
			CENTS_50++;
			break;
		case "1.0":
			EUROS_1++;
			break;
		case "2.0":
			EUROS_2++;
			break;
		default:
			throw new InvalidPaymentException();
		}
	}
	
	public double refreshTotalAmount() {
		double amount = 0.05 * CENTS_5 + 0.1 * CENTS_10 + 0.2 * CENTS_20 + 0.5 * CENTS_50 + EUROS_1 + 2 * EUROS_2;
		return amount;
	}
	
	public void dispenseRest(double credit) throws InsufficientCashForRestException {	
		if(credit > totalAmount) {
			throw new InsufficientCashForRestException();
		}
		
		while (credit >= 2) {
			if (EUROS_2 == 0) {
				break;
			}
			EUROS_2--;
			credit -= 2;
		}
		
		while (credit >= 1) {
			if (EUROS_1 == 0) {
				break;
			}
			EUROS_1--;
			credit -= 1;
		}
		
		while (credit >= 0.5) {
			if (CENTS_50 == 0) {
				break;
			}
			CENTS_50--;
			credit -= 0.5;
		}
		
		while (credit >= 0.2) {
			if (CENTS_20 == 0) {
				break;
			}
			CENTS_20--;
			credit -= 0.2;
		}
		
		while (credit >= 0.1) {
			if (CENTS_10 == 0) {
				break;
			}
			CENTS_10--;
			credit -= 0.1;
		}
		
		while (credit >= 0.05) {
			if (CENTS_5 == 0) {
				break;
			}
			CENTS_5--;
			credit -= 0.05;
		}
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

}









