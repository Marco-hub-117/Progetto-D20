package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies.*;

public class CreditStrategyFactory {
	private static CreditStrategyFactory factory;
	private static CashCreditStrategy cash;
	private static KeyCreditStrategy key;
	private static AppCreditStrategy app;
	
	private CreditStrategyFactory() {
		cash=new CashCreditStrategy();
		key=new KeyCreditStrategy();
		app=new AppCreditStrategy();
	}
	
	//andrebbe invocato nel costruttore della vending
	public static CreditStrategyFactory getPaymentStrategyFactory() {
		if (factory==null) {
			factory=new CreditStrategyFactory();
		}
		
		return factory;	
	}
	
	//il controllore della gui prende quella che vuole, passando la stringa apposita, e la passa alla sale
	public static ICreditStrategy getStrategy(String strategy) {
		switch(strategy) {
		case "CASH":
			return cash;
		case "KEY":
			return key;
		case "APP":
			return app;
		}
		return null;
	}
	
}
