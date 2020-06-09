package it.unipv.ingsw.d20.vendingmachine.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Tank;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.TankAbsentException;

public class TankHandler {
	
	private HashMap<Ingredients,Tank> tankList;
	
	public TankHandler(HashMap<Ingredients,Tank> tankList) {
		this.tankList = tankList;
	}
	
	public boolean isAvailable(BeverageDescription bvDesc) {
		Map<Ingredients, Double> recipe = bvDesc.getIngredients();
		
		for (Entry<Ingredients, Double> entry : recipe.entrySet()) {
			if (tankList.get(entry.getKey()).getLevel() < entry.getValue()) {
				return false;
			}
		}
		
		return true;
	}
	
	public void scaleTanksLevel(BeverageDescription bvDesc) {
		for (Entry<Ingredients, Double> entry : bvDesc.getIngredients().entrySet()) {
			tankList.get(entry.getKey()).lowerLevelBy(entry.getValue());
		}
	}

	public HashMap<Ingredients, Tank> getTankList() {
		return tankList;
	}
	
	public void modifyTankSettings(String id, double temp) throws TankAbsentException {
		Ingredients ingredient = Ingredients.valueOf(id);
		
		if(tankList.containsKey(ingredient)) {
			tankList.get(ingredient).setTemperature(temp);
		}else {
			throw new TankAbsentException("Tank non presente");
		}
	}

	public HashMap<Ingredients, Double> getTanksLevel() {
		HashMap<Ingredients, Double> tanksLevel = new HashMap<Ingredients, Double>();
		
		for (Entry<Ingredients, Tank> entry : tankList.entrySet()) {
			tanksLevel.put(entry.getKey(), entry.getValue().getLevel());
		}
		
		return tanksLevel;
	}

	public void refillTank(String id) {
		Ingredients ingredient = Ingredients.valueOf(id);
		
		tankList.get(ingredient).refill();		
	}

	public int getTankNumber() {
		return tankList.size();
	}

}
