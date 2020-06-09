package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidPaymentException;

/**
 * Javadoc test for CashContainer class.
 *
 */
public class CashContainer {
	
	private final double[] coinValue = {0.05, 0.10, 0.20, 0.50, 1.0, 2.0};
	private int[] coinNumber = new int[6];
	
	private double totalAmount;
	
	public CashContainer(Integer[] cashQuantity) {
		for (int i = 0; i < coinNumber.length; i++) {
			coinNumber[i] = cashQuantity[i];
		}

		refreshTotalAmount();
	}
	
	public void addCoin(double coin) {
		//l'inserimento di una moneta nel nostro caso non può essere falso perché avviene tramite pulsanti
				
		String coinString = String.valueOf(coin);
		/* ha poco senso perché a livello grafico viene uno schifo
		if (Math.random() < 0.05) {
			throw new InvalidPaymentException(); //5% chance that the coin is not valid
		}
		*/
		int index;
		//boolean allowedValue = false;

		
		for (index = 0; index < coinValue.length; index++) {
			if (coinValue[index] == coin) {
				//allowedValue = true;
				break;
			}
		}
		
		//if (!allowedValue) {
			//throw new InvalidPaymentException();
		//}
		
		coinNumber[index]++;
		
		refreshTotalAmount();
	}
	
	public void refreshTotalAmount() {
		//aggiornare file locale per persistenza
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
			while (creditX100 >= (int) coinValue[i] * 100) {
				if (coinNumber[i] == 0) {
					break;
				}
				coinNumber[i]--;
				creditX100 -= (int) coinValue[i] * 100;
			}
		}
		
		if (creditX100 != 0) {
			throw new InsufficientCashForRestException();
		}
		
		System.out.println("Dispensed �" + credit);
		
		refreshTotalAmount();
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

}









