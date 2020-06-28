package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unipv.ingsw.d20.util.Preferences;

/**
 * Pannello che contiene il display della macchinetta e il tastierino numerico.
 *
 */
@SuppressWarnings("serial")
public class CodePanel extends JPanel {
	
	private JLabel display;
	private CustomerButton[] codeButtons = new CustomerButton[12];
	
	public CodePanel() {
		setLayout(new BorderLayout());	
		
		JPanel displayPanel = new JPanel(); displayPanel.setLayout(new BorderLayout()); displayPanel.setBackground(Color.BLACK);
		display = new JLabel(" E0,00"); //alla creazione imposto il credito nullo sul display
		display.setBackground(Color.BLACK); display.setForeground(Color.WHITE); display.setHorizontalAlignment(SwingConstants.RIGHT);
		displayPanel.add(display, BorderLayout.CENTER);
		
		display.setFont(Preferences.getDisplayFont());
		
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new GridLayout(4, 3));
		
		initializeButtons();

		for (int i = 0; i < codeButtons.length; i++) {
			numberPanel.add(codeButtons[i]);
			codeButtons[i].setBackground(Color.DARK_GRAY);
		}
		
		
		add(displayPanel, BorderLayout.NORTH); add(numberPanel, BorderLayout.CENTER);
	}
	
	private void initializeButtons() {
		for (int i = 0; i < 9; i++) 
			codeButtons[i] = new CustomerButton(String.valueOf(i+1), i+1);
		codeButtons[9] = new CustomerButton("Canc", -1);
		codeButtons[10] = new CustomerButton("0", 0); 
		codeButtons[11] = new CustomerButton("Ok", 10);	
	} 
	
	public CustomerButton[] getCodeButtons() {
		return codeButtons;
	}

	public String getDisplay() {
		return display.getText().substring(1); //rimuove lo spazio iniziale dal display e lo ritorna
	}

	public void setDisplay(String creditToString) {
		display.setText(" " + creditToString); //imposta a display il parametro preceduto da uno spazio (utile per la grafica)
	}

}
