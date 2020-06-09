package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidPaymentException;

/**
 * Javadoc test for CashContainer class.
 *
 */
public class CashContainer {
	
	private int CENTS_5;
	private int CENTS_10;
	private int CENTS_20;
	private int CENTS_50;
	private int EUROS_1;
	private int EUROS_2;
	
	private double totalAmount;
	
	public CashContainer(Integer[] cashQuantity) {
		CENTS_5 = cashQuantity[5];
		CENTS_10 = cashQuantity[4];
		CENTS_20 = cashQuantity[3];
		CENTS_50 = cashQuantity[2];
		EUROS_1 = cashQuantity[1];
		EUROS_2 = cashQuantity[0];

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
		int creditX100 = (int) credit * 100; //opero sul credito moltiplicato per 100 a causa dell'approssimazione della virgola mobile

		if(creditX100 > (int) (totalAmount * 100)) {
			throw new InsufficientCashForRestException();
		}
		
		while (creditX100 >= 200) {
			if (EUROS_2 == 0) {
				break;
			}
			EUROS_2--;
			creditX100 -= 200;
		}
		
		while (creditX100 >= 100) {
			if (EUROS_1 == 0) {
				break;
			}
			EUROS_1--;
			creditX100 -= 100;
		}
		
		while (creditX100 >= 50) {
			if (CENTS_50 == 0) {
				break;
			}
			CENTS_50--;
			creditX100 -= 50;
		}
		
		while (creditX100 >= 20) {
			if (CENTS_20 == 0) {
				break;
			}
			CENTS_20--;
			creditX100 -= 20;
		}
		
		while (creditX100 >= 10) {
			if (CENTS_10 == 0) {
				break;
			}
			CENTS_10--;
			creditX100 -= 10;
		}
		
		while (creditX100 >= 5) {
			if (CENTS_5 == 0) {
				break;
			}
			CENTS_5--;
			creditX100 -= 5;
		}
		
		if (creditX100 != 0) {
			throw new InsufficientCashForRestException();
		}
		
		System.out.println("Dispensed €" + credit);
		
		refreshTotalAmount();
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

}









