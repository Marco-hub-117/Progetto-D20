package it.unipv.ingsw.d20.vendingmachine.view.operator;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class OperatorButton extends JButton {

	private int pos;
	private double value;
	private String idTank;
	
	public OperatorButton(int pos, String string, String idTank) {
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
