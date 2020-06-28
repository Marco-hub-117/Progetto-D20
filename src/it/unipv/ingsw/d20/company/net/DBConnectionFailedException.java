package it.unipv.ingsw.d20.company.net;

@SuppressWarnings("serial")
public class DBConnectionFailedException extends Exception{
	
	public DBConnectionFailedException() {
		super("Si è verificato un problema di connesione con il database. Controllare che il database Server sia attivo");
	}
}
