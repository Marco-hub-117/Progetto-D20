package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.net.MySQLHandler;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.KeyNotInsertedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;

public class KeyHandler {
	
	private boolean keyInserted;
	private String serialCode;
	private double creditOnKey;
	
	public KeyHandler() {
		keyInserted = false;
		serialCode = "";
		creditOnKey = 0;
	}

	public void insertKey(String serialCode) throws UnrecognisedKeyException {
		creditOnKey = MySQLHandler.getKeyCredit(serialCode); 
		
		if (creditOnKey < -1) { //se il metodo getKeyCredit restituisce -1 sgnifica che la chiavetta non è presente nel db
			throw new UnrecognisedKeyException();
		} else {
			this.serialCode = serialCode;
			keyInserted = true;
		}
		
	}

	public void ejectKey(double credit) throws KeyNotInsertedException {
		if(keyInserted) {
			MySQLHandler.setKeyCredit(serialCode, credit); //aggiorna il credito disponibile sulla chiavetta
		
			keyInserted = false; //reinizializza le variabili di classe
			serialCode = "";
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
