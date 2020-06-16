package it.unipv.ingsw.d20.vendingmachine.model;


import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Beverage;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.InsufficientIngredientsException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.KeyRestException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.NonExistentCodeException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.RefillMachineException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.TankAbsentException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.WithdrawAmountException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.CashContainer;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.KeyHandler;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;
import it.unipv.ingsw.d20.vendingmachine.model.tanks.Tank;
import it.unipv.ingsw.d20.vendingmachine.model.tanks.TankHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {

	private String id;
	private VendingMachineStatus status;
	private ArrayList<Sale> salesRegister;
	private double credit; //soldi attualmente inseriti
	private TankHandler tankHandler;
	private BeverageCatalog bvCatalog;	//catalogo delle bevande
	private CashContainer cashContainer;
	private KeyHandler keyHandler;
	
	/**
	 * Costruttore della classe VendingMachine
	 * @param id Stringa che rappresenta l'ID univoco della macchinetta
	 * 
	 */
	public VendingMachine(String id) {	
		this.id = id;
		this.credit = 0.0;
		
		salesRegister = new ArrayList<Sale>(); //DA IMLEMENTARE COME BVCATALOG E TANKLIST
		
		keyHandler = new KeyHandler();
		
		bvCatalog = getCatalogFromLocal();//la vending istanzia il catalogo delle bevande prendedolo dal file locale
		tankHandler = new TankHandler(getTanksFromLocal());
		cashContainer = getCashContainerFromLocal(); 
	}
	/**
	 * Questo metodo gestisce l'inserimento di una moneta 
	 * @param coinValue è il valore della moneta
	 * 
	 */
	public void insertCoin(double coinValue) {
		cashContainer.addCoin(coinValue); //aggiunge la moneta al CashHandler
		saveCashContainerIntoLocal();
		credit += coinValue;			//e aggiorna il credito
	}
	
	public void insertKey() throws UnrecognisedKeyException { 
		keyHandler.insertKey(credit);
		credit=keyHandler.getCreditOnKey();
	}
	
	public void ejectKey() { 
		keyHandler.ejectKey();
		credit = 0.0;
	}
	/**
	 * Questo metodo restituisce il resto al cliente
	 * @throws InsufficientCashForRestException
	 * @throws KeyRestException 
	 * 
	 */
	public void dispenseCash() throws InsufficientCashForRestException, KeyRestException { 
		if (keyHandler.keyIsInserted()) { 
			throw new KeyRestException("Impossibile erogare resto, togliere la chiavetta");
		}
		cashContainer.dispenseRest(credit);
		saveCashContainerIntoLocal();
		credit = 0.0;
	}
	
	/**
	 * Questo metodo permette al cliente di inserire il codice della bevanda, inizializza la vendita dopo aver fatto i controlli
	 * @throws InsufficientCreditException
	 * @throws NonExistentCodeException
	 * @throws InsufficientIngredientsException 
	 * 
	 */
	public void insertCode(String code) throws InsufficientCreditException, NonExistentCodeException, InsufficientIngredientsException { 
		BeverageDescription bvDesc = bvCatalog.getBeverageDesc(code);
		
		if (bvDesc == null) {
			throw new NonExistentCodeException("Codice della bevanda inesistente"); 
		} else if (tankHandler.isAvailable(bvDesc)) {
			startTransaction(bvDesc);
		} else {
			throw new InsufficientIngredientsException("Spiacente, bevanda terminata");
		}
	}
	
	/**
	 * Questo metodo esegue la transazione e l'erogazione effettiva della bevanda
	 * @throws InsufficientCreditException 
	 * 
	 */
	public void startTransaction(BeverageDescription bvDesc) throws InsufficientCreditException { 
		Sale s = new Sale(bvDesc, credit); //se il credito non è sufficiente per erogare la bevanda lancia eccezione
		//TODO saveSaleIntoLocal();
		saveCashContainerIntoLocal();
		
		tankHandler.scaleTanksLevel(bvDesc);
		saveTankIntoLocal();
		
		Beverage bev = new Beverage(); bev.start();
		System.out.println("Erogato " + bvDesc.getName() + " correttamente");
		credit = s.getRest();
		salesRegister.add(s);
	}
	
	public HashMap<Ingredients, Double> getTanksLevels() {
		return tankHandler.getTanksLevel();
	}

	public void refillTanks(String id){
		tankHandler.refillTank(id); 
		saveTankIntoLocal();
	}
	
	/**
	 * Metodo che gestisce il ritiro del credito dalla VendingMachine.
	 * @throws WithdrawAmountException
	 * @throws RefillMachineException
	 */
	public double withdrawAmount() throws WithdrawAmountException, RefillMachineException { //Vedere se refill o OFF
		double withdrawnAmount = cashContainer.withdrawAmount();
		saveCashContainerIntoLocal();
		//lanciare le eccezioni non so come, fate voi lol
		return withdrawnAmount;
	}

	public void modifyTankSettings(String id, Double temp) throws RefillMachineException, TankAbsentException{ //serve per modificare la temperatura del tank
	/*	
		if(status.equals(VendingMachineStatus.REFILL)) {
			if(tankList.containsKey(id)) {
				tankList.get(id).setTemperature(temp);
			}else {
				throw new TankAbsentException("Tank non presente");
			}
		}else {
			throw new RefillMachineException("Stato della macchinetta non corretto");
		}*/
	}

	public void setIngredient(String code, String name, Double quantity) {		
		/*
		 * Creare nella classe beverageCatalog un metodo che permette l'aggiunta di un ingrediente 
		 * passando come argomento il nome dell'ingrediente, e non un'oggetto Ingredients.
		 */
		//this.bvCatalog.setIngredient(code, name, quantity);
	}
	
	public BeverageCatalog getCatalogFromLocal() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		return v.getCatalogFromLocal();
	}

	public void saveCatalogIntoLocal () {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		v.saveCatalogIntoLocal(bvCatalog);
	}
	
	public HashMap<Ingredients,Tank> getTanksFromLocal() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		return v.getTanksFromLocal();
	}
	
	public void saveTankIntoLocal() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		v.saveTankIntoLocal(tankHandler.getTankList());
	}
	
	private CashContainer getCashContainerFromLocal() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		return v.getCashContainerFromLocal();
	}
	
	public void saveCashContainerIntoLocal() {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		v.saveCashContainerIntoLocal(cashContainer);
	}
	
	public void setStatus(VendingMachineStatus status) {
		this.status = status;
	}
	
	public boolean isCorrectId(String insertedId) {
		if (id.equals(insertedId)) 
			return true;
		else
			return false;
	}

	public String getId() {
		return id;
	}
	
	public Double getCredit() {
		return credit;
	}
	
	public void setCurrentAmount(double amount) {
		credit=amount;
	}

	public VendingMachineStatus getStatus() {
		return status;
	}
	
	public BeverageCatalog getCatalog() {
		return bvCatalog;
	}
	
	public int getTankNumber() {
		return tankHandler.getTankNumber();
	}

	public HashMap<Ingredients, Tank> getTankList() {
		return tankHandler.getTankList();
	}
}
