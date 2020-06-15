package it.unipv.ingsw.d20.vendingmachine.gui.operator;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class OperatorGui extends JFrame {
		private OperatorPanel p;
		private JButton customer;
		
		public OperatorGui(int numeroTank) {
			setSize(600,400);
			setTitle("Interfaccia operatore");
			setLocationRelativeTo(null);
			customer=new JButton("esci");
			p=new OperatorPanel(numeroTank);
			setLayout(new BorderLayout());
			add(p, BorderLayout.CENTER);
			
			add(customer, BorderLayout.SOUTH);
		}
		
		public OperatorButton[] getPulsanti() {
			return p.getPulsanti();
		}
		
		public void setElements(String nome,String livello, int pos) {
			p.setElements(nome, livello, pos);
		}
		
		public JButton getCustomer() {
			return customer;
		}
		
}
