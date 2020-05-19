package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

public class CashContainer {
	
	private static int CENTS_10;
	private static int CENTS_20;
	private static int CENTS_50;
	private static int EUROS_1;
	private static int EUROS_2;
	
	public CashContainer(int cents10, int cents20, int cents50, int euros1, int euros2) {
		CENTS_10 = cents10;
		CENTS_20 = cents20;
		CENTS_50 = cents50;
		EUROS_1 = euros1;
		EUROS_2 = euros2;
	}
	
	public void addCoin(double coin) throws InvalidPaymentException {
		String coinString = String.valueOf(coin);
		
		switch (coinString) {
		case "0.1":
			CENTS_10++;
			break;
		case "0.2":
			CENTS_20++;
			break;
		case "0.5":
			CENTS_50++;
			break;
		case "1":
			EUROS_1++;
			break;
		case "2":
			EUROS_2++;
			break;
		default:
			throw new InvalidPaymentException();
		}
	}
	
	public double getTotalAmount() {
		double amount = 0.1 * CENTS_10 + 0.2 * CENTS_20 + 0.5 * CENTS_50 + EUROS_1 + 2 * EUROS_2;
		return amount;
	}
	
	public void dispenseRest(double credit) throws InsufficientCashForRestException {	
		if(credit > getTotalAmount()) {
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
	}

}









