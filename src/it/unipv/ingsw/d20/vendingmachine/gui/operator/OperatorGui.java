package it.unipv.ingsw.d20.vendingmachine.gui.operator;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.Pulsante;

public class OperatorGui extends JFrame {
		private Pannello p;
		private JButton customer;
		public OperatorGui(int numeroTank) {
			setSize(600,400);
			setTitle("Interfaccia operatore");
			customer=new JButton("esci");
			p=new Pannello(numeroTank);
			setLayout(new BorderLayout());
			add(p, BorderLayout.CENTER);
			add(customer, BorderLayout.SOUTH);
		}
		public Pulsante[] getPulsanti() {
			return p.getPulsanti();
		}
		public void setElements(String nome,String livello, int pos) {
			p.setElements(nome, livello, pos);
		}
		public JButton getCustomer() {
			return customer;
		}
}
