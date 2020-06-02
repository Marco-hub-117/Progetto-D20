package it.unipv.ingsw.d20.persistence.sale;


public class SalePOJO {
	
	private String idSale;
	private String idVending;
	private String idBeverage;
	private String date; // nel formato yyyy-mm-dd
	
	public SalePOJO(String idSale, String idVending, String idBeverage, String date) {
		this.idSale = idSale;
		this.idVending = idVending;
		this.idBeverage = idBeverage;
		this.date = date;
	}

	public String getIdSale() {
		return idSale;
	}

	public void setIdSale(String idSale) {
		this.idSale = idSale;
	}

	public String getIdVending() {
		return idVending;
	}

	public void setIdVending(String idVending) {
		this.idVending = idVending;
	}

	public String getIdBeverage() {
		return idBeverage;
	}

	public void setIdBeverage(String idBeverage) {
		this.idBeverage = idBeverage;
	}
	
	/**
	 * La data deve essere nel formato yyyy-mm-dd
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * La data è nel formato yyyy-mm-dd
	 * @return
	 */
	public String getDate() {
		return date;
	}
	
	
}
