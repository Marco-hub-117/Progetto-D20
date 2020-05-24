package it.unipv.ingsw.d20.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CoinPanel extends JPanel {
	public CoinPanel() {
		JButton cinq=new JButton("0,05€");
		JButton dieci=new JButton("0,10€");
		JButton venti=new JButton("0,20€");
		JButton cinqu=new JButton("0,50€");
		JButton uno=new JButton("1,00€");
		JButton due=new JButton("2,00€");
		
		this.setLayout(new GridLayout(2,3,2,2));
		add(due);
		add(uno);
		add(cinqu);
		add(venti);
		add(dieci);
		add(cinq);
	}
}
