package it.unipv.ingsw.d20.company;

import it.unipv.ingsw.d20.company.webapp.WebAppLauncher;

/**
 * Classe che si occupa di fare partire la Company
 * e la webapp.
 *
 */
public class CompanyLauncher {

	public static void main(String[] args) {
		new Company("D20 Project");
		
		try {
			WebAppLauncher.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
