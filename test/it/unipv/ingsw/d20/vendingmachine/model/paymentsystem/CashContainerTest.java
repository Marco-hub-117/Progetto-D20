package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidCoinException;
/**
 * Test JUnit 5 sulla classe CashCointainer. Si testano i metodi getTotalAmount, addCoin, 
 * withdrawAmount e dispenseRest nelle classi di equivalenza individuate.
 * 
 * */
class CashContainerTest {
	
	private int a;
	private int b;
	private int c;
	private int d;
	private int e;
	private int f;
	
	/**
	 * Inizializzazione con tutti i valori != 0.
	 * 
	 */
	private CashContainer init (){
		a=20; b=20; c=20; d=20; e=20; f=20;
		int[] coinValue = {a, b, c, d, e, f};
		CashContainer cc=new CashContainer(coinValue);
		return cc;
	}
	
	/**
	 * Inizializzazione con tutti i valori = 0.
	 * 
	 */
	private CashContainer initZero (){
		a=0; b=0; c=0; d=0; e=0; f=0;
		int[] coinValue = {a, b, c, d, e, f};
		CashContainer cc=new CashContainer(coinValue);
		return cc;
	}
	
	/**
	 * Inizializzazione con alcuni valori = 0, altri != 0
	 * 
	 */
	private CashContainer initMixed (){
		a=10; b=0; c=10; d=0; e=10; f=0;
		int[] coinValue = {a, b, c, d, e, f};
		CashContainer cc=new CashContainer(coinValue);
		return cc;
	}
	
	//TEST SU getTotalAmount
	/**
	 * Test su getTotalAmount con i numeri di monete per ciascun tipo != 0
	 * 
	 */
	@Test
	void testTotalAmount() {
		CashContainer cc=init();
		double real=cc.getTotalAmount();
		double expected=a*0.05 + b*0.1 + c*0.2 + d*0.5 + e + f*2;
		assertEquals(expected, real);
	}
	
	/**
	 * Test su getTotalAmount con i numeri di monete per ciascun tipo = 0
	 * 
	 */
	@Test
	void testTotalAmountZeroes() {
		CashContainer cc=initZero();
		double real=cc.getTotalAmount();
		double expected=0;
		assertEquals(expected, real);
	}
	
	/**
	 * Test su getTotalAmount con numeri di monete = e != da 0.
	 * 
	 */
	@Test
	void testTotalAmountMixed() {
		CashContainer cc=initMixed();
		double real=cc.getTotalAmount();
		double expected=a*0.05 + b*0.1 + c*0.2 + d*0.5 + e + f*2;
		assertEquals(expected, real);
	}
	
	
	//TEST SU addCoin
	/**
	 * Test su addCoin (e refreshTotalAmount()) con moneta esistente.
	 * 
	 */
	@Test
	void testAddCoin() {
		CashContainer cc=initMixed();
		double amount=cc.getTotalAmount();
		InvalidCoinException exc=new InvalidCoinException("Moneta non valida");
		assertDoesNotThrow(()->cc.addCoin(0.5), exc.getMessage()); //ATTENZIONE, POTREBBE FALLIRE PER VIA DELLA PROBABILITA' NON NULLA DI SIMULARE UNA MONETA ESISENTE MA NON VALIDA
		double expected=amount+0.5;
		assertEquals(expected, cc.getTotalAmount());
	}
	
	/**
	 * Test su addCoin (e refreshTotalAmount()) con moneta non esistente.
	 * 
	 */
	@Test
	void testAddCoinFalse() {
		CashContainer cc=initMixed();
		double amount=cc.getTotalAmount();
		assertThrows(ArrayIndexOutOfBoundsException.class, ()->cc.addCoin(0.3));
		double expected=amount;
		assertEquals(expected, cc.getTotalAmount());
	}
	
	
	//TEST SU withdrawAmount
	/**
	 * Test su withdrawAmount con numero di monete di ciascun tipo > MIN_COIN_NUMBER (=10).
	 * 
	 */
	@Test
	void testWithdrawAmount() {
		CashContainer cc=init();
		double amount=cc.getTotalAmount();
		double expected=amount - ((a-10)*0.05 + (b-10)*0.1 + (c-10)*0.2 + (d-10)*0.5 + (e-10)*1 + (f-10)*2);
		double real = cc.withdrawAmount();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su withdrawAmount con numero di monete di ciascun tipo <= MIN_COIN_NUMBER (=10).
	 * 
	 */
	@Test
	void testWithdrawAmountPoor() {
		CashContainer cc=initMixed();
		double expected=0;
		double real = cc.withdrawAmount();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su withdrawAmount con numero di monete di ciascun tipo = 0.
	 * 
	 */
	@Test
	void testWithdrawAmountZero() {
		CashContainer cc=initZero();
		double expected=0;
		double real = cc.withdrawAmount();
		assertEquals(expected, real);
	}
	
	
	//TEST SU dispenseRest
	/**
	 * Test su dispenseRest con totalAmount sufficiente.
	 * 
	 */
	@Test
	void testDispenseRest() {
		CashContainer cc=init();
		InsufficientCashForRestException exc=new InsufficientCashForRestException();
		double expected= cc.getTotalAmount()-10;
		assertDoesNotThrow(()->cc.dispenseRest(10), exc.getMessage());
		double real = cc.getTotalAmount();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su dispenseRest con totalAmount insufficiente.
	 * 
	 */
	@Test
	void testDispenseRestPoor() {
		CashContainer cc=initMixed();
		double expected= cc.getTotalAmount();
		assertThrows(InsufficientCashForRestException.class, ()->cc.dispenseRest(50));
		double real = cc.getTotalAmount();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su dispenseRest con totalAmount = 0.
	 * 
	 */
	@Test
	void testDispenseRestZero() {
		CashContainer cc=initZero();
		double expected= cc.getTotalAmount();
		assertThrows(InsufficientCashForRestException.class, ()->cc.dispenseRest(50));
		double real = cc.getTotalAmount();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su dispenseRest con credito da restituire = 0 e totalAmount != 0.
	 * 
	 */
	@Test
	void testDispenseRestNiceTry() {
		CashContainer cc=init();		
		double expected= cc.getTotalAmount();
		InsufficientCashForRestException exc=new InsufficientCashForRestException();
		assertDoesNotThrow(()->cc.dispenseRest(0), exc.getMessage());
		double real = cc.getTotalAmount();
		assertEquals(expected, real);
	}
	
	/**
	 * Test su dispenseRest con credito da restituire = 0 e totalAmount = 0.
	 * 
	 */
	@Test
	void testDispenseRestNiceTryZero() {
		CashContainer cc=initZero();		
		double expected= cc.getTotalAmount();
		InsufficientCashForRestException exc=new InsufficientCashForRestException();
		assertDoesNotThrow(()->cc.dispenseRest(0), exc.getMessage());
		double real = cc.getTotalAmount();
		assertEquals(expected, real);
	}
	
}
