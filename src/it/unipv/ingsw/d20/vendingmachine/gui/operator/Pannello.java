package it.unipv.ingsw.d20.vendingmachine.gui.operator;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.Pulsante;

public class Pannello extends JPanel {
	private JLabel[] nome_serbatoio;
	private JLabel[] livello_serbatoio;
	private Pulsante[] riempimento_serbatoio;
	private int n;
	
	public Pannello(int numeroTank) {
		this.n=numeroTank;
		nome_serbatoio=new JLabel [numeroTank];
		livello_serbatoio=new JLabel [numeroTank];
		riempimento_serbatoio=new Pulsante [numeroTank];
		this.setLayout(new GridLayout(numeroTank,3));
		for(int i=0; i<numeroTank;i++) {
			nome_serbatoio[i]=new JLabel(""+i);
			livello_serbatoio[i]=new JLabel(""+i);
			riempimento_serbatoio[i]=new Pulsante(i,"pulsante numero "+i);
			this.add(nome_serbatoio[i]);
			this.add(livello_serbatoio[i]);
			this.add(riempimento_serbatoio[i]);
		}
		
		
		
	}
	public void setElements(String nome,String livello) {
		for(int i=0; i<n;i++) {
			nome_serbatoio[i].setText(nome);
			livello_serbatoio[i].setText(livello);
		}
	}
	public Pulsante[] getPulsanti() {
		return riempimento_serbatoio;
	}
}
