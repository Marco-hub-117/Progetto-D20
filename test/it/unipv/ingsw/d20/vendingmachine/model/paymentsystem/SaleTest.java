package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCreditException;

class SaleTest {

	@Test
	void test() throws InsufficientCreditException {
		BeverageDescription b = new BeverageDescription("001","caffe", 1.0);
		double credit=1;
		Sale sale = new Sale(b, credit);
	}

}
