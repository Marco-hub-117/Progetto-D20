package it.unipv.ingsw.d20.vendingmachine.view.operator;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pannello che contiene le informazioni relative ai tank e i pulsanti
 * per riempirli.
 *
 */
@SuppressWarnings("serial")
public class TankPanel extends JPanel {
	
	private JLabel[] tankIngredient;
	private JLabel[] tankLevel;
	private OperatorButton[] refillTankButtons;
	
	public TankPanel(int tankNumber) {
		tankIngredient = new JLabel[tankNumber];
		tankLevel = new JLabel[tankNumber];
		refillTankButtons = new OperatorButton[tankNumber];
		
		setLayout(new GridLayout(tankNumber, 3));
		
		for(int i = 0; i < tankNumber; i++) {
			tankIngredient[i] = new JLabel(String.valueOf(i)); 
			tankIngredient[i].setFont(tankIngredient[i].getFont().deriveFont(Font.PLAIN, 20));
			
			tankLevel[i]=new JLabel(String.valueOf(i));
			tankLevel[i].setFont(tankLevel[i].getFont().deriveFont(Font.PLAIN, 20));
			
			refillTankButtons[i] = new OperatorButton(i, "Riempi", "");
			refillTankButtons[i].setFont(refillTankButtons[i].getFont().deriveFont(Font.PLAIN, 20));
			
			add(tankIngredient[i]);
			add(tankLevel[i]);
			add(refillTankButtons[i]);
		}
	}
	
	public void setElement(String name, String level, int pos) {
		tankIngredient[pos].setText(name);
		tankLevel[pos].setText(level + "%");
		refillTankButtons[pos].setIdTank(name);
	}
	
	public OperatorButton[] getRefillTankButtons() {
		return refillTankButtons;
	}
	
}
