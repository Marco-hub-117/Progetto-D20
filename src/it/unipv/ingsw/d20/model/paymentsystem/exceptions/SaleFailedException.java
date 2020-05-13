package it.unipv.ingsw.d20.model.paymentsystem.exceptions;

/**
 * @author Luigi Zaccaria Del Pio
 *
 */
@SuppressWarnings("serial")
public class SaleFailedException extends Exception {

	public SaleFailedException() {
		super("Sale failed");
	}

}
