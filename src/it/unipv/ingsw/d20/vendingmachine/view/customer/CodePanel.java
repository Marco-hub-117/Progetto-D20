package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CodePanel extends JPanel {
	
	private JLabel display;
	private CustomerButton[] codeButtons = new CustomerButton[12];
	
	public CodePanel() {
		setLayout(new BorderLayout());	
		
		JPanel displayPanel = new JPanel(); displayPanel.setLayout(new BorderLayout()); displayPanel.setBackground(Color.BLACK);
		display = new JLabel(" E0,00"); 
		display.setBackground(Color.BLACK); display.setForeground(Color.WHITE); display.setHorizontalAlignment(SwingConstants.RIGHT);
		displayPanel.add(display, BorderLayout.CENTER);
		
		String filename = "res/font/DS-DIGIB.TTF";
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		
		display.setFont(font); display.setFont(display.getFont().deriveFont(Font.BOLD, 50));
		
		
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new GridLayout(4, 3));
		
		initializeButtons();

		for (int i = 0; i < codeButtons.length; i++) {
			numberPanel.add(codeButtons[i]);
		}
		
		
		add(displayPanel, BorderLayout.NORTH); add(numberPanel, BorderLayout.CENTER);
	}
	
	private void initializeButtons() {
		codeButtons[0] = new CustomerButton("1", 1);
		codeButtons[1] = new CustomerButton("2", 2);
		codeButtons[2] = new CustomerButton("3", 3);
		codeButtons[3] = new CustomerButton("4", 4);
		codeButtons[4] = new CustomerButton("5", 5);
		codeButtons[5] = new CustomerButton("6", 5);		
		codeButtons[6] = new CustomerButton("7", 7);
		codeButtons[7] = new CustomerButton("8", 8);
		codeButtons[8] = new CustomerButton("9", 9);
		codeButtons[9] = new CustomerButton("Canc", -1);
		codeButtons[10] = new CustomerButton("0", 0);
		codeButtons[11] = new CustomerButton("Ok", 10);		
	}
	
	public CustomerButton[] getCodeButtons() {
		return codeButtons;
	}

	public String getDisplay() {
		return display.getText().substring(1);
	}

	public void setDisplay(String creditToString) {
		display.setText(" " + creditToString);
	}

}
