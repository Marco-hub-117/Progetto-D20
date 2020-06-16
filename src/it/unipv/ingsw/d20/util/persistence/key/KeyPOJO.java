package it.unipv.ingsw.d20.util.persistence.key;

public class KeyPOJO {
	
	private String serialCode;
	private double credit;
		
	public KeyPOJO(String serialCode, double credit) {
		this.serialCode = serialCode;
		this.credit = credit;
	}
	
	public String getSerialCode() {
		return serialCode;
	}
	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}

}
