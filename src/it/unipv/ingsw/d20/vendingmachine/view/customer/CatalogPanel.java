package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unipv.ingsw.d20.util.Preferences;

/**
 * Pannello che contiene il catalogo del distributore automatico e il pulsante
 * per poter entrare in modalita' operatore.
 *
 */
@SuppressWarnings("serial")
public class CatalogPanel extends JPanel {
	
	private CustomTextArea catalogTextArea;
	private JButton operatorButton;
	
	public CatalogPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		
		catalogTextArea = new CustomTextArea();
		catalogTextArea.setBackground(new Color(1, 1, 1, (float) 0.01));
		catalogTextArea.setFont(catalogTextArea.getFont().deriveFont(Font.BOLD, 18));
		catalogTextArea.setForeground(Color.WHITE);
		catalogTextArea.setEditable(false);
		
		
		JPanel operatorPanel = new JPanel();
		operatorPanel.setLayout(new GridLayout(1, 9));
		operatorPanel.setBackground(Color.DARK_GRAY);
		
		operatorButton = new JButton(Preferences.getGearIcon());
		operatorButton.setOpaque(false); operatorButton.setContentAreaFilled(false); operatorButton.setBorderPainted(false); operatorButton.setFocusPainted(false);
		
		operatorPanel.add(operatorButton); 
		for (int i = 0; i < 8; i++) {  //creo 8 pannelli grigi vuoti, utili per la grafica
			JPanel emptyPanel = new JPanel();
			emptyPanel.setBackground(Color.DARK_GRAY);
			operatorPanel.add(emptyPanel);
		}
		
		
		add(catalogTextArea, BorderLayout.CENTER); add(operatorPanel, BorderLayout.SOUTH);
	}
	
	public void setCatalog(String catalog) {
		catalogTextArea.setText(catalog);
	}

	public JButton getOperatorButton() {
		return operatorButton;
	}

}
