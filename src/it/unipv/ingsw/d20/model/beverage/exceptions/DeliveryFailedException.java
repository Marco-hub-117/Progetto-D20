package it.unipv.ingsw.d20.model.beverage.exceptions;

@SuppressWarnings("serial")
public class DeliveryFailedException extends Exception {
	
	public DeliveryFailedException() {
		super("Delivery failed");
	}

}
