package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;
/**
 * Test JUnit 5 sulla classe Sale
 * 
 * */
class SaleTest {
	
	private String vendingMachineID = "TEST_ID";
	/**
	 * Il test controlla il funzionamento della classe Sale con valori specificati
	 * @throws InsufficientCreditException
	 * */
	@Test
	void test1() throws InsufficientCreditException {
		BeverageDescription b = new BeverageDescription("001","caffe", 1.0);
		double credit=1;
		Sale sale = new Sale(vendingMachineID, b, credit);
		double real=sale.getRest();
		double expected=0;
		assertEquals(expected, real);
	}
	/**
	 * Il test controlla il funzionamento della classe Sale con valori specificati
	 * @throws InsufficientCreditException
	 * */
	@Test
	void test2() throws InsufficientCreditException {
		BeverageDescription b = new BeverageDescription("001","caffe", 2.1);
		double credit=100;
		Sale sale = new Sale(vendingMachineID, b, credit);
		double real=sale.getRest();
		double expected=97.9;
		assertEquals(expected, real);
	}
}