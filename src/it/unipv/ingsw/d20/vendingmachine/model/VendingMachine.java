package it.unipv.ingsw.d20.vendingmachine.model;


import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Beverage;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Tank;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.InsufficientIngredientsException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.NonExistentCodeException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.RefillMachineException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.TankAbsentException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.WithdrawAmountException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.CashContainer;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.KeyHandler;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidPaymentException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.KeyNotInsertedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.UnrecognisedKeyException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class VendingMachine {

	private String id;
	private VendingMachineStatus status;
	private ArrayList<Sale> salesRegister;
	private double credit; //soldi attualemente inseriti
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
		this.setStatus(VendingMachineStatus.REFILL);	//LASCIATO REFILL PER POI RIEMPIRE SERBATOI LA PRIMA VOLTA
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
	
	public void insertKey(String serialCode) { //da rivedere perché generata casualmente
		
		/*
		 * try { keyHandler.insertKey(serialCode); //se la chiavetta è riconosciuta
		 * viene inserita credit += keyHandler.getCreditOnKey(); //e si aggiorna il
		 * credito della macchinetta con quello //disponibile sulla chiavetta } catch
		 * (UnrecognisedKeyException e) { //altrimenti viene raccolta la relativa
		 * eccezione
		 * 
		 * }
		 */
	}
	
	public void ejectKey(double credit) { //da rivedere perché generata casualmente
		/*
		 * try {
		 * keyHandler.ejectKey(credit); //se c'è una chiavetta inserita la si toglie
		 * (passandole il nuovo credito) credit = 0; //e si azzera il credito corrente
		 * della macchinetta } catch (KeyNotInsertedException e) { //altrimenti viene
		 * raccolta la relativa eccezione e.printStackTrace(); }
		 */
	}
	/**
	 * Questo metodo restituisce il resto al cliente
	 * @throws InsufficientCashForRestException
	 * 
	 */
	public void dispenseCash() throws InsufficientCashForRestException { //gestire l'eccezione nel controller
		if (keyHandler.keyIsInserted()) { //controlla che non ci sia una chiavetta inserita, si potrebbe usare un'eccezione
			return;
		}
			cashContainer.dispenseRest(credit);
			saveCashContainerIntoLocal();
			credit = 0;
	}
	/**
	 * Questo metodo permette al cliente di inserire il codice della bevanda, inizializza la vendita dopo aver fatto i controlli
	 * @throws InsufficientCreditException
	 * @throws NonExistentCodeException
	 * @throws InsufficientIngredientsException 
	 * 
	 */
	public void insertCode(String code) throws InsufficientCreditException, NonExistentCodeException, InsufficientIngredientsException { //le eccezioni vanno gestite nel controller 
		BeverageDescription bvDesc = bvCatalog.getBeverageDesc(code);
		
		if (bvDesc == null) {
			throw new NonExistentCodeException("Codice non valido"); 
		} else if (tankHandler.isAvailable(bvDesc)) {
			startTransaction(bvDesc);
		} else {
			throw new InsufficientIngredientsException("Spiacente, bevanda terminata");
		}
	}
	/**
	 * Questo metodo esegue la transazione e l'erogazione effettiva della bevanda
	 * @throws InsufficientCreditException viene lanciata se il credito inserito non è sufficiente per erogare la bevanda scelta
	 * 
	 */
	public void startTransaction(BeverageDescription bvDesc) throws InsufficientCreditException { 
		setStatus(VendingMachineStatus.DISPENSING);
		
		try {
			Sale s = new Sale(bvDesc, credit); //se il credito non è sufficiente per erogare la bevanda lancia eccezione
			tankHandler.scaleTanksLevel(bvDesc);
			saveTankIntoLocal();
			saveCashContainerIntoLocal();
			TimeUnit.SECONDS.sleep(3); //attesa di 3 secondi per erogare la bevanda
			System.out.println("Erogato " + bvDesc.getName() + " correttamente");
			credit = s.getRest();
			salesRegister.add(s);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage()); 
		} finally {
			setStatus(VendingMachineStatus.READY);
		}
	}
	
	public HashMap<Ingredients, Double> getTanksLevels() {
		return tankHandler.getTanksLevel();
	}

	public void setTankLevel(String id){
		tankHandler.refillTank(id); 
	}
	
	/**
	 * Metodo che gestisce il ritiro del credito dalla VendingMachine.
	 * @throws WithdrawAmountException
	 * @throws RefillMachineException
	 */

	public void withdrawAmount() throws WithdrawAmountException, RefillMachineException{	//Vedere se refill o OFF
		//da implementare secondo cashHandler
	}
	
	/**
	 * Questo metodo crea una nuova istanza della classe VendingMachineInfo e la restituisce.
	 * @return VendingMachineInfo contiene al suo interno le informazioni di questa VendingMachine.
	 */

	public VendingMachineInfo sendInfo() {				
		return new VendingMachineInfo(id, status, cashContainer.getTotalAmount(), tankHandler.getTankList());	
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

	public String getId() {
		return id;
	}
	
	public Double getCurrentAmount() {
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
