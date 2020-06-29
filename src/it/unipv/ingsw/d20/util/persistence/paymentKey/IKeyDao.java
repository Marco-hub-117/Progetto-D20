package it.unipv.ingsw.d20.util.persistence.paymentKey;

import java.util.ArrayList;


public interface IKeyDao {
	
	/**
	 * Ottiene tutte le chiavette salvate.
	 * @return keyList POJO di tutte le chiavette
	 */
	public ArrayList<KeyPOJO> getAllKeys();
	
	/**
	 * Ottiene una specifica chiave, con il codice seriale specificato come parametro.
	 * @param serialCode id della chiavetta
	 * @return key POJO della chiavetta
	 */
	public KeyPOJO getKey(String serialCode);
	
	/**
	 * Salva una nuova chiavetta.
	 * @param serialCode Codice della chiavetta
	 * @param credit credito inziale
	 */
	public void addKey(int serialCode, double credit);
	
	/**
	 * Aggiorna il credito di una chiavetta.
	 * @param serialCode Codice della chiavetta
	 * @param credit nuovo credito
	 * @return boolean esito dell'operazione
	 */
	public boolean updateCredit(String serialCode, double credit);

	/**
	 * Ottiene il credito di una specifica chiavetta.
	 * @param serialCode Codice della chiavetta
	 * @return credit credito della chiavetta
	 */
	public double getCredit(String serialCode);
	
	/**
	 * Disattiva una chiavetta.
	 * @param serialCode Codice della chiavetta
	 */
	public void deactivateKey(String serialCode);

}
