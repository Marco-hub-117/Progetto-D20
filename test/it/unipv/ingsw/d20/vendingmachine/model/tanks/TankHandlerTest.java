package it.unipv.ingsw.d20.vendingmachine.model.tanks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;

class TankHandlerTest {
	private HashMap<Ingredients,Tank> tankList=new HashMap<Ingredients,Tank>();
	private Ingredients i1=Ingredients.CHOCOLATE;
	private Ingredients i2=Ingredients.COFFEE;
	private Ingredients i3=Ingredients.MILK;
	private Ingredients i4=Ingredients.WATER;
	private Tank t1=new Tank(i1,5.4,300.0);
	private Tank t2=new Tank(i2,65.835,370.0);
	private Tank t3=new Tank(i3,98.6, 273.15);
	private Tank t4=new Tank(i4,2.0, 293.15);
	private BeverageDescription b1=new BeverageDescription("a1","caffe macchiato",0.5);
	private TankHandler q;
	@Test
	void testisAvailable() {
		init();
		boolean real;
		real=q.isAvailable(b1);
		assertTrue(real);
		
	}
	private void init () {
		tankList.put(i1, t1);
		tankList.put(i2, t2);
		tankList.put(i3, t3);
		tankList.put(i4,t4);
		q=new TankHandler(tankList);
		b1.addIngredient(i2,1);
		b1.addIngredient(i3,0.5);
		b1.addIngredient(i4, 50.0);
		
	}

}
