package it.unipv.ingsw.d20.vendingmachine.model.exceptions;
/**
 * L'eccezione viene sollevata quando un operatore remoto tenta di modificare le proprietà di un tank non esistente
 * */
@SuppressWarnings("serial")
public class TankAbsentException extends Exception{
	
	public TankAbsentException(String message) {
		super(message);
	}
	
	public void printMessage() {
		System.out.println(super.getMessage());

	}

}
