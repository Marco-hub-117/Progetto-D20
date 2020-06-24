package it.unipv.ingsw.d20.vendingmachine.model.tanks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;

class TankHandlerTest {
	private HashMap<Ingredients,Tank> tankList;
	private final TankHandler t = new TankHandler(tankList);
	
	@Test
	void test() {
		
	}

}
