package it.unipv.ingsw.d20.paymentsystem;

/**
 * 
 * @author 
 * @author Nicolò Fasulo <fasulo.nicol@gmail.com>
 *
 */
public class Payment {
	
	private double amount;
	private double price;
	
	public Payment(double amount, double price) {
		this.amount = amount;
		this.price = price;
	}
	
	public double getChange() {
		return -1;
	}

	public boolean isAccepted() {
		// TODO Auto-generated method stub
		return false;
	}

}
