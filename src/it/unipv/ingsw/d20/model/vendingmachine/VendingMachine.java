package it.unipv.ingsw.d20.model.vendingmachine;
import it.unipv.ingsw.d20.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.model.paymentsystem.payment.exceptions.InvalidPaymentException;

import java.util.HashMap;
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
	private List Sale;
	private Double currentAmount;
	private String currentCode;
	private Map<String,Tank> tankList;
	private Map<String, BeverageDescription> bvDesc;	//catalogo per poter ottenere il prezzo della bevanda selezionata
	private BeverageCatalog bvCatalog;	//catalogo delle bevande
	
/**
 * 
 * @param id
 * @param totalAmount Inizialmente totalAmount viene impostato dalla company per i resti
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

	public void getTanksLevels() {
		
	}

	public void setTankLevel(String id, Double level) {
		
	}

	public void withdrawAmount() {

		
	}

	public void sendInfo() {
		
	}

	public void setTankSettings(String id, Double temp) {
		
	}

	public void setIngredient(String code, String name, Double quantity) {
		
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
	
	

}
