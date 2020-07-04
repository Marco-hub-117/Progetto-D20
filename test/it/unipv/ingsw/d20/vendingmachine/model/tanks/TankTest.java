package it.unipv.ingsw.d20.vendingmachine.model.tanks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;

/**
 * Test JUnit 5 sulla classe Tank. Si testano i metodi lowerLevelBy e refill,
 * a seconda delle classi di equivalenza individuate.
 * 
 * */
public class TankTest {
	
	private Tank t1;
	
	/**
	 * Metodo di inizializzazione.
	 * 
	 * */
	private void init() {
		t1=new Tank(Ingredients.PEACH_TEA,45 ,3 , 200);
	}

	//TEST SU lowerLevelBy
	/**
	 * Test su lowerLevelBy, con quantità positiva.
	 * 
	 * */
	@Test
	void testLowerLevelBy() {
		init();
		Double expected=t1.getLevel()-10*100/200;
		t1.lowerLevelBy(10);
		Double real=t1.getLevel();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su lowerLevelBy, con quantità 0.
	 * 
	 * */
	@Test
	void testLowerLevelBy0() {
		init();
		Double expected=t1.getLevel();
		t1.lowerLevelBy(0);
		Double real=t1.getLevel();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su lowerLevelBy, con quantità negativa.
	 * 
	 * */
	@Test
	void testLowerLevelByNeg() {
		init();
		Double expected=t1.getLevel();
		t1.lowerLevelBy(-5);
		Double real=t1.getLevel();
		assertEquals(expected, real);
	}
	
	//TEST SU refill
	/**
	 * Test su refill.
	 * 
	 * */
	@Test
	void testRefill() {
		init();
		Double expected=100.0;
		t1.refill();
		Double real=t1.getLevel();
		assertEquals(expected, real);
	}
	
}
