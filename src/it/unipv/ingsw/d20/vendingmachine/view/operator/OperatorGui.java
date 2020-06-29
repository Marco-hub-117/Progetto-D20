package it.unipv.ingsw.d20.vendingmachine.view.operator;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * La GUI dedicata all'operatore. E' possibile visionare la percentuale
 * di riempimento dei tank e riempirli. E' anche possibile ritirare le monete
 * contenute nella macchinetta.
 *
 */
@SuppressWarnings("serial")
public class OperatorGui extends JFrame {
	
	private static final int WIDTH = 600, HEIGHT = 400;
	
	private TankPanel operatorPanel;
	private JButton withdrawCashButton;
	private JButton exitButton;
		
	public OperatorGui(int tankNumber) {
		setTitle("Modalità operatore");
		setSize(WIDTH, HEIGHT);
		
		setLayout(new BorderLayout());
		
		operatorPanel = new TankPanel(tankNumber);
		
		exitButton = new JButton("Esci");
		exitButton.setFont(exitButton.getFont().deriveFont(Font.PLAIN, 20));
		withdrawCashButton = new JButton("Ritira contanti");
		withdrawCashButton.setFont(withdrawCashButton.getFont().deriveFont(Font.PLAIN, 20));
		
		JPanel southPanel = new JPanel(); southPanel.setLayout(new BorderLayout());
		southPanel.add(withdrawCashButton, BorderLayout.WEST);
		southPanel.add(exitButton, BorderLayout.EAST);
		
		add(operatorPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
		
	public OperatorButton[] getButtons() {
		return operatorPanel.getRefillTankButtons();
	}
	
	public void setElement(String name, String level, int position) {
		operatorPanel.setElement(name, level, position);
	}
	
	public JButton getWithdrawCashButton() {
		return withdrawCashButton;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}
		
}
