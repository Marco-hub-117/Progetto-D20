package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;

/**
 * Javadoc test for CashContainer class.
 *
 */
public class CashContainer {
	
	private final double[] coinValue = {0.05, 0.10, 0.20, 0.50, 1.0, 2.0};
	private int[] coinNumber = new int[6];
	
	private double totalAmount;
	
	public CashContainer(int[] cashQuantity) {
		for (int i = 0; i < coinNumber.length; i++) {
			coinNumber[i] = cashQuantity[i];
		}

		refreshTotalAmount();
	}
	
	public void addCoin(double coin) {
		int index;
		for (index = 0; index < coinValue.length; index++) {
			if (coinValue[index] == coin) {
				break;
			}
		}
		
		coinNumber[index]++;
		
		refreshTotalAmount();
	}
	
	public void refreshTotalAmount() {
		totalAmount = 0;
		for (int i = 0; i < coinValue.length; i++) {
			totalAmount += coinValue[i] * coinNumber[i]; //moltiplica il valore della moneta per il numero di monete di quel tipo
		}
	}
	
	public void dispenseRest(double credit) throws InsufficientCashForRestException {	
		int creditX100 = (int) credit * 100; //opero sul credito moltiplicato per 100 a causa dell'approssimazione della virgola mobile

		if(creditX100 > (int) (totalAmount * 100)) {
			throw new InsufficientCashForRestException();
		}
		
		for (int i = coinValue.length - 1; i >= 0; i--) {
			while (creditX100 >= (int) (coinValue[i] * 100)) {
				if (coinNumber[i] == 0) {
					break;
				}
				coinNumber[i]--;
				creditX100 -= (int) (coinValue[i] * 100);
			}
		}
		
		if (creditX100 != 0) {
			throw new InsufficientCashForRestException();
		}
		
		System.out.println("Dispensed �" + credit);
		
		refreshTotalAmount();
	}
	
	public double withdrawAmount() {
		double total = 0;
		
		for (int i = 0; i < coinValue.length; i++) {
			int difference = coinNumber[i] - 10;
			if (difference > 0) {
				total += difference * coinValue[i];
			}
		}
		
		refreshTotalAmount();
		
		return total;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public int[] getCoinNumber() {
		return coinNumber;
	}

}









