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
import it.unipv.ingsw.d20.util.persistence.PersistenceFactory;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

/**
 * Questa classe si occupa della gestione dei distributori automatici nel loro insieme: la comunicazione avviene
 * tramite un server che accetta connessioni TCP dai distributori e tramite una webapp
 * utilizzata dagli operatori remoti.
 *
 */
public class Company {
	
	@SuppressWarnings("unused")
	private String name;
	
	private static final int PORT = 8888;
	
	private final static String ID_PREF = "IDN";
	private final static String LOC_PREF = ", Via ";
	
	/**
	 * Questa mappa contiene la lista di tutte le macchinette attualmente registrate
	 * insieme con le loro informazioni temporanee.
	 */
	public static Map<String, VendingMachineInfo> vendingMachineInfoList;
	
	
	/**
	 * Il costruttore si occupa di assegnare il nome alla compagnia, inizializzare la lista 
	 * dei distributori rivolgendosi al database e "accendere" il server. Inoltre fa partire
	 * un timer che si occupa di controllare lo status dei distributori nella lista.
	 * @param name nome della compagnia
	 */
	public Company(String name) {
		this.name = name;

		vendingMachineInfoList = new HashMap<>();
		
        CompanyServer server = new CompanyServer(PORT);
        server.start();
        
        PersistenceFactory pf = PersistenceFactory.getInstance();
        IVendingDao v = pf.getVendingDao();
        ArrayList<VendingPOJO> vendingList = v.getAllVendings();
        
        for (VendingPOJO vp : vendingList) { //riempie la mappa con tutte le vending machine già registrate nel database
        	vendingMachineInfoList.put(vp.getId(), new VendingMachineInfo());
        }
        
        Timer timer = new Timer();																   
		timer.schedule(new RefreshVendingListStatus(), new Date(), TimeUnit.SECONDS.toMillis(30)); //ogni 30 secondi aggiorna lo status di tutte le vending machine
	}
	
	/**
	 * Associa un nuovo ID ad un distributore che è stato acceso per la prima
	 * volta e lo registra nel database.
	 * @return ID del nuovo distributore
	 */
	public static String registerNewVendingMachine(String type) {
		String IDNumber = generateNewID();
		String location = generateNewLocation();
		
		PersistenceFactory pf = PersistenceFactory.getInstance();
		IVendingDao ivd = pf.getVendingDao();
		ivd.addVending(new VendingPOJO(IDNumber, location, type));
		
		vendingMachineInfoList.put(IDNumber, new VendingMachineInfo());
		
		return IDNumber;
	}
	
	/**
	 * Genera un ID univoco per un distributore che è stato acceso per la prima volta.
	 * @return ID generato
	 */
	private static String generateNewID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String vendingIDNumber = sdf.format(new Date());
		
		String fileName = ID_PREF + vendingIDNumber;
		return fileName; 
	}
	
	/**
	 * Genera la posizone per un distributore che è stato acceso per la prima volta.
	 * @return stringa con la posizione generata
	 */
	private static String generateNewLocation() {
		int i = (int) (Math.random() * 10);
		int j = (int) (Math.random() * 10);
		String city = Cities.values()[i].toString();
		String street = Streets.values()[j].toString();
		int number = (int) (Math.random() * 100);
	
		String location = city + LOC_PREF + street + " " + number ;
		return location; 
	}
	
	/**
	 * Restituisce il boolean che indica se il setpoint dei tank di una vending è stato modificato
	 * @param id id della vending
	 * @return true se il setpoint è stato modificato
	 */
	public static boolean isSetpointModified(String id) {
		return vendingMachineInfoList.get(id).isTempsSetPointModified();
	}
	
	/**
	 * Imposta il boolean che indica se il setpoint dei tank di una vending è stato modificato
	 * @param id id della vending
	 * @param bool boolean che indica se il setpoint dei tank di una vending è stato modificato
	 */
	public static void setSetpointModified(String id, boolean bool) {
		vendingMachineInfoList.get(id).setTempsSetPointModified(bool);
	}
	
	public static VendingMachineInfo getVendingInfo(String id) {
		return vendingMachineInfoList.get(id);
	}
	
}
