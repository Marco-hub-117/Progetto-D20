package it.unipv.ingsw.d20.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CoinPanel extends JPanel {
	private List<Pulsante> l;
	public CoinPanel() {
		this.l=new ArrayList<>();
		l.add(new Pulsante(2,1,"2.0E"));
		l.add(new Pulsante(1,2,"1.0E"));
		l.add(new Pulsante(50,3,"0.50E"));
		l.add(new Pulsante(20,4,"0.20E"));
		l.add(new Pulsante(10,5,"0.10E"));
		l.add(new Pulsante(5,6,"0.05E"));
		this.setLayout(new GridLayout(2,3,2,2));
		ordinaPulsanti();
		for(Pulsante p:l) {
			this.add(p);
		}
		
	}
	private void ordinaPulsanti() { 
		PulsanteComparator p=new PulsanteComparator();
		l.sort(p);
	}
	public List<Pulsante> getL() {
		return l;
	}

}
