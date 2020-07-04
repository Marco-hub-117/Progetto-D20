package it.unipv.ingsw.d20.vendingmachine.model.tanks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.unipv.ingsw.d20.util.persistence.PersistenceFactory;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.TankAbsentException;

/**
 * La classe ha il compito di gestire i serbatoi del distributore automatico.
 * 
 */
public class TankHandler {
	
	private HashMap<Ingredients,Tank> tankList;
	
	/**
	 * Costruttore di TankHandler.
	 * @param tankList lista dei Tank oresenti
	 */
	public TankHandler(HashMap<Ingredients,Tank> tankList) {
		this.tankList = tankList;
	}
	
	/**
	 * Metodo che controlla se la quantità nei serbatoi è sufficiente per erogare la bevanda.
	 * @param bvDesc Descrizione delle bevanda da erogare
	 * @return true se è possibile procedere con l'erogazione, false altrimenti.
	 */
	public boolean isAvailable(BeverageDescription bvDesc) {
		Map<Ingredients, Double> recipe = bvDesc.getIngredients();
		
		for (Entry<Ingredients, Double> entry : recipe.entrySet()) {
			if (tankList.get(entry.getKey()).getLevel() < entry.getValue()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Metodo che serve per ridurre la quantità nei serbatoi dopo l'erogazione della bevanda.
	 * @param bvDesc Descrizione delle bevanda da erogare
	 */
	public void scaleTanksLevel(BeverageDescription bvDesc) {
		for (Entry<Ingredients, Double> entry : bvDesc.getIngredients().entrySet()) {
			tankList.get(entry.getKey()).lowerLevelBy(entry.getValue());
		}
	}
	
	/**
	 * Metodo che permette di modificare la temperatura dei serbatoi.
	 * @param setpointList lista delle nuove temperature
	 */	
	public void modifyTankSettings(String setpointList) {
		String[] setpoints = setpointList.split(" ");
		int i = 0;
		for (Tank tank : tankList.values()) {
			tank.setTemperature(Double.parseDouble(setpoints[i]));
			i++;
		}
	}
	
	
	/**
	 * Metodo che ritorna una mappa con i livelli attuali dei tank.
	 * @return livelli dei tank
	 */
	public HashMap<Ingredients, Double> getTanksLevel() {
		HashMap<Ingredients, Double> tanksLevel = new HashMap<Ingredients, Double>();
		
		for (Entry<Ingredients, Tank> entry : tankList.entrySet()) {
			tanksLevel.put(entry.getKey(), entry.getValue().getLevel());
		}
		
		return tanksLevel;
	}
	
	/**
	 * Metodo che riempie il serbatoio indicato.
	 * @param id Id del serbatoio da riempire
	 */
	public void refillTank(String id) {
		Ingredients ingredient = Ingredients.valueOf(id);
		tankList.get(ingredient).refill();		
	}
	
	public HashMap<Ingredients, Tank> getTankList() {
		return tankList;
	}
	
	public int getTankNumber() {
		return tankList.size();
	}

}
