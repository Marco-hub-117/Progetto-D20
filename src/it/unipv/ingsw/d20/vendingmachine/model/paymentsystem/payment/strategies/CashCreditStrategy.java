package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.CashHandler;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class CashCreditStrategy extends AbstractCreditStrategy {
	
	public String serialize(Object creditInfo) throws InvalidPaymentException {
		double value;
		
		try {
			value=(double)creditInfo;
		} catch (ClassCastException e) {
			throw new InvalidPaymentException();
		}
		
		String serial="";
		if (Math.random()<0.1) { //simula una probabilità del 10% di inserire una moneta non valida
			serial="INVALID";
		}
		else {
			serial=CoinSerializer.associateSerialString(value);
		}	
		return serial;
	}
	
	public boolean checkValidity(String serial) {
		if (serial.equals("INVALID")) { 
			return false;
		}
		else {
			return true;
		}
	}
	
	public double getAmount(String serial) {
		double coin=CoinSerializer.convertToDouble(serial);
		//CashContainer.addCoin(coin); //magari rendere statico addCoin, in modo che non debba conoscere per forze l'istanza, 
		//tanto è 1 per tutto il programma (eventualmente Sinlgeton?).
		//addCoin non dovrebbe lanciare InvalidPayment perchè a questo punto ho già verificato che la moneta sia valida
		
		return coin;
	}
	
	@Override
	public String toString() {
		return ("Cash");
	}

}
