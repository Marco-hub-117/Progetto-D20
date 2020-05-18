package it.unipv.ingsw.d20.vendingmachine.model;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Tank;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.RefillMachineException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.TankAbsentException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.WithdrawAmountException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {

	private String id;
	private VendingMachineStatus status;
	private double totalAmount;
	private ArrayList<Sale> salesRegister;
	private Double currentAmount;
	private String currentCode;
	private HashMap<String,Tank> tankList;
	private BeverageCatalog bvCatalog;	//catalogo delle bevande
	
/**
 * 
 * @param id
 * @param totalAmount Inizialmente totalAmount viene impostato dalla company per i resti. Riscuotendo dalla macchinetta, per hp lasciamo 10€
 * 
 */
	
	public VendingMachine(String id, double totalAmount) {	
		this.id = id;
		this.setStatus(VendingMachineStatus.OFF);	//LASCIATO OFF PER POI RIEMPIRE SERBATOI LA PRIMA VOLTA
		this.totalAmount = totalAmount;
		this.currentAmount = 0.0;
		salesRegister = new ArrayList<Sale>();
		tankList = new HashMap<>();
		//INIT TANK
		
	}
	
	public void insertAmount(Double amount) {
		this.currentAmount += amount;
	}
	
	public void insertCode(String code) {
		this.currentCode = code;
		startTransaction();
	}
	
	public void startTransaction() {
		try {
			Sale s = new Sale(bvCatalog.getBeverageDesc(currentCode), currentAmount);
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

	public void withdrawAmount() throws WithdrawAmountException, RefillMachineException{		//Vedere se refill o OFF
		
		if(this.getStatus().equals(VendingMachineStatus.REFILL)) {
			if(this.getTotalAmount() < Constants.IMPORTOMIN) {
				throw new WithdrawAmountException("Importo minore di 10 €");
			}else {
				this.setTotalAmount(Constants.IMPORTOMIN);
			}
		}else {
			throw new RefillMachineException("Stato della macchinetta non corretto");
		}
	}

	public void sendInfo() {				//DA CHIARIRE

		/*
		 * Pensata la creazione Di una classe VendingMachineInfo che contiene al suo interno tutte le informazioni che servono.
		 * 
		 */
		
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

	public void setIngredient(String code, String name, Double quantity) {		// commenti dentro
		/*
		 *  Pensare di togliere la classe ingredient perch� inutile. Non possiamo accedere alla set desc perch� non abbiamo l'ingrediente, ma il nome.
		 *  
		 */
	}

	
	public void setStatus(VendingMachineStatus status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}
	
	public Double getCurrentAmount() {
		return currentAmount;
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
	
	
	

}
