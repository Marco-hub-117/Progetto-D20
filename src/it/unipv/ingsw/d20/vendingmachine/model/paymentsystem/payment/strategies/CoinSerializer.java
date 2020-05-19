package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import java.util.HashMap;
import java.util.Map;

public class CoinSerializer {
	private final static Map<Double, String> coder;
	    static
	    {
	        coder = new HashMap<>();
	        coder.put(0.1, "CENTS_10");
	        coder.put(0.2, "CENTS_20");
	        coder.put(0.5, "CENTS_50");
	        coder.put(1.0, "EUROS_1");
	        coder.put(2.0, "EUROS_2");
	    }
	
	
	public static String associateSerialString(double value) {
		return coder.get(value);
	}
	
	public static double convertToDouble(String serial) {
		for (Map.Entry<Double, String> entry: coder.entrySet()) {
			if (((entry.getValue()).equals(serial))) { 
				return entry.getKey();
			}
		}
		return 0;
	}

}
