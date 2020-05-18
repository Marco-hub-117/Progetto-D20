package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class CashPaymentStrategy extends AbstractPaymentStrategy {
	String serial;
	
	public void elaboratePayment(double price, Object creditInfo) throws InsufficientCreditException, InvalidPaymentException {

		serial=serialize(creditInfo);
		
		double amount=0;
		if (checkValidity(serial)) {
			amount=getAmount(serial);
		}
		else {
			throw new InvalidPaymentException();
		}
				
		double change=checkCredit(amount, price);
	}
	
	public String serialize(Object creditInfo) throws InvalidPaymentException {
		
		Map<Double, Integer> coins=new HashMap<>();
		try {
			coins=(Map)creditInfo;
		} catch (ClassCastException e) {
			throw new InvalidPaymentException();
		}
		
		StringBuilder serial=new StringBuilder();
		for (Map.Entry<Double, Integer> coin: coins.entrySet()) {
			for (int i=0; i<coin.getValue(); i++) {
				char nextCh;
				if (Math.random()<0.1) { //simula una probabilità del 10% di inserire una moneta non valida
					nextCh='z';
				}
				else {
					nextCh=CoinSerial.associateSerialCharacter(coin.getKey());
				}	
				serial.append(nextCh);
			}
		}
	
		return serial.toString();
	}
	
	public boolean checkValidity(String serial) {
		
		if (serial.contains("z")) {
			serial.replace("z", ""); //comunque tengo buone quelle valide
		}
		if (serial.equals("")) { //erano tutte non valide
			return false;
		}
		else {
			return true;
		}
	}
	
	public double getAmount(String serial) {
		double amount=0;
		for (int i=0; i<serial.length(); i++) {
			amount+=CoinSerial.convertToDouble(serial.charAt(i));
		}
		
		return amount;
	}
	
	@Override
	public String toString() {
		return ("Cash");
	}

}
