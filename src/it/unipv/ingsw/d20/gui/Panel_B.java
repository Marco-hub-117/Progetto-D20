package it.unipv.ingsw.d20.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_B extends JPanel{ 
	private JTextField price;
	private JTextField amount;
	private CoinPanel p;
	public Panel_B(){
		price=new JTextField("Costo bevanda selezionata");
		amount=new JTextField("Credito inserito");
		p=new CoinPanel();
		this.setLayout(new BorderLayout());
		JPanel pan=new JPanel();
		pan.add(price);
		pan.add(amount);
		add(pan, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
	}
	public List<Pulsante> getL() {
		return p.getL();
	}

}
