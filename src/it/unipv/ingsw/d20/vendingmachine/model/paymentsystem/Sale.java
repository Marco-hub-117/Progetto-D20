package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;


import java.text.SimpleDateFormat;
import java.util.Date;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.*;

/**
 * Classe che gestisce la vendita di una bevanda.
 * 
 */
public class Sale {
	
	String vmId;
	private Date date;
	private BeverageDescription beverageDescription;
	private double price;
	private double rest;
	
	/**
	 * Costruttore della classe Sale
	 * @param vmId Id del distributore
	 * @param beverageDescription Descrizione delle bevanda da erogare
	 * @param credit Credito inserito
	 * @throws InsufficientCreditException
	 */
	public Sale(String vmId, BeverageDescription beverageDescription, double credit) throws InsufficientCreditException {
		this.vmId = vmId;
		this.beverageDescription = beverageDescription;
		date = new Date();
		price = beverageDescription.getPrice();
		rest = checkCredit(credit, price);
	}
	
	/**
	 * Il metodo controlla che il credito sia sufficiente per la bevanda selezionata e,
	 * se possibile, ritorna il resto.
	 * @param credit Credito inserito
	 * @param price Costo della bevanda
	 * @throws InsufficientCreditException
	 */
	private double checkCredit(double credit, double price) throws InsufficientCreditException {
		if (credit >= price) {
			return credit - price;
		} else { 
			throw new InsufficientCreditException("Il credito inserito non è sufficiente");
		}		
	}

	@Override
	public String toString() { 
		StringBuilder saleInfo = new StringBuilder();
		
		saleInfo.append(vmId + "	");
		
		saleInfo.append(beverageDescription.getName() + "	");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		saleInfo.append(sdf.format(date) + "	");
		
		return saleInfo.toString();
	}
	
	public double getRest() {
		return rest;
	}
	
}
