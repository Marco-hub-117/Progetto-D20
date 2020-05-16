package it.unipv.ingsw.d20.model.vendingmachine;
import it.unipv.ingsw.d20.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.model.paymentsystem.payment.exceptions.InvalidPaymentException;
import it.unipv.ingsw.d20.model.vendingmachine.exceptions.RefillMachineException;
import it.unipv.ingsw.d20.model.vendingmachine.exceptions.TankAbsentException;
import it.unipv.ingsw.d20.model.vendingmachine.exceptions.WithdrawAmountException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.unipv.ingsw.d20.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.model.beverage.Tank;
import it.unipv.ingsw.d20.model.beverage.exceptions.DeliveryFailedException;

public class VendingMachine {

	private String id;
	private VendingMachineStatus status;
	private double totalAmount;
	private List salesRegister;
	private Double currentAmount;
	private String currentCode;
	private Map<String,Tank> tankList;
	private Map<String, BeverageDescription> bvDesc;	//catalogo per poter ottenere il prezzo della bevanda selezionata
	private BeverageCatalog bvCatalog;	//catalogo delle bevande
	
/**
 * 
 * @param id
 * @param totalAmount Inizialmente totalAmount viene impostato dalla company per i resti. Riscuotendo dalla macchinetta, per hp lasciamo 10â‚¬
 * 
 */
	
	public VendingMachine(String id, double totalAmount) {	
		this.id = id;
		this.setStatus(status.OFF);	//LASCIATO OFF PER POI RIEMPIRE SERBATOI LA PRIMA VOLTA
		this.totalAmount = totalAmount;
		this.currentAmount = 0.0;
		bvDesc = new HashMap<>();
		tankList = new HashMap<>();
		bvDesc = bvCatalog.getCatalog();	//La macchina ha il catalogo completo di tutte le bevande, anche di quelle che non contiene
		//INIT TANK
		
	}
	
	public void insertAmount(Double amount) {
		this.currentAmount += amount;
	}
	
	public void insertCode(String code) {
		this.currentCode = code;
	}
	
	public void startTransaction() {
		try {
			Sale s = new Sale(bvCatalog.getBeverageDesc(currentCode), currentAmount);
		}catch(InvalidPaymentException e) {
			//IN ATTESA DI IMPLEMENTAZIONE DELLE ECCEZIONI
		}catch(DeliveryFailedException e) {
			
		}
	}

	public Map<String, Double> getTanksLevels() {
		Map<String, Double> tanksLevel;
		tanksLevel = new HashMap<>(); 
		
		for(String id : tankList.keySet()) {
			tanksLevel.put(id, tankList.get(id).getLevel());
		}
		return tanksLevel;		//Warning: Eccezione da fare?
	}

	public void setTankLevel(String id, Double level) throws RefillMachineException{
		
		if(this.getStatus().equals(status.REFILL)) {
			tankList.get(id).setLevel(level);
		}else {
			throw new RefillMachineException("Stato della macchinetta non corretto");
		}
	}

	public void withdrawAmount() throws WithdrawAmountException, RefillMachineException{		//Vedere se refill o OFF
		
		if(this.getStatus().equals(status.REFILL)) {
			if(this.getTotalAmount()<Constants.IMPORTOMIN) {
				throw new WithdrawAmountException("Importo minore di 10 â‚¬");
			}else {
				this.setTotalAmount(Constants.IMPORTOMIN);
			}
		}else {
			throw new RefillMachineException("Stato della macchinetta non corretto");
		}
	}

	public void sendInfo() {				//DA CHIARIRE

		
		
	}

	public void setTankSettings(String id, Double temp) throws RefillMachineException, TankAbsentException{
		
		if(this.getStatus().equals(status.REFILL)) {
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
		 *  Pensare di togliere la classe ingredient perchè inutile. Non possiamo accedere alla set desc perchè non abbiamo l'ingrediente, ma il nome.
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
