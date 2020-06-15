package it.unipv.ingsw.d20.vendingmachine.gui.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class KeyboardPanel extends JPanel {
	
	private CodePanel codePanel;
	private CreditPanel creditPanel;
	
	public KeyboardPanel() {
		setLayout(new GridLayout(2, 1));
		
		codePanel = new CodePanel();		
		creditPanel = new CreditPanel(); 
		
		add(codePanel); add(creditPanel);
	}
	
	public String getDisplay() {
		return codePanel.getDisplay();
	}

	public void setDisplay(String creditToString) {
		codePanel.setDisplay(creditToString);
	}
	
	public CustomerButton[] getCodeButtons() {
		return codePanel.getCodeButtons();
	}

	public CustomerButton[] getCashButtons() {
		return creditPanel.getCashButtons();
	}

	public JButton getInsertKeyButton() {
		return creditPanel.getInsertKeyButton();
	}

	public JButton getEjectKeyButton() {
		return creditPanel.getEjectKeyButton();
	}

}
