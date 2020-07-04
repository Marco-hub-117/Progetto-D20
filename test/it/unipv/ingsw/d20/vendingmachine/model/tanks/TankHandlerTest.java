package it.unipv.ingsw.d20.vendingmachine.model.tanks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;

/**
 * Test JUnit 5 sulla classe TankHandler. Si testano i metodi isAvailable e modifyTankSettings,
 * a seconda delle classi di equivalenza individuate.
 * 
 * */
class TankHandlerTest {
	private HashMap<Ingredients,Tank> tankList=new HashMap<Ingredients,Tank>();
	private Ingredients i1=Ingredients.CHOCOLATE;
	private Ingredients i2=Ingredients.COFFEE;
	private Ingredients i3=Ingredients.MILK;
	private Ingredients i4=Ingredients.WATER;
	private Ingredients i5=Ingredients.WATER;
	private Tank t1=new Tank(i1,50.05,35.0, 10);
	private Tank t2=new Tank(i2,75.41,40.0, 20);
	private Tank t3=new Tank(i3,100, 15, 15);
	private Tank t4=new Tank(i4,100, 20, 40);
	private Tank t5=new Tank(i5,0, 20, 40);
	private BeverageDescription b1=new BeverageDescription("a1","caffe macchiato",0.5);
	private TankHandler th;
	
	/**
	 * Metodo di supporto per inizializzare il test.
	 * 
	 * */
	private void init() {
		tankList.put(i1, t1);
		tankList.put(i2, t2);
		tankList.put(i3, t3);
		tankList.put(i4, t4);
		th=new TankHandler(tankList);
		b1.addIngredient(i2,1);
		b1.addIngredient(i3,0.5);
		b1.addIngredient(i4, 5.0);
	}
	
	/**
	 * Metodo di supporto per inizializzare il test con un tank vuoto tra quelli necessari alla bevanda.
	 * 
	 * */
	private void initVoidTank() {
		tankList.put(i1, t1);
		tankList.put(i2, t2);
		tankList.put(i3, t3);
		tankList.put(i4, t5); //tank vuoto
		th=new TankHandler(tankList);
		b1.addIngredient(i2, 1);
		b1.addIngredient(i3, 0.5);
		b1.addIngredient(i4, 5.0);
	}
	
	/**
	 * Metodo di supporto per inizializzare il test con un tank mancante tra quelli necessari alla bevanda.
	 * 
	 * */
	private void initMissingTank() {
		tankList.put(i1, t1);
		tankList.put(i2, t2);
		tankList.put(i3, t3);
		th=new TankHandler(tankList);
		b1.addIngredient(i2, 1);
		b1.addIngredient(i3, 0.5);
		b1.addIngredient(i4, 5.0);
	}
	
	/**
	 * Metodo di supporto per inizializzare il test con meno tank che ingredienti necessari.
	 * 
	 * */
	private void initTooFewTanks() {
		tankList.put(i2, t2);
		tankList.put(i3, t3);
		th=new TankHandler(tankList);
		b1.addIngredient(i2, 1);
		b1.addIngredient(i3, 0.5);
		b1.addIngredient(i4, 5.0);
	}
	
	
	//TEST SU isAvailable
	/**
	 * Il test prova il funzionamento del controllo sulla quantita' di ingredienti per erogare una bevanda.
	 * 
	 * */
	@Test
	void testIsAvailable() {
		init();
		boolean real=th.isAvailable(b1);
		assertTrue(real);
	}
	
	/**
	 * Il test prova il funzionamento del controllo sulla quantita' di ingredienti 
	 * per erogare una bevanda, con un tank vuoto tra quelli che servono.
	 * 
	 * */
	@Test
	void testIsAvailableFalse() {
		initVoidTank();
		boolean real=th.isAvailable(b1);
		assertTrue(!real);
	}
	
	/**
	 * Il test prova il funzionamento del controllo sulla quantita' di ingredienti 
	 * per erogare una bevanda, con un tank utile mancante.
	 * 
	 * */
	@Test
	void testIsAvailableMissingTank() {
		initMissingTank();
		boolean real=th.isAvailable(b1);
		assertTrue(!real);
	}
	
	/**
	 * Il test prova il funzionamento del controllo sulla quantita' di ingredienti 
	 * per erogare una bevanda, con un numero di tank minore del numero degli ingredienti necessari.
	 * 
	 * */
	@Test
	void testIsAvailableTooFewTanks() {
		initTooFewTanks();
		boolean real=th.isAvailable(b1);
		assertTrue(!real);
	}
	
	
	//TEST SU modifyTankSettings
	/**
	 * Test su modifyTankSettings con numero di temperature giusto.
	 * 
	 * */
	@Test
	void testModifyTankSettings() {
		init();
		String temps="0.23 0.3 2.0 0.6";
		th.modifyTankSettings(temps);
		
		StringBuilder real=new StringBuilder();
		for (Map.Entry<Ingredients, Tank> entry : th.getTankList().entrySet()) {
			real.append(entry.getValue().getTemperature()+" ");
		}
		assertEquals(temps, real.toString().trim());
	}
	
	/**
	 * Test su modifyTankSettings con più temperature che tank.
	 * 
	 * */
	@Test
	void testModifyTankSettingsLessTanks() {
		init();
		String temps="0.23 0.3 2.0 0.6 4.1";
		th.modifyTankSettings(temps);
		
		StringBuilder build=new StringBuilder();
		for (Map.Entry<Ingredients, Tank> entry : th.getTankList().entrySet()) {
			build.append(entry.getValue().getTemperature()+" ");
		}
		String expected=temps.substring(0, 16);
		String real=build.toString().trim();
		assertEquals(expected, real);
	}
	
	
	/**
	 * Test su modifyTankSettings con meno temperature che tank.
	 * 
	 * */
	@Test
	void testModifyTankSettingsMoreTanks() {
		init();
		String temps="0.23 0.3 2.0";
		th.modifyTankSettings(temps);
		
		StringBuilder build=new StringBuilder();
		for (Map.Entry<Ingredients, Tank> entry : th.getTankList().entrySet()) {
			build.append(entry.getValue().getTemperature()+" ");
		}
		String expected=temps.substring(0, 12)+" "+th.getTankTemp(i4);
		System.out.println(th.getTankTemp(i4)); 
		String real=build.toString().trim();
		assertEquals(expected, real); //fallisce solo se eseguiti tutti i test in gruppo, perchè?
	}

}
