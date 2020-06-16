package it.unipv.ingsw.d20.vendingmachine.view.operator;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class OperatorButton extends JButton {

	private int pos;
	private double value;
	private String tankId;
	
	public OperatorButton(int pos, String text, String tankId) {
		super(text);

		this.pos = pos; 
		this.tankId = tankId;
	}

	public double getValue() {
		return value;
	}

	public int getPos() {
		return pos;
	}

	public String getTankId() {
		return tankId;
	}
	
	public void setIdTank(String idTank) {
		this.tankId = idTank;
	}
	
}
