package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pannello che contiene tutti i tasti relativi al credito: ottenimento 
 * del resto, inserimento ed espulsione di una chiavetta e inserimento dei
 * diversi tipi di monete.
 *
 */
@SuppressWarnings("serial")
public class CreditPanel extends JPanel {
	
	private CustomerButton[] cashButtons = new CustomerButton[7];
	private JButton insertKeyButton;
	private JButton ejectKeyButton;
	
	public CreditPanel() {
		setLayout(new GridLayout(4, 1));
		setBackground(Color.DARK_GRAY);
		
		JPanel restPanel = new JPanel(); restPanel.setLayout(new BorderLayout()); restPanel.setBackground(Color.DARK_GRAY);
		cashButtons[0] = new CustomerButton("Resto", -1); 
		cashButtons[0].setFont(cashButtons[0].getFont().deriveFont(Font.PLAIN, 25));
		cashButtons[0].setBackground(Color.DARK_GRAY);
		restPanel.add(cashButtons[0], BorderLayout.CENTER); 
		restPanel.add(new JLabel(" "), BorderLayout.SOUTH); //label vuota utile per la grafica
		
		
		JPanel keyPanel = new JPanel(); keyPanel.setLayout(new GridLayout(1, 2));
		insertKeyButton = new JButton("Inserisci chiavetta"); 
		insertKeyButton.setFont(insertKeyButton.getFont().deriveFont(Font.PLAIN, 25)); 
		insertKeyButton.setBackground(Color.DARK_GRAY);
		ejectKeyButton = new JButton("Espelli chiavetta"); 
		ejectKeyButton.setFont(ejectKeyButton.getFont().deriveFont(Font.PLAIN, 25));
		ejectKeyButton.setBackground(Color.DARK_GRAY);
		keyPanel.add(insertKeyButton); keyPanel.add(ejectKeyButton);
		
		
		JPanel coinPanel1 = new JPanel(); coinPanel1.setLayout(new GridLayout(1, 3));
		JPanel coinPanel2 = new JPanel(); coinPanel2.setLayout(new GridLayout(1, 3));
		
		initializeButtons();
		
		for (int i = 1; i < cashButtons.length; i++) { //aggiunge tutti i pulsanti per l'inserimento di monete nel posto giusto
			if (i < (cashButtons.length + 1) / 2) {
				coinPanel1.add(cashButtons[i]);
			} else {
				coinPanel2.add(cashButtons[i]);
			}
			cashButtons[i].setBackground(Color.DARK_GRAY);
		}		
		
		
		add(restPanel); add(keyPanel); add(coinPanel1); add(coinPanel2);
	}

	private void initializeButtons() {
		cashButtons[1] = new CustomerButton("€2,00", 2.0);
		cashButtons[2] = new CustomerButton("€1,00", 1.0);
		cashButtons[3] = new CustomerButton("€0,50", 0.5);
		cashButtons[4] = new CustomerButton("€0,20", 0.2);
		cashButtons[5] = new CustomerButton("€0,10", 0.1);
		cashButtons[6] = new CustomerButton("€0,05", 0.05);		
	}

	public CustomerButton[] getCashButtons() {
		return cashButtons;
	}

	public JButton getInsertKeyButton() {
		return insertKeyButton;
	}

	public JButton getEjectKeyButton() {
		return ejectKeyButton;
	}

}
