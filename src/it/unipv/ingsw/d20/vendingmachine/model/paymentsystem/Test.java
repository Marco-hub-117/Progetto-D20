package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

public class Test {

	public static void main(String[] args) {
		CashHandler cc = new CashHandler(10, 10, 10, 10, 10, 10);
		
		System.out.println(cc.refreshTotalAmount());
		
		try {
			cc.addCoin(0.50);
		} catch (InvalidPaymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cc.refreshTotalAmount());
		
		try {
			cc.dispenseRest(5);
			cc.dispenseRest(5);
			cc.dispenseRest(5);
		} catch (InsufficientCashForRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cc.dispenseRest(50);
		} catch (InsufficientCashForRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cc.refreshTotalAmount());

	}

}
