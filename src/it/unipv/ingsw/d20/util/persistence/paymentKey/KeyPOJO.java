package it.unipv.ingsw.d20.util.persistence.paymentKey;

public class KeyPOJO {
	
	private int serialCode;
	private double credit;
		
	public KeyPOJO(int serialCode, double credit) {
		this.serialCode = serialCode;
		this.credit = credit;
	}
	
	public int getSerialCode() {
		return serialCode;
	}
	public void setSerialCode(int serialCode) {
		this.serialCode = serialCode;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "KeyPOJO [serialCode=" + serialCode + ", credit=" + credit + "]";
	}
	
	

}
