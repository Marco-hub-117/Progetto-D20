package it.unipv.ingsw.d20.company.webapp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unipv.ingsw.d20.company.Company;
import it.unipv.ingsw.d20.company.VendingMachineInfo;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidPasswordException;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidUserException;
import it.unipv.ingsw.d20.util.persistence.PersistenceFactory;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.IBeverageDescriptionDao;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IIngredientRecipeDao;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.util.persistence.operator.IOperatorDao;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorPOJO;
import it.unipv.ingsw.d20.util.persistence.paymentKey.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyPOJO;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;
import it.unipv.ingsw.d20.vendingmachine.model.tanks.Tank;

/**
 * Gestisce le richieste intercettate delle Servlet, costituendo per esse una facade verso il DB e la Company.
 *
 */
public class WebAppController {
	private PersistenceFactory sourceDAO;
	private IVendingDao vendingsManager;
	private IOperatorDao operatorsManager;
	private IKeyDao keysManager;
	private IBeverageDescriptionDao beveragesManager;
	private IIngredientRecipeDao ingredientsManager;
	private OperatorPOJO loggedOperator;
	
	/**
	 * E' true quando l'operatore loggato è un operatore e non un operatore remoto.
	 *
	 */
	private boolean operatorLimited;
	
	/**
	 * Stringa da stampare sulle pagine web nel caso manchi un'informazione.
	 *
	 */
	public static String absenceString="None";
	
	/**
	 * Registro dei report segnalati dagli operatori.
	 *
	 */
	private List<ReportPOJO> reportList;
	
	/**
	 * Tipi di operatori: la distinzione serve per separare il contenuto visualizzato nei due casi.
	 *
	 */
	private enum OperatorType{
		Operator, RemoteOperator;
	}
	
	/**
	 * Il costruttore inizializza gli attributi DAO per poter svolgere le operazioni su DB, oltre alla lista dei report.
	 */
	public WebAppController() {
		sourceDAO= PersistenceFactory.getInstance();
		vendingsManager=sourceDAO.getVendingDao();
		operatorsManager=sourceDAO.getOperatorDao();
		keysManager=sourceDAO.getKeyDao();
		beveragesManager=sourceDAO.getBeverageDescriptionDao();	
		ingredientsManager=sourceDAO.getIngredientRecipeDao();
		reportList=new LinkedList<>();
		reportList.add(new ReportPOJO("VEND1", "Connection Lost", "testing report", "Mr.Tester"));
	}
	
	
		
	/*Metodi di gestione del login*/
	
	/**
	 * Restituisce l'operatore loggato.
	 * @return operatore correttamente loggato
	 * 
	 */
	public OperatorPOJO getLoggedOperator() {
		return loggedOperator;
	}
	
	/**
	 * Esegue il logout dell'operatore.
	 * 
	 */
	public void setNotLogged() {
		loggedOperator=null;
	}
	
	/**
	 * Controlla che i dati inseriti nel form di login siano validi. In caso di successo, controlla se l'operatore
	 * loggato debba visualizzare contenuti limitati o no.
	 * @param username username inserito dall'utente
	 * @param password password inserita dall'utente
	 * @throws InvalidPasswordException
	 * @throws InvalidUserException
	 * 
	 */
	public void checkOperatorLogIn (String username, String password) throws InvalidPasswordException, InvalidUserException{
		OperatorPOJO operator=lookForOperator(username);
		
		if (operator.getCode().equals(username) && operator.getPassword().equals(password)) {
			loggedOperator=getOperator(username);
		}
		else {
			throw new InvalidPasswordException();
		}
		
		checkLimitation();
	}
	
	/**
	 * Cerca se lo username inserito è presente sul DB. In caso di successo, viene restituito l'operatore corrispondente.
	 * @param username username inserito dall'utente
	 * @return operatore reperito da DB
	 * @throws InvalidUserException
	 * 
	 */
	private OperatorPOJO lookForOperator(String username) throws InvalidUserException {
		OperatorPOJO operator;
		if (getOperator(username)==null) {
			throw new InvalidUserException();
		}
		else {
			operator=getOperator(username);
		}
		return operator;
	}
	
	/**
	 * Controlla se l'operatore loggato debba visualizzare contenuti limitati.
	 * 
	 */
	private void checkLimitation() {
		if (loggedOperator.getType().equals(OperatorType.Operator.toString())){
			setLimited(true);
		}
		else {
			setLimited(false);
		}
	}
	
	private void setLimited(boolean bool) {
		this.operatorLimited=bool;
	}
	
	/**
	 * Restituisce true se l'operatore loggato è limitato nella visualizzazione dei contenuti.
	 * @return boolean che indica se l'operatore loggato è limitato
	 */
	public boolean operatorIsLimited() {
		return operatorLimited;
	}
	

	/*Metodi di recupero dei VendingPOJO da DB.*/
	
	/**
	 * Recupera tutte le istanze di VendingPOJO corrispondenti alle tuple della table Vending.
	 * @return lista di VendingPOJO
	 */
	public List<VendingPOJO> getAllVendingMachines() {
		return vendingsManager.getAllVendings();
	}
	
	/**
	 * Restituisce l'istanza di VendingPOJO corrispondente all'id passato come parametro.
	 * @param id id della VendingMachine da cercare su DB
	 * @return istanza di VendingPOJO
	 */
	public VendingPOJO getVendingMachine(String id) {
		return vendingsManager.getVending(id);
	}
	
	/*Metodi di recupero dei VendingMachineInfo da Company.*/
	
	/**
	 * Restituisce l'istanza di VendingMachineInfo corrispondente all'id passato come parametro.
	 * @param id id della VendingMachine da cercare nella lista di VendingMachineInfo in Company.
	 * @return istanza di VendingMachineInfo
	 */
	public VendingMachineInfo getVendingMachineInfo(String id) {
		return Company.vendingMachineInfoList.get(id);	
	}
	
	/**
	 * Restituisce tutte le istanze di VendingMachineInfo presenti in Company.
	 * @return mappa di id-VendingMachineInfo 
	 */
	public Map<String, VendingMachineInfo> getAllVendingMachineInfo() {
		return Company.vendingMachineInfoList;	
	}
	
	/*Metodi di recupero e aggiornamento delle informazioni temporanee delle Vending.*/
	
	/**
	 * Recupera lo status della Vending il cui id è specificato come parametro.
	 * @param id id della Vending
	 * @return status della Vending
	 * 
	 */
	public String getVendingStatus(String id) {
		VendingMachineInfo info=getVendingMachineInfo(id);
		return info.getStatusString();
	}
	
	/**
	 * Recupera l'ammontare corrente della Vending il cui id è specificato come parametro.
	 * @param id id della Vending
	 * @return ammontare della Vending
	 * 
	 */
	public Double getVendingCurrentAmount(String id) {
		VendingMachineInfo info=getVendingMachineInfo(id);
		return info.getCurrentAmount();
	}
	
	/**
	 * Recupera i Tank della Vending il cui id è specificato come parametro.
	 * @param id id della Vending
	 * @return lista di Tank
	 * 
	 */
	public List<Tank> getTanks(String id){
		VendingMachineInfo info=getVendingMachineInfo(id);
		List<Tank> tanks=info.getTankList();
		return tanks;
	}
	
	/**
	 * Recupera i nomi dei Tank della Vending il cui id è specificato come parametro.
	 * @param id id della Vending
	 * @return lista dei nomi dei Tank
	 * 
	 */
	public List<String> getTanksNames(String id) {
		List<Tank> tanks=getTanks(id);
		List<String> tanksNames=new LinkedList<>();
		for (Tank entry: tanks) {
			tanksNames.add(entry.getId().toString());
		}
		
		tanksNames=fillWithNone(tanksNames, IngredientRecipePOJO.maxIngredientsPerVending);
		
		return tanksNames;
	}
	
	/**
	 * Recupera i livelli dei Tank della Vending il cui id è specificato come parametro.
	 * @param id id della Vending
	 * @return lista dei livelli dei Tank
	 * 
	 */
	public List<Double> getTanksLevels(String id) {
		List<Tank> tanks=getTanks(id);
		List<Double> tanksLevels=new LinkedList<>();
		
		for (Tank entry: tanks) {
			tanksLevels.add(entry.getLevel());
		}
		
		tanksLevels= fillWithZeroes(tanksLevels, IngredientRecipePOJO.maxIngredientsPerVending);
		return tanksLevels;
	}
	
	/**
	 * Recupera le temperature dei Tank della Vending il cui id è specificato come parametro.
	 * @param id id della Vending
	 * @return lista delle temperature dei Tank
	 * 
	 */
	public List<Double> getTanksTemps(String id) {
		List<Tank> tanks=getTanks(id);
		List<Double> tanksTemps=new LinkedList<>();
		
		for (Tank entry: tanks) {
			tanksTemps.add(entry.getTemperature());
		}
		
		tanksTemps= fillWithZeroes(tanksTemps, IngredientRecipePOJO.maxIngredientsPerVending);
		return tanksTemps;
	}
	
	/**
	 * Aggiorna le temperature dei Tank della Vending il cui id è specificato come primo parametro.
	 * @param vendingID id della Vending
	 * @param updatedTanksTemps temperature aggiornate
	 * 
	 */
	public void updateTanks(String vendingID, List<Double> updatedTanksTemps)  {
		VendingMachineInfo info=getVendingMachineInfo(vendingID);
		info.updateTanksTemp(updatedTanksTemps);
	}
	
	
	/*Metodi di recupero e aggiunta degli operatori su DB.*/
	
	/**
	 * Recupera tutte le istanze di OperatorPOJO corrispondenti alle tuple della table Operator.
	 * @return lista di OperatorPOJO
	 */
	public List<OperatorPOJO> getAllOperators() {
		return operatorsManager.getAllOperators();
	}
	
	/**
	 * Restituisce l'istanza di OperatorPOJO corrispondente al codice passato come parametro.
	 * @param code codice fiscale dell'operatore
	 * @return OperatorPOJO corrispondente al codice passato come parametro
	 */
	public OperatorPOJO getOperator(String code) {
		return operatorsManager.getOperator(code);
	}
	
	/**
	 * Aggiunge un operatore al DB.
	 * @param code codice fiscale dell'operatore
	 * @param name nome dell'operatore
	 * @param password password dell'operatore 
	 * @param type tipo di operatore
	 */
	public void addOperator(String code, String name, String password, String type) {
		operatorsManager.addOperator(code, name, password, type);
	}
	
	
	/*Metodi di recupero e aggiunta delle chiavette su DB.*/
	
	/**
	 * Recupera tutte le istanze di KeyPOJO corrispondenti alle tuple della table PaymentKey.
	 * @return lista di KeyPOJO
	 */
	public List<KeyPOJO> getAllKeys() {
		return keysManager.getAllKeys();
	}
	
	/**
	 * Aggiunge una chiavetta al DB.
	 * @param serialCode codice seriale della chiavetta
	 * @param credit credito di base della chiavetta
	 * 
	 */
	public void addKey(int serialCode, double credit) {
		keysManager.addKey(serialCode, credit);
	}
	
	/**
	 * Elimina una chiavetta dal DB.
	 * @param serialCode codice seriale della chiavetta da eliminare
	 * 
	 */
	public void deactivateKey(String serialCode) {
		keysManager.deactivateKey(serialCode);
	}
	
	
	/*Metodi di recupero dei BeverageDescriptionPOJO da DB.*/
	
	/**
	 * Recupera tutte le istanze di BeverageDescriptionPOJO corrispondenti alle tuple della table BeverageDescription.
	 * @return lista di BeverageDescriptionPOJO
	 */
	public List<BeverageDescriptionPOJO> getAllBeverageDescriptions() {
		return beveragesManager.getAllBeverageDescriptions();
	}
	
	/**
	 * Recupera l'istanza di BeverageDescriptionPOJO corrispondente al nome di bevanda passato.
	 * @param beverageName nome della bevanda
	 * @return istanza di BeverageDescriptionPOJO
	 */
	public BeverageDescriptionPOJO getBeverageDescription(String beverageName) {
		return beveragesManager.getBeverageDescriptionByBevName(beverageName);
	}
	
	
	/*Metodi di recupero e aggiornamento degli ingredienti memorizzati su DB.*/

	/**
	 * Aggiorna la quantità di un ingrediente di una ricetta su DB.
	 * @param idRecipe id della ricetta
	 * @param ingredientName nome dell'ingrediente
	 * @param quantity quantità dell'ingrediente
	 * 
	 */
	public void updateIngredients (String idRecipe, String ingredientName, double quantity) {
		ingredientsManager.updateIngredientRecipe(idRecipe, ingredientName, quantity);
	}
	
	/**
	 * Recupera i nomi degli ingredienti di una ricetta individuata dal parametro passato.
	 * @param idRecipe id della ricetta
	 * @return lista di nomi degli ingredienti
	 * 
	 */
	public List<String> getIngredientsNames(String idRecipe) {
		List<String> ingredientsNames=new LinkedList<>();
		List<IngredientRecipePOJO> recipe= ingredientsManager.getAllIngredientRecipeByIdRecipe(idRecipe);
		
		for (IngredientRecipePOJO entry: recipe) {
			ingredientsNames.add(entry.getIngredientName());
		}
		
		ingredientsNames=fillWithNone(ingredientsNames, BeverageDescriptionPOJO.maxIngredientsPerRecipe);
		
		return ingredientsNames;
	}
	
	/**
	 * Recupera le quantità degli ingredienti di una ricetta individuata dal parametro passato.
	 * @param idRecipe id della ricetta
	 * @return lista delle quantità degli ingredienti
	 * 
	 */
	public List<Double> getIngredientsQuantities(String idRecipe) {
		List<Double> ingredientsQuantities=new LinkedList<>();
		List<IngredientRecipePOJO> recipe= ingredientsManager.getAllIngredientRecipeByIdRecipe(idRecipe);
		
		for (IngredientRecipePOJO entry: recipe) {
			ingredientsQuantities.add(entry.getQuantity());
		}
		
		ingredientsQuantities=fillWithZeroes(ingredientsQuantities, BeverageDescriptionPOJO.maxIngredientsPerRecipe);
		
		return ingredientsQuantities;
	}
	
	
	/*Metodi di gestione dei report.*/
	
	/**
	 * Recupera i report memorizzati nel registro.
	 * @return lista di ReportPOJO
	 * 
	 */
	public List<ReportPOJO> getReportList() {
		return reportList;
	}
	
	/**
	 * Aggiunge un report al registro.
	 * @param report istanza di ReportPOJO
	 * 
	 */
	public void addReport(ReportPOJO report) {
		reportList.add(report);
	}
	
	/**
	 * Rimuove un report memorizzato nel registro.
	 * @param id id della Vending a cui il report si riferisce
	 * @param problem problema oggetto della segnalazione
	 * 
	 */
	public void removeReport(String id, String problem) {
		for (ReportPOJO report: reportList) {
			if (report.getVendingID().equals(id) && report.getProblem().equals(problem)) {
				reportList.remove(report);
			}
		}
	}
	
	/*Metodi di supporto*/
	
	/**
	 * Aggiunge elementi che rappresentano assenza di informazione alla lista passata come parametro, in modo che abbia 
	 * tanti elementi quanti il parametro max. 
	 * @param list lista di stringhe
	 * @param max numero desiderato di elementi della lista
	 * @return lista di stringhe con numero desiderato di elementi
	 * 
	 */
	public List<String> fillWithNone(List<String> list, int max) {
		List<String> OutList=list;
		if (OutList.size()<max) {
			int ingredientNumber= OutList.size();
			int i;
			for (i=0; i<(max-ingredientNumber); i++) {
				OutList.add(absenceString);
			}
		}
		return OutList;
	}
	
	/**
	 * Aggiunge elementi che rappresentano assenza di informazione alla lista passata come parametro, in modo che abbia 
	 * tanti elementi quanti il parametro max. 
	 * @param list lista di double
	 * @param max numero desiderato di elementi della lista
	 * @return lista di double con numero desiderato di elementi
	 * 
	 */
	public List<Double> fillWithZeroes(List<Double> list, int max) {
		List<Double> OutList=list;
		if (OutList.size()<max) {
			int ingredientNumber= OutList.size();
			int i;
			for (i=0; i<(max-ingredientNumber); i++) {
				OutList.add(0.0);
			}
		}
		return OutList;
	}
	
}
