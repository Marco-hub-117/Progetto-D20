package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions;

/**
 * L'eccezione viene lanciata quando si tenta di rimuovere la chiavetta senza che sia stata inserita
 */
@SuppressWarnings("serial")
public class KeyNotInsertedException extends Exception {
	
	public KeyNotInsertedException() {
		super("Nessuna chiavetta inserita");
	}

}