package it.unipv.ingsw.d20.vendingmachine.gui.operator;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.Pulsante;

public class OperatorGui extends JFrame {
		private Pannello p;
		public OperatorGui(int numeroTank) {
			
			setSize(600,400);
			setTitle("Interfaccia operatore");

			//setLayout(new BorderLayout());
			p=new Pannello(numeroTank);
			this.add(p);
		}
		public Pulsante[] getPulsanti() {
			return p.getPulsanti();
		}
}
