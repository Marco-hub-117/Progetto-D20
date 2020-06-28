package it.unipv.ingsw.d20.vendingmachine.model.tanks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.TankAbsentException;

/**
 * La classe gestisce i serbatoi degli ingredienti del distributore.
 * 
 */
public class TankHandler {
	
	private HashMap<Ingredients,Tank> tankList;
	
	/**
	 * Costruttore di TankHandler.
	 * @param tankList
	 */
	public TankHandler(HashMap<Ingredients,Tank> tankList) {
		this.tankList = tankList;
	}
	
	/**
	 * Metodo che controlla se la quantitànei serbatoi è sufficiente per erogare la bevanda.
	 * @param bvDesc Descrizione delle bevanda da erogare
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
	 * Metodo che serve per ridurre la quantitànei serbatoi dopo l'erogazione della bevanda.
	 * @param bvDesc Descrizione delle bevanda da erogare
	 */
	public void scaleTanksLevel(BeverageDescription bvDesc) {
		for (Entry<Ingredients, Double> entry : bvDesc.getIngredients().entrySet()) {
			tankList.get(entry.getKey()).lowerLevelBy(entry.getValue());
		}
	}
	
	/**
	 * Metodo che permette di modificare la temperatura di un serbatoio.
	 * @param id Id del serbatoio
	 * @param temp nuova temperatura
	 * @throws TankAbsentException
	 */	
	public void modifyTankSettings(String id, double temp) throws TankAbsentException {
		Ingredients ingredient = Ingredients.valueOf(id);
		
		if(tankList.containsKey(ingredient)) {
			tankList.get(ingredient).setTemperature(temp);
		}else {
			throw new TankAbsentException("Tank non presente");
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
