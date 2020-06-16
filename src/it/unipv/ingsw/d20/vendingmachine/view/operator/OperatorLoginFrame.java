package it.unipv.ingsw.d20.vendingmachine.view.operator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class OperatorLoginFrame extends JFrame {
	
	private static final int WIDTH = 500, HEIGHT = 275;
	
	private JPasswordField keyPassField;
	private JLabel wrongKeyLabel;
	private JButton enterButton;
	
	public OperatorLoginFrame() {
		setTitle("Entra in modalità operatore");
		setSize(WIDTH, HEIGHT);
		
		Container container = getContentPane();
		JPanel panel = new JPanel();
		container.add(panel);
		
		panel.setLayout(new GridLayout(4 ,1));
		
		JPanel insertKeyPanel = new JPanel();
		JLabel insertKeyLabel = new JLabel("Inserire la chiave:");
		insertKeyLabel.setFont(insertKeyLabel.getFont().deriveFont(Font.PLAIN, 20));
		insertKeyPanel.add(insertKeyLabel);
		panel.add(insertKeyPanel);
		
		JPanel keyPanel = new JPanel();
		keyPassField = new JPasswordField(20); 
		keyPassField.setFont(keyPassField.getFont().deriveFont(Font.PLAIN, 25));
		keyPanel.add(keyPassField);
		panel.add(keyPanel);
		
		JPanel wrongKeyPanel = new JPanel();
		wrongKeyLabel = new JLabel(" ");
		wrongKeyLabel.setFont(wrongKeyLabel.getFont().deriveFont(Font.PLAIN, 20));
		wrongKeyLabel.setForeground(Color.RED);
		wrongKeyPanel.add(wrongKeyLabel);
		panel.add(wrongKeyPanel);
		
		JPanel enterPanel = new JPanel();
		enterButton = new JButton("  Entra  "); 
		enterButton.setFont(enterButton.getFont().deriveFont(Font.PLAIN, 20));
		enterPanel.add(enterButton);
		panel.add(enterPanel);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JButton getEnterButton() {
		return enterButton;
	}
	
	@SuppressWarnings("deprecation")
	public String getInsertedKey() {
		return keyPassField.getText();
	}
	
	public void setWrongKeyWarning() {
		wrongKeyLabel.setText("La chiave inserita non è corretta");
	}

}
