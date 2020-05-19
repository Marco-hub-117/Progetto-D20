package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import java.util.HashMap;
import java.util.Map;

public class CoinSerial {
	private final static Map<Double, Character> coder;
	    static
	    {
	        coder = new HashMap<>();
	        coder.put(0.1, 'a');
	        coder.put(0.2, 'b');
	        coder.put(0.5, 'c');
	        coder.put(1.0, 'd');
	        coder.put(2.0, 'e');
	    }
	
	
	public static char associateSerialCharacter(double value) {
		return coder.get(value);
	}
	
	public static double convertToDouble(char ch) {
		
		for (Map.Entry<Double, Character> entry: coder.entrySet()) {
			if ((char)entry.getValue()==ch) { //forse cast non necessario
				return entry.getKey();
			}
		}
		return 0;
	}

}
