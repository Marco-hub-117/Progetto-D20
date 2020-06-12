package guitest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CatalogPanel extends JPanel {
	
	private CustomTextArea catalogTextArea;
	private JButton operatorButton;
	
	public CatalogPanel() {
		setLayout(new BorderLayout());
		
		catalogTextArea = new CustomTextArea();
		catalogTextArea.setBackground(new Color(1, 1, 1, (float) 0.01));
		catalogTextArea.setFont(catalogTextArea.getFont().deriveFont(Font.BOLD, 18));
		catalogTextArea.setEditable(false);
		
		
		JPanel operatorPanel = new JPanel();
		operatorPanel.setLayout(new GridLayout(1, 9)); //i pannelli vuoti sono utili solo alla grafica
		
		operatorButton = new JButton(new ImageIcon("images/gearicon.png"));
		operatorButton.setOpaque(false); operatorButton.setContentAreaFilled(false); operatorButton.setBorderPainted(false); operatorButton.setFocusPainted(false);
		
		operatorPanel.add(operatorButton); 
		operatorPanel.add(new JPanel()); operatorPanel.add(new JPanel()); operatorPanel.add(new JPanel()); operatorPanel.add(new JPanel());
		operatorPanel.add(new JPanel()); operatorPanel.add(new JPanel()); operatorPanel.add(new JPanel()); operatorPanel.add(new JPanel());
		
		
		add(catalogTextArea, BorderLayout.CENTER); add(operatorPanel, BorderLayout.SOUTH);
	}
	
	public void setCatalog(String catalog) {
		catalogTextArea.setText(catalog);
	}

	public JButton getOperatorButton() {
		return operatorButton;
	}

}
