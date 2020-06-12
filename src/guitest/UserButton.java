package guitest;

import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class UserButton extends JButton {
	
	private double coinValue;
	
	public UserButton(String text, double value) {
		super(text);
		coinValue = value;
		
		setFont(getFont().deriveFont(Font.PLAIN, 22));
	}
	
	public double getValue() {
		return coinValue;
	}

}
