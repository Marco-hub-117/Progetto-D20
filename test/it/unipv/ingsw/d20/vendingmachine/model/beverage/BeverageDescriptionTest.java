package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test JUnit 5 sulla classe BeverageDescription. Si testano i metodi addIngredient e changeQuantity,
 * a seconda delle classi di equivalenza individuate.
 * 
 * */
public class BeverageDescriptionTest {
	
	private Ingredients i1;
	private Ingredients i2;
	private BeverageDescription b1;
	
	/**
	 * Metodo di inizializzazione.
	 * 
	 * */
	private void init() {
		i1=Ingredients.CHOCOLATE;
		i2=Ingredients.MILK;
		b1=new BeverageDescription("CM1","chocolate milk",2.0);
		b1.addIngredient(i1, 3);
		b1.addIngredient(i2, 5);
	}
	
	
	//TEST su addIngredient.
	/**
	 * Test su addIngredient con quantità > 0.
	 * 
	 * */
	@Test
	void testAddIngredient() {
		init();
		b1.addIngredient(Ingredients.SUGAR, 5.2);
		int expected=3;
		int real = b1.getIngredients().size();
		assertEquals(expected , real);
		double secondExpected = 5.2;
		double secondReal = b1.getIngredientQuant(Ingredients.SUGAR);
		assertEquals(secondExpected, secondReal);
	}
	
	/**
	 * Test su addIngredient con quantità = 0.
	 * 
	 * */
	@Test
	void testAddIngredientZero() {
		init();
		b1.addIngredient(Ingredients.SUGAR);
		int expected=3;
		int real = b1.getIngredients().size();
		assertEquals(expected , real);
		double secondExpected = 0.0;
		double secondReal = b1.getIngredientQuant(Ingredients.SUGAR);
		assertEquals(secondExpected, secondReal);
	}
	
	/**
	 * Test su addIngredient con quantità < 0.
	 * 
	 * */
	@Test
	void testAddIngredientNeg() {
		init();
		b1.addIngredient(Ingredients.SUGAR, -3);
		int expected=3;
		int real = b1.getIngredients().size();
		assertEquals(expected , real);
		double secondExpected = 0;
		double secondReal = b1.getIngredientQuant(Ingredients.SUGAR);
		assertEquals(secondExpected, secondReal);
	}
	
	/**
	 * Test su addIngredient con ingrediente già presente
	 * 
	 * */
	@Test
	void testAddIngredientDuplicate() {
		init();
		b1.addIngredient(Ingredients.CHOCOLATE);
		int expected=2;
		int real = b1.getIngredients().size();
		assertEquals(expected , real);
		double secondExpected = 0;
		double secondReal = b1.getIngredientQuant(Ingredients.SUGAR);
		assertEquals(secondExpected, secondReal);
	}
	
	
	//TEST su changeQuantity
	/**
	 * Test su changeQuantity con quantità > 0.
	 * 
	 * */
	@Test
	void testChangeQuantity() {
		init();
		b1.changeQuantity(i1, 20);
		double expected = 20;
		double real = b1.getIngredientQuant(i1);
		assertEquals(expected, real);
	}
	
	/**
	 * Test su changeQuantity con quantità = 0.
	 * 
	 * */
	@Test
	void testChangeQuantityZero() {
		init();
		b1.changeQuantity(i1, 0);
		double expected=0.0;
		double real = b1.getIngredientQuant(i1);
		assertEquals(expected, real);
	}
	
	/**
	 * Test su changeQuantity con quantità < 0.
	 * 
	 * */
	@Test
	void testChangeQuantityNeg() {
		init();
		b1.changeQuantity(i1, -4);
		double expected=0.0;
		double real = b1.getIngredientQuant(i1);
		assertEquals(expected, real);
	}
	
	/**
	 * Test su changeQuantity con ingrediente non presente
	 * 
	 * */
	@Test
	void testChangeQuantityAbsent() {
		init();
		b1.changeQuantity(Ingredients.LEMON_TEA, 5);
		double expected=0;
		assertEquals(expected, b1.getIngredientQuant(Ingredients.LEMON_TEA));
	}
}

