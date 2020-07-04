package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;
/**
 * Test JUnit 5 sulla classe Sale.
 * Un test per ognuna delle 6 classi di equivalenza individuate:
 * price>credit / price=credit /price<credit / credit=0 price!=0 / credit!=0 price=0 / credit=0 price=0
 * 
 **/
class SaleTest {
	
	private String vendingMachineID = "TEST_ID";
	
	private Sale init (double credit, double price) throws InsufficientCreditException {
		BeverageDescription b = new BeverageDescription("001","caffe", price);
		Sale sale = new Sale(vendingMachineID, b, credit);
		return sale;
	}
	
	/**
	 * Il test controlla il funzionamento della classe Sale con price>credit.
	 * @throws InsufficientCreditException
	 * */
	@Test
	void testCreditSmaller() throws InsufficientCreditException {
		assertThrows(InsufficientCreditException.class, ()->init(0.5, 1));
	}
	
	/**
	 * Il test controlla il funzionamento della classe Sale con price=credit.
	 * @throws InsufficientCreditException
	 * */
	@Test
	void testExactCredit() throws InsufficientCreditException {
		Sale sale=init(1, 1);
		double real=sale.getRest();
		double expected=0;
		assertEquals(expected, real);
	}
	
	/**
	 * Il test controlla il funzionamento della classe Sale con price<credit.
	 * @throws InsufficientCreditException
	 * */
	@Test
	void testCreditBigger() throws InsufficientCreditException {
		Sale sale=init(100, 1);
		double real=sale.getRest();
		double expected=99;
		assertEquals(expected, real);
	}
	
	/**
	 * Il test controlla il funzionamento della classe Sale con credit=0 e price!=0.
	 * @throws InsufficientCreditException
	 * */
	@Test
	void testCreditZero() throws InsufficientCreditException {
		assertThrows(InsufficientCreditException.class, ()->init(0, 1));
	}
	
	/**
	 * Il test controlla il funzionamento della classe Sale con credit!=0 e price=0 (es. distribuzione gratuita).
	 * @throws InsufficientCreditException
	 * */
	@Test
	void testPriceZero() throws InsufficientCreditException {
		Sale sale=init(1, 0);
		double real=sale.getRest();
		double expected=1;
		assertEquals(expected, real);
	}
	
	/**
	 * Il test controlla il funzionamento della classe Sale con credit=0 e price=0.
	 * @throws InsufficientCreditException
	 * */
	@Test
	void testAllZero() throws InsufficientCreditException {
		Sale sale=init(0, 0);
		double real=sale.getRest();
		double expected=0;
		assertEquals(expected, real);
	}
}