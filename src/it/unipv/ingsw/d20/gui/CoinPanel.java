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
		l.add(new Pulsante(2.0,1,"2.0E"));
		l.add(new Pulsante(1.0,2,"1.0E"));
		l.add(new Pulsante(0.5,3,"0.50E"));
		l.add(new Pulsante(0.2,4,"0.20E"));
		l.add(new Pulsante(0.1,5,"0.10E"));
		l.add(new Pulsante(0.05,6,"0.05E"));
		this.setLayout(new GridLayout(2,3,2,2));
		ordinaPulsanti();
		for(Pulsante p:l) {
			//for-each
			//aggiungo i pulsanti al pannello dei tasti
			this.add(p);
		}
		
	}
	private void ordinaPulsanti() { 
		//creo un metodo che mi ordini i pulsanti secondo la loro posizione (definita in PulsanteComparator)
		PulsanteComparator p=new PulsanteComparator();
		l.sort(p);
	}
	public List<Pulsante> getL() {
		return l;
	}

}
