package it.unipv.ingsw.d20.util.persistence;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyPOJO;

public class TestPaymentKey {

	public static void main(String[] args) {
		
		RdbOperations prova = new RdbOperations();
		
		KeyPOJO testKey1 = prova.getKey("15");
		System.out.println(testKey1);
		
		prova.updateKeyCredit("15", 5.0);
		
		KeyPOJO testKey2 = prova.getKey("15");
		System.out.println(testKey2);
		
		KeyPOJO newKeyTest = new KeyPOJO(30,10.0);
		prova.addKey(newKeyTest);
		System.out.println(prova.getKey("30"));
		
		System.out.println(prova.getKeyCredit("30"));
		
		ArrayList<KeyPOJO> allKeys = prova.getAllKeys();
		
		System.out.println("STAMPA 1");
		for (KeyPOJO stampa: allKeys) {
			System.out.println(stampa);
		}
		
		prova.deactivateKey("30");
		
		allKeys = prova.getAllKeys();
		
		System.out.println("STAMPA 2");
		for (KeyPOJO stampa: allKeys) {
			System.out.println(stampa);
		}

	}

}
