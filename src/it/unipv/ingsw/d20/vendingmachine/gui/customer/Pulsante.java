package it.unipv.ingsw.d20.vendingmachine.gui.customer;

import javax.swing.JButton;

public class Pulsante extends JButton {
	private int pos;
	private double value;
	private String idTank;
	
	public Pulsante(double value, int pos, String string) {
		super(string);
		super.setSize(10, 60);
		this.value=value;
		this.pos=pos; 
	}
	
	public Pulsante(int pos, String string, String idTank) {
		super(string);
		super.setSize(10, 60);
		this.pos=pos; 
		this.idTank=idTank;
	}

	public double getValue() {
		return value;
	}

	public int getPos() {
		return pos;
	}

	public String getIdTank() {
		return idTank;
	}
	public void setIdTank(String idTank) {
		this.idTank = idTank;
	}

}
