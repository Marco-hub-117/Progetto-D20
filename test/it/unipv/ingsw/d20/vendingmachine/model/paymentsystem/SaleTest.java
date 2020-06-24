package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;

class SaleTest {
	
	@Test
	void test1() throws InsufficientCreditException {
		BeverageDescription b = new BeverageDescription("001","caffe", 1.0);
		double credit=1;
		Sale sale = new Sale(b, credit);
		double real=sale.getRest();
		double expected=0;
		assertEquals(expected, real);
	}
	@Test
	void test2() throws InsufficientCreditException {
		BeverageDescription b = new BeverageDescription("001","caffe", 2.1);
		double credit=100;
		Sale sale = new Sale(b, credit);
		double real=sale.getRest();
		double expected=97.9;
		assertEquals(expected, real);
	}
}