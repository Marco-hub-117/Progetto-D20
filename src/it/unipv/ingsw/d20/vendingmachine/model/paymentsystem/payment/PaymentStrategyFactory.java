package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies.*;

public class PaymentStrategyFactory {
	private static PaymentStrategyFactory factory;
	private static CashPaymentStrategy cash;
	private static KeyPaymentStrategy key;
	private static AppPaymentStrategy app;
	
	private PaymentStrategyFactory() {
		cash=new CashPaymentStrategy();
		key=new KeyPaymentStrategy();
		app=new AppPaymentStrategy();
	}
	
	//andrebbe invocato nel costruttore della vending
	public static PaymentStrategyFactory getPaymentStrategyFactory() {
		if (factory==null) {
			factory=new PaymentStrategyFactory();
		}
		
		return factory;	
	}
	
	//si potrebbe fare enumerazione invece di stringhe
	public static IPaymentStrategy getStrategy(String strategy) {
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
