package it.unipv.ingsw.d20.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import it.unipv.ingsw.d20.company.constants.Cities;
import it.unipv.ingsw.d20.company.constants.Streets;
import it.unipv.ingsw.d20.company.net.CompanyServer;
import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

/**
 * Questa classe si occupa della gestione delle macchinette nel loro insieme: ci riesce
 * tramite un server che accetta connessioni TCP dalle macchinette e tramite una webapp
 * utilizzata dagli operatori da remoto.
 *
 */
public class Company {
	
	@SuppressWarnings("unused")
	private String name;
	
	/**
	 * Questa mappa contiene la lista di tutte le macchinette attualmente registrate
	 * insieme con le loro informazioni temporanee.
	 */
	public static Map<String, VendingMachineInfo> vendingMachineInfoList;
	
	
	/**
	 * Il costruttore si occupa di assegnare il nome alla compagnia, inizializzare la lista 
	 * delle macchinette rivolgendosi al database e "accendere" il server. Inoltre fa partire
	 * un timer che si occupa di controllare lo status delle macchinette nella lista.
	 * @param name nome della compagnia
	 */
	public Company(String name) {
		this.name = name;

		vendingMachineInfoList = new HashMap<>();
		
		int port = 8888;
        CompanyServer server = new CompanyServer(port);
        server.start();
        
        PersistenceFacade pf = PersistenceFacade.getInstance();
        IVendingDao v = pf.getVendingDao();
        ArrayList<VendingPOJO> vendingList = v.getAllVendings();
        
        for (VendingPOJO vp : vendingList) { //riempie la mappa con tutte le vending machine già registrate nel database
        	vendingMachineInfoList.put(vp.getId(), new VendingMachineInfo());
        }
        
        Timer timer = new Timer();																   
		timer.schedule(new RefreshVendingListStatus(), new Date(), TimeUnit.MINUTES.toMillis(2)); //ogni 2 minuti aggiorna lo status di tutte le vending machine
	}
	
	/*public VendingMachine selectVendingMachine(String id) {
		return null;
		
	}
	
	public void addOperator(String id) throws AddingOperatorException {
		//da usare per il database
	}
	
	public void addRemoteOperator (String id) throws AddingOperatorException {
		//da usare per il database
	}*/
	
	/**
	 * Associa un nuovo ID ad una macchinetta che è stata accesa per la prima
	 * volta e la registra nel database.
	 * @return ID della nuova macchinetta
	 */
	public static String registerNewVendingMachine(String type) {
		String IDNumber = generateNewID();
		String location = generateNewLocation();
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		IVendingDao ivd = pf.getVendingDao();
		ivd.addVending(new VendingPOJO(IDNumber, location, type));
		
		vendingMachineInfoList.put(IDNumber, new VendingMachineInfo());
		
		return IDNumber;
	}
	
	/**
	 * Genera un ID univoco per una macchinetta che è stata accesa per la prima volta.
	 * @return ID generato
	 */
	private static String generateNewID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String vendingIDNumber = sdf.format(new Date());
		
		String fileName = "IDN" + vendingIDNumber;
		return fileName; 
	}
	
	/**
	 * Genera la posizone per una macchinetta che è stata accesa per la prima volta.
	 * @return stringa con la posizione generata
	 */
	private static String generateNewLocation() {
		int i = (int) (Math.random() * 10);
		int j = (int) (Math.random() * 10);
		String city = Cities.values()[i].toString();
		String street = Streets.values()[j].toString();
		int number = (int) (Math.random() * 100);
	
		String location = city + ", Via " + street + " " + number ;
		return location; 
	}
	
}
