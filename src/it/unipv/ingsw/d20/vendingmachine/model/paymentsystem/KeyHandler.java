package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import java.util.ArrayList;
import java.util.Random;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.paymentKey.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyPOJO;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;
/**
 * La classe gestisce l'uso delle chiavette
 *
 */
public class KeyHandler {
	
	private boolean keyInserted;
	private double creditOnKey;
	/**
	 * Costruttore della classe KeyHandler
	 *
	 */
	public KeyHandler() {
		keyInserted = false;
		creditOnKey=0.0;
	}
	/**
	 * Il metodo gestisce l'inserimento di una chiavetta
	 * @param credit credito già presente prima dell'inserimento della chiavetta
	 * @throws UnrecognisedKeyException
	 */
	public void insertKey(double credit) throws UnrecognisedKeyException {
		/*if (Math.random() < 0.20) { //la chiavetta non e' valida nel 20% dei casi
			throw new UnrecognisedKeyException();
		} else {
			keyInserted = true;
			
			Random rand = new Random();
			creditOnKey = credit + rand.nextInt(10) * 1.0 + rand.nextInt(10) * 0.5 + rand.nextInt(10) * 0.2 + 
					rand.nextInt(10) * 0.1 + rand.nextInt(10) * 0.05;			
		}*/
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		IKeyDao kDao = pf.getKeyDao();
		ArrayList<KeyPOJO> keyList = kDao.getAllKeys();
		int maxIndex = (int) 2 * keyList.size();
		
		Random rand = new Random();
		int index;
		if ((index = rand.nextInt(maxIndex)) >= keyList.size())
			throw new UnrecognisedKeyException();
		else {
			KeyPOJO key = keyList.get(index);
			creditOnKey = key.getCredit();
		}
			
		
	}
	
	/**
	 * Il metodo permette di espellere la chiavetta
	 * 
	 */
	public void ejectKey() {
		keyInserted = false; //reinizializza le variabili di classe
		creditOnKey = 0; 
		
	}

	public double getCreditOnKey() {
		return creditOnKey;
	}
	
	public boolean keyIsInserted() {
		return keyInserted;
	}

}
