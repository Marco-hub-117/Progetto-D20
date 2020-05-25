package it.unipv.ingsw.d20.vendingmachine.model;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Tank;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.RefillMachineException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.TankAbsentException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.WithdrawAmountException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.CashHandler;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.KeyHandler;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.ICreditStrategy;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.KeyNotInsertedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.UnrecognisedKeyException;

import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {

	private String id;
	private VendingMachineStatus status;
	private double totalAmount;
	private ArrayList<Sale> salesRegister;
	private double credit;
	private String currentCode;
	private HashMap<String,Tank> tankList;
	private BeverageCatalog bvCatalog;	//catalogo delle bevande
	
	private CashHandler cashHandler; //classi che gestiscono il pagamento
	private KeyHandler keyHandler;
	

	public VendingMachine() {
		//for testing
	}
	
	/**
	 * Costruttore della classe VendingMachine
	 * @param id Stringa che rappresenta l'ID univoco della macchinetta
	 * @param totalAmount Inizialmente totalAmount viene impostato dalla company per i resti. 
	 * 
	 */
	public VendingMachine(String id, double totalAmount) {	
		this.id = id;
		this.setStatus(VendingMachineStatus.OFF);	//LASCIATO OFF PER POI RIEMPIRE SERBATOI LA PRIMA VOLTA
		this.totalAmount = totalAmount;
		this.credit = 0.0;
		
		salesRegister = new ArrayList<Sale>();
		tankList = new HashMap<>(); //INIT TANK
		
		cashHandler = new CashHandler(15, 15, 15, 15, 15); //Gli argomenti sono il numero di monete per tipo con cui viene inizializzata 
														   //la macchinetta -> da valutare se passargliele con un array di int o cambiare 
		keyHandler = new KeyHandler();
	}
	
	public void insertCoin(double coinValue) { 
		try {
			cashHandler.addCoin(coinValue); //se la moneta Ã¨ valida la aggiunge al CashHandler
			credit += coinValue;			//e aggiorna il credito
		} catch (InvalidPaymentException e) { //altrimenti viene raccolta la relativa eccezione
			e.printStackTrace();
		}
	}
	
	public void insertKey(String serialCode) {
		try {
			keyHandler.insertKey(serialCode); //se la chiavetta Ã¨ riconosciuta viene inserita
			credit += keyHandler.getCreditOnKey(); //e si aggiorna il credito della macchinetta con quello
												   //disponibile sulla chiavetta
		} catch (UnrecognisedKeyException e) { //altrimenti viene raccolta la relativa eccezione
			
		}
	}
	
	public void ejectKey(double credit) {
		try {
			keyHandler.ejectKey(credit); //se c'Ã¨ una chiavetta inserita la si toglie (passandole il nuovo credito)
			credit = 0;					 //e si azzera il credito corrente della macchinetta
		} catch (KeyNotInsertedException e) { //altrimenti viene raccolta la relativa eccezione
			e.printStackTrace();
		}
	}
	
	public void dispenseCash() {
		if (keyHandler.keyIsInserted()) { //controlla che non ci sia una chiavetta inserita, si potrebbe usare un'eccezione
			return;
		}
		
		try {
			cashHandler.dispenseRest(credit);
		} catch (InsufficientCashForRestException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCode(String code) {
		BeverageDescription bvDesc = bvCatalog.getBeverageDesc(code);
		
		if (bvDesc == null) {
			//throw new NonExistentCodeException();
		} else {
			startTransaction(bvDesc);
		}
	}
	
	public void startTransaction(BeverageDescription bvDesc) {
		try {
			Sale s = new Sale(bvDesc, credit);
			credit = s.getRest();
			salesRegister.add(s);
		}catch(InvalidPaymentException e) {
			//IN ATTESA DI IMPLEMENTAZIONE DELLE ECCEZIONI
		}catch(DeliveryFailedException e) {
			
		}
	}

	public HashMap<String, Double> getTanksLevels() {
		HashMap<String, Double> tanksLevel;
		tanksLevel = new HashMap<>(); 
		
		for(String id : tankList.keySet()) {
			tanksLevel.put(id, tankList.get(id).getLevel());
		}
		return tanksLevel;		//Warning: Eccezione da fare?
	}

	public void setTankLevel(String id, Double level) throws RefillMachineException{
		
		if(this.getStatus().equals(VendingMachineStatus.REFILL)) {
			tankList.get(id).setLevel(level);
		}else {
			throw new RefillMachineException("Stato della macchinetta non corretto");
		}
	}
	
	/**
	 * Metodo che gestisce il ritiro del credito dalla VendingMachine.
	 * @throws WithdrawAmountException
	 * @throws RefillMachineException
	 */

	public void withdrawAmount() throws WithdrawAmountException, RefillMachineException{		//Vedere se refill o OFF
		
		if(this.getStatus() != VendingMachineStatus.REFILL) { // Controllare se lo stato della macchinetta è corretto
			throw new RefillMachineException("Stato della macchinetta non corretto");
		}
		
		if(this.getTotalAmount() < Constants.IMPORTOMIN) { // verificare che se per qualche motivo il credito attuale della macchinetta è minore dell'importo minimo.
			throw new WithdrawAmountException("Importo minore di 10 €");
		}
		
		// Se tutte le verifiche hanno riportato esito negativo, il totalAmount viene aggiornato correttamente.
		this.totalAmount = Constants.IMPORTOMIN;
	}
	
	/**
	 * Questo metodo crea una nuova istanza della classe VendingMachineInfo e la restituisce.
	 * @return VendingMachineInfo contiene al suo interno le informazioni di questa VendingMachine.
	 */

	public VendingMachineInfo sendInfo() {				
		return new VendingMachineInfo(id, status, totalAmount, tankList);	
	}

	public void setTankSettings(String id, Double temp) throws RefillMachineException, TankAbsentException{
		
		if(this.getStatus().equals(VendingMachineStatus.REFILL)) {
			if(tankList.containsKey(id)) {
				tankList.get(id).setTemperature(temp);
			}else {
				throw new TankAbsentException("Tank non presente");
			}
		}else {
			throw new RefillMachineException("Stato della macchinetta non corretto");
		}
	}

	public void setIngredient(String code, String name, Double quantity) {		
		/*
		 * Creare nella classe beverageCatalog un metodo che permette l'aggiunta di un ingrediente 
		 * passando come argomento il nome dell'ingrediente, e non un'oggetto Ingredients.
		 */
		//this.bvCatalog.setIngredient(code, name, quantity);
	}

	
	public void setStatus(VendingMachineStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	
	public Double getCurrentAmount() {
		return credit;
	}
	
	public void setCurrentAmount(double amount) {
		credit=amount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public VendingMachineStatus getStatus() {
		return status;
	}
	
	/* metodo provvisorio per un'opzione di gestione dei pagamenti
	public double elaborateCredit(ICreditStrategy strategy, Object creditInfo) {
		try {
			double amount=strategy.elaborateCredit(creditInfo);
			currentAmount+=amount;
			return currentAmount;
		} catch (InvalidPaymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0; 
	}*/
	
	

}
