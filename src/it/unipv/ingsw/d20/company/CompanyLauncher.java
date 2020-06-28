package it.unipv.ingsw.d20.company;

import it.unipv.ingsw.d20.company.net.DBConnectionFailedException;
import it.unipv.ingsw.d20.company.webapp.WebAppLauncher;
import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;

/**
 * Classe che si occupa di fare partire la Company
 * e la webapp.
 *
 */
public class CompanyLauncher {
	
	public static void main(String[] args) throws DBConnectionFailedException {
		try {
			new Company("D20 Project");
		
		/*//controllo se la connessione al DB è attiva
		if (!PersistenceFacade.getInstance().testConnection()) {
			throw new DBConnectionFailedException();
		} //NON FUNZIONANTE*/
		
		
			WebAppLauncher.start();
		} catch (Exception e) {
			System.out.println("Connection error.");
			System.exit(-1);
		}
	}

}
