package it.unipv.ingsw.d20.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_B extends JPanel{ 
	public Panel_B(){
		JTextField price=new JTextField("Costo bevanda selezionata");
		JTextField amount=new JTextField("Credito inserito");
		CoinPanel p=new CoinPanel();
		this.setLayout(new BorderLayout());
		JPanel pan=new JPanel();
		pan.add(price);
		pan.add(amount);
		add(pan, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
	}
}
