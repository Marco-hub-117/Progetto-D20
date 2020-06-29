package it.unipv.ingsw.d20.util.persistence.sale;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe che replica la Table "Sale" del database.
 * La chiave primaria � composta dagli attributi idVending e Date.
 * Data nel formato yyyy/MM/dd hh:mm:ss
 * Gli attributi sono nel seguente ordine: idVending, idBeverage, Date.
 */

public class SalePOJO {
	
	private String idVending;
	private String idBeverage;
	private String date; // Data nel formato yyyy/MM/dd hh:mm:ss
	
	/**
	 * Costruttore con la date nel formato String
	 * @param idVending id della vending machine
	 * @param idBeverage id della bevanda
	 * @param date passata nel formato di Stringa
	 */
	public SalePOJO(String idVending, String idBeverage, String date) {
		this.idVending = idVending;
		this.idBeverage = idBeverage;
		this.date = date;	
	}
	
	/**
	 * Costruttore con la date nel formato java.util.Date
	 * @param idVending id della vending machine
	 * @param idBeverage id della bevanda
	 * @param date formato java.util.Date
	 */
	public SalePOJO(String idVending, String idBeverage, Date date) {
		this.idVending = idVending;
		this.idBeverage = idBeverage;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.date = sdf.format(date);	
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SalePOJO [idVending=" + idVending + ", idBeverage=" + idBeverage + ", date=" + date + "]";
	}
	
	
	
	
	
}
