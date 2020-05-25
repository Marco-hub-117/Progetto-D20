package it.unipv.ingsw.d20.gui;

import javax.swing.JButton;

public class Pulsante extends JButton {
	private int pos;
	private int value;
	
	public Pulsante(int value, int pos, String string) {
		super(string);
		this.value=value;
		this.pos=pos; 
	}

	public int getValue() {
		return value;
	}

	
	public int getPos() {
		return pos;
	}

	
}
