package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;

/**
 * La classe gestisce il denaro presente nel distributore automatico
 *
 */
public class CashContainer {
	
	private final double[] coinValue = {0.05, 0.10, 0.20, 0.50, 1.0, 2.0};
	private int[] coinNumber = new int[6];
	
	private double totalAmount;
	/**
	 * Costruttore della classe CashContainer
	 * @param cashQuantity numero di monete per ogni valore presenti nel distributore
	 */
	public CashContainer(int[] cashQuantity) {
		for (int i = 0; i < coinNumber.length; i++) {
			coinNumber[i] = cashQuantity[i];
		}

		refreshTotalAmount();
	}
	/**
	 * Questo metodo aggiunge una moneta alla cassa del distributore
	 * @param coin valore della moneta
	 */
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
	/**
	 * Questo metodo aggiorna il totale del denaro presenta nel distributore
	 * 
	 */
	public void refreshTotalAmount() {
		totalAmount = 0;
		for (int i = 0; i < coinValue.length; i++) {
			totalAmount += coinValue[i] * coinNumber[i]; //moltiplica il valore della moneta per il numero di monete di quel tipo
		}
	}
	/**
	 * Questo metodo calcola il resto da erogare
	 * @param credit credito attualmente inserito 
	 * @throws InsufficientCashForRestException
	 */
	public void dispenseRest(double credit) throws InsufficientCashForRestException {	
		int creditX100 = (int) (credit * 100); //opero sul credito moltiplicato per 100 a causa dell'approssimazione della virgola mobile

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
	/**
	 * Questo metodo gestisce il ritiro delle monete da parte dell'operatore
	 *
	 */
	public double withdrawAmount() {
		double total = 0;
		
		for (int i = 0; i < coinValue.length; i++) {
			int difference = coinNumber[i] - 10; 
			if (difference > 0) { //fa in modo di lasciare sempre almeno 10 monete di ogni tipo
				total += difference * coinValue[i];
				coinNumber[i] = 10;
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