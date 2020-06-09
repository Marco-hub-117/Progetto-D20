package it.unipv.ingsw.d20.vendingmachine.model.beverage;
import java.util.concurrent.TimeUnit;

public class Beverage{
	
	public Beverage() throws InterruptedException  {	
		TimeUnit.SECONDS.sleep(3); //attesa di 3 secondi, può lanciare una eccezione che va gestita nella vending machine
	}

	
	
}
