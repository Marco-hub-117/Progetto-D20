package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;

class CashContainerTest {
	int a=10;
	int b=10;
	int c=10;
	int d=10;
	int e=10;
	int f=10;
	private int[] coinValue = {a, b, c, d, e, f};
	private final CashContainer cc=new CashContainer(coinValue);
	@Test
	void testTotalAmount() {
		double real=cc.getTotalAmount();
		double expected=a*0.05 + b*0.1 + c*0.2 + d*0.5 + e + f*2;
		assertEquals(expected, real);
	}
	@Test
	void testRest() throws InsufficientCashForRestException {
		double credit=10.35;
		cc.dispenseRest(credit);
		double real=cc.getTotalAmount();
		double expected=(a*0.05 + b*0.1 + c*0.2 + d*0.5 + e + f*2)-credit;
		assertEquals(expected, real);
	}
}
