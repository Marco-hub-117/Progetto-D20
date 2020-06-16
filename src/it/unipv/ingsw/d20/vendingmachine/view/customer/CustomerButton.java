package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class CustomerButton extends JButton {
	
	private double coinValue;
	
	public CustomerButton(String text, double value) {
		super(text);
		coinValue = value;
		
		setFont(getFont().deriveFont(Font.PLAIN, 22));
	}
	
	public double getValue() {
		return coinValue;
	}

}
