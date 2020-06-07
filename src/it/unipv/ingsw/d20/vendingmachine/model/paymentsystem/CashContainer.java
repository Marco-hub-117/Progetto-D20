package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidPaymentException;

public class CashContainer {
	
	private int CENTS_5;
	private int CENTS_10;
	private int CENTS_20;
	private int CENTS_50;
	private int EUROS_1;
	private int EUROS_2;
	
	private double totalAmount;
	
	public CashContainer(Integer[] cashQuantity) {
		CENTS_5 = cashQuantity[0];
		CENTS_10 = cashQuantity[1];
		CENTS_20 = cashQuantity[2];
		CENTS_50 = cashQuantity[3];
		EUROS_1 = cashQuantity[4];
		EUROS_2 = cashQuantity[5];

		refreshTotalAmount();
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
		
		refreshTotalAmount();
	}
	
	public void refreshTotalAmount() {
		//aggiornare file locale per persistenza
		totalAmount = 0.05 * CENTS_5 + 0.1 * CENTS_10 + 0.2 * CENTS_20 + 0.5 * CENTS_50 + EUROS_1 + 2 * EUROS_2;
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
		
		if (credit != 0) {
			throw new InsufficientCashForRestException();
		}
		
		refreshTotalAmount();
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

}









