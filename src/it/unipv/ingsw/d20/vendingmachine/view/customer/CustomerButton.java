package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.Font;

import javax.swing.JButton;

/**
 * Pulsante personalizzato che viene premuto dal cliente.
 *
 *
 */
@SuppressWarnings("serial")
public class CustomerButton extends JButton {
	
	private double coinValue;
	/**
	 * Costruttore della classe CustomerButton
	 * Al pulsante e' associato un valore in modo da riconoscerne il ruolo.
	 * @param text scritta sul pulsante
	 * @param value valore del pulsante
	 *
	 */
	public CustomerButton(String text, double value) {
		super(text);
		coinValue = value;
		setFont(getFont().deriveFont(Font.PLAIN, 22));
	}
	
	public double getValue() {
		return coinValue;
	}

}
