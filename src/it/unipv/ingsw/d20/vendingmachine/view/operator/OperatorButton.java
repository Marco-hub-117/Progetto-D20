package it.unipv.ingsw.d20.vendingmachine.view.operator;

import javax.swing.JButton;

/**
 * Pulsante personalizzato che viene premuto dall'operatore.
 * Gli vengono assegnati l'ID del tank annesso e la sua posizione.
 *
 */
@SuppressWarnings("serial")
public class OperatorButton extends JButton {

	private int position;
	private double value;
	private String tankId;
	
	public OperatorButton(int position, String text, String tankId) {
		super(text);

		this.position = position; 
		this.tankId = tankId;
	}

	public double getValue() {
		return value;
	}

	public int getPosition() {
		return position;
	}

	public String getTankId() {
		return tankId;
	}
	
	public void setIdTank(String tankId) {
		this.tankId = tankId;
	}
	
}
