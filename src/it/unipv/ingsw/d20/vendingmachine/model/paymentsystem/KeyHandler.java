package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import java.util.Random;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.KeyNotInsertedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;

public class KeyHandler {
	
	private boolean keyInserted;
	private double creditOnKey;
	
	public KeyHandler() {
		keyInserted = false;
		creditOnKey = 0;
	}

	public void insertKey() throws UnrecognisedKeyException {
		if (Math.random() < 0.20) { //la chiavetta non è valida nel 20% dei casi
			throw new UnrecognisedKeyException();
		} else {
			keyInserted = true;
			
			Random rand = new Random();
			creditOnKey = rand.nextInt(10) * 1.0 + rand.nextInt(10) * 0.5 + rand.nextInt(10) * 0.2 + 
					rand.nextInt(10) * 0.1 + rand.nextInt(10) * 0.05;			
		}
		
	}

	public void ejectKey() throws KeyNotInsertedException {
		if(keyInserted) {
			keyInserted = false; //reinizializza le variabili di classe
			creditOnKey = 0; 
		} else {
			throw new KeyNotInsertedException();
		}		
	}

	public double getCreditOnKey() {
		return creditOnKey;
	}
	
	public boolean keyIsInserted() {
		return keyInserted;
	}

}
