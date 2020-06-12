package guitest;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CreditPanel extends JPanel {
	
	private UserButton[] cashButtons = new UserButton[7];
	private JButton insertKeyButton;
	private JButton ejectKeyButton;
	
	public CreditPanel() {
		setLayout(new GridLayout(4, 1));
		
		JPanel restPanel = new JPanel(); restPanel.setLayout(new BorderLayout());
		cashButtons[0] = new UserButton("Resto", -1); cashButtons[0].setFont(cashButtons[0].getFont().deriveFont(Font.PLAIN, 25));
		restPanel.add(cashButtons[0], BorderLayout.CENTER); restPanel.add(new JLabel(" "), BorderLayout.SOUTH);
		
		
		JPanel keyPanel = new JPanel(); keyPanel.setLayout(new GridLayout(1, 2));
		insertKeyButton = new JButton("Inserisci chiavetta"); insertKeyButton.setFont(insertKeyButton.getFont().deriveFont(Font.PLAIN, 25)); keyPanel.add(insertKeyButton);
		ejectKeyButton = new JButton("Espelli chiavetta"); ejectKeyButton.setFont(ejectKeyButton.getFont().deriveFont(Font.PLAIN, 25)); keyPanel.add(ejectKeyButton);
		
		
		JPanel coinPanel1 = new JPanel(); coinPanel1.setLayout(new GridLayout(1, 3));
		JPanel coinPanel2 = new JPanel(); coinPanel2.setLayout(new GridLayout(1, 3));
		
		initializeButtons();
		
		for (int i = 1; i < cashButtons.length; i++) {
			if (i < (cashButtons.length + 1) / 2) {
				coinPanel1.add(cashButtons[i]);
			} else {
				coinPanel2.add(cashButtons[i]);
			}
		}		
		
		
		add(restPanel); add(keyPanel); add(coinPanel1); add(coinPanel2);
	}

	private void initializeButtons() {
		cashButtons[1] = new UserButton("€2,00", 2.0);
		cashButtons[2] = new UserButton("€1,00", 1.0);
		cashButtons[3] = new UserButton("€0,50", 0.5);
		cashButtons[4] = new UserButton("€0,20", 0.2);
		cashButtons[5] = new UserButton("€0,10", 0.1);
		cashButtons[6] = new UserButton("€0,05", 0.05);		
	}

	public UserButton[] getCashButtons() {
		return cashButtons;
	}

	public JButton getInsertKeyButton() {
		return insertKeyButton;
	}

	public JButton getEjectKeyButton() {
		return ejectKeyButton;
	}

}
