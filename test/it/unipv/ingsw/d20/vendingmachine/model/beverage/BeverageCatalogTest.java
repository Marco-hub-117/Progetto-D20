package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test JUnit 5 sulla classe BeverageDescription. Si testano i metodi setIngredient e getBeverageDesc
 * a seconda delle classi di equivalenza individuate.
 * 
 * */
public class BeverageCatalogTest {
	private BeverageCatalog cat;
	private Ingredients i1;
	private Ingredients i2;
	private BeverageDescription b1;
	private Ingredients i3;
	private Ingredients i4;
	private BeverageDescription b2;
	
	private void init() {
		cat=new BeverageCatalog();
		
		i1=Ingredients.CHOCOLATE;
		i2=Ingredients.MILK;
		b1=new BeverageDescription("CM1","chocolate milk",2.0);
		b1.addIngredient(i1, 3);
		b1.addIngredient(i2, 5);
		
		i3=Ingredients.LEMON_TEA;
		i4=Ingredients.ORANGE_JUICE;
		b2=new BeverageDescription("J1","juice", 1.0);
		b2.addIngredient(i3);
		b2.addIngredient(i4, 2);
		
		cat.addBeverageDescription(b1);
		cat.addBeverageDescription(b2);
	}
	
	
	//TEST su setIngredient
	/**
	* Test su setIngredient con quantità > 0.
	* 
	* */
	@Test
	void testSetIngredient() {
		init();
		cat.setIngredient("J1", Ingredients.LEMON_TEA, 4.0);
		double expected=4.0;
		BeverageDescription bev=cat.getCatalog().get("J1");
		double real=bev.getIngredients().get(Ingredients.LEMON_TEA);
		assertEquals(expected, real);
	}
	
	/**
	 * Test su setIngredient con quantità = 0.
	 * 
	 * */
	@Test
	void testSetIngredientZero() {
		init();
		cat.setIngredient("CM1", Ingredients.CHOCOLATE, 0.0);
		double expected=0;
		BeverageDescription bev=cat.getCatalog().get("CM1");
		double real=bev.getIngredients().get(Ingredients.CHOCOLATE);
		assertEquals(expected, real);
	}
		
	/**
	 * Test su setIngredient con quantità < 0.
	 * 
	 * */
	@Test
	void testSetIngredientNeg() {
		init();
		cat.setIngredient("CM1", Ingredients.CHOCOLATE, -8.0);
		double expected=0;
		double real=cat.getBeverageDesc("CM1").getIngredientQuant(Ingredients.CHOCOLATE);
		assertEquals(expected, real);
	}
	
	/**
	 * Test su setIngredient con bevanda non presente.
	 * 
	 * */
	@Test
	void testSetIngredientAbsentBev() {
		init();
		cat.setIngredient("CM2", Ingredients.CHOCOLATE, 2.0);
		assertEquals(null, cat.getCatalog().get("CM2"));
	}
	
	/**
	 * Test su setIngredient con ingrediente non presente.
	 * 
	 * */
	@Test
	void testSetIngredientAbsentIngr() {
		init();
		cat.setIngredient("CM1", Ingredients.SPARKLING_WATER, 1.0);
		double expected = 0;
		double real = cat.getBeverageDesc("CM1").getIngredientQuant(Ingredients.SPARKLING_WATER);
		assertEquals(expected, real);
	}
	
	
	//TEST su getBeverageDesc
	/**
	 * Test su getBeverageDesc con bevanda presente.
	 * 
	 * */
	@Test
	void testGetBeverageDesc() {
		init();
		double expected=1.0;
		double real=cat.getBeverageDesc("J1").getPrice();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su getBeverageDesc con bevanda assente
	 * 
	 * */
	@Test
	void testGetBeverageDescAbsent() {
		init();
		assertEquals(null, cat.getBeverageDesc("INVENTATO"));
	}

}
