package it.unipv.ingsw.d20.company;

import it.unipv.ingsw.d20.company.webapp.WebAppLauncher;

/**
 * Classe che si occupa di fare partire la Company
 * e la webapp.
 *
 */
public class CompanyLauncher {
	
	public static void main(String[] args) {
		try {
			new Company("D20 Project");

			WebAppLauncher.start();
		} catch (Exception e) {
			System.out.println("Connection error.");
			System.exit(-1);
		}
	}

}
