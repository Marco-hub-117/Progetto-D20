package it.unipv.ingsw.d20.vendingmachine.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class NumberPanel extends JPanel {
	private List<Pulsante> l;
	public NumberPanel() {
		this.l=new ArrayList<>();
		
		l.add(new Pulsante(0,11,"0"));
		l.add(new Pulsante(1,1,"1"));
		l.add(new Pulsante(2,2,"2"));
		l.add(new Pulsante(3,3,"3"));
		l.add(new Pulsante(4,4,"4"));
		l.add(new Pulsante(5,5,"5"));
		l.add(new Pulsante(6,6,"6"));
		l.add(new Pulsante(7,7,"7"));
		l.add(new Pulsante(8,8,"8"));
		l.add(new Pulsante(9,9,"9"));
		l.add(new Pulsante(-1,10,"Canc"));
		l.add(new Pulsante(10,12,"Ok"));
		
		this.setLayout(new GridLayout(4,3,2,2));
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
