package it.unipv.ingsw.d20.gui;

import javax.swing.JButton;

public class Pulsante extends JButton {
	private int pos;
	private double value;
	
	public Pulsante(double value, int pos, String string) {
		super(string);
		this.value=value;
		this.pos=pos; 
	}

	public double getValue() {
		return value;
	}

	
	public int getPos() {
		return pos;
	}

	
}
