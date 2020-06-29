package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import java.util.ArrayList;
import java.util.Random;

import it.unipv.ingsw.d20.util.persistence.PersistenceDAOFactory;
import it.unipv.ingsw.d20.util.persistence.paymentKey.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyPOJO;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;

/**
 * Classe che gestisce l'uso delle chiavette.
 *
 */
public class KeyHandler {
	
	private boolean keyInserted;
	private int keySerialCode;
	private double creditOnKey;
	
	/**
	 * Costruttore della classe KeyHandler
	 *
	 */
	public KeyHandler() {
		keyInserted = false;
		creditOnKey = 0.0;
	}
	
	/**
	 * Il metodo gestisce l'inserimento di una chiavetta.
	 * @param credit credito gi√† presente prima dell'inserimento della chiavetta
	 * @throws UnrecognisedKeyException eccezione che viene lanciata se la chiavetta non Ë riconosciuta
	 */
	public void insertKey(double credit) throws UnrecognisedKeyException {
		PersistenceDAOFactory pf = PersistenceDAOFactory.getInstance();
		IKeyDao kDao = pf.getKeyDao();
		ArrayList<KeyPOJO> keyList = kDao.getAllKeys();
		int maxIndex = (int) 2 * keyList.size();
		
		Random rand = new Random();
		int index;
		if ((index = rand.nextInt(maxIndex)) >= keyList.size())
			throw new UnrecognisedKeyException();
		else {
			KeyPOJO key = keyList.get(index);
			keySerialCode = key.getSerialCode();
			creditOnKey = key.getCredit() + credit;
		}
	}
	
	/**
	 * Aggiorna il credito residuo della chiavetta sul database
	 * e la espelle dalla macchinetta.
	 * @param residualCredit credito residuo sulla chiavetta
	 * 
	 */
	public void ejectKey(double residualCredit) {
		PersistenceDAOFactory pf = PersistenceDAOFactory.getInstance();
		IKeyDao kDao = pf.getKeyDao();
		kDao.updateCredit(String.valueOf(keySerialCode), residualCredit); //aggiorna il credito sul DB
		
		//reinizializza le variabili di classe
		keySerialCode = 0;
		creditOnKey = 0.0; 
		keyInserted = false; 
	}

	public double getCreditOnKey() {
		return creditOnKey;
	}
	
	public boolean keyIsInserted() {
		return keyInserted;
	}

}
