package it.unipv.ingsw.d20.paymentsystem.payment;

/**
 * 
 * @author 
 * @author Luigi Zaccaria Del Pio
 *
 */
public class Payment {
	
	private double amount;
	private double price;
	private boolean isAccepted;
	private double change;
	
	public Payment(double amount, double price) throws PaymentNotValidException {
		this.amount = amount;
		this.price = price;
		
		if (check())
			setChange(amount, price);
		else 
			throw new PaymentNotValidException();
			
	}
	
	public boolean check() {
		if (amount>=price) {
			isAccepted=true;
			return true;
		}
		else 
			return false;
	}
	
	public boolean isAccepted() {
		return isAccepted;
	}
	
	public double getChange() {
		return change;
	}

	public void setChange(double amount, double price) {
		this.change = amount-price;
	}
	
	

	

}
