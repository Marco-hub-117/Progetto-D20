package it.unipv.ingsw.d20.vendingmachine.gui.operator;

import javax.swing.JFrame;

public class OperatorGui extends JFrame {
		private Pannello p;
		public OperatorGui(int numeroTank) {
			
			setSize(600,400);
			setTitle("Interfaccia operatore");
<<<<<<< HEAD
			setLayout(new BorderLayout());
			p=new Pannello(5);
=======
			//setLayout(new BorderLayout());
			p=new Pannello(numeroTank);
>>>>>>> branch 'master' of https://github.com/IngSW-unipv/Progetto-D20.git
			this.add(p);
		}
}
