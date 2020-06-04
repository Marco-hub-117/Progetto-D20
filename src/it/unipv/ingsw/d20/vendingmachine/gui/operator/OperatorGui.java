package it.unipv.ingsw.d20.vendingmachine.gui.operator;



import javax.swing.JFrame;

public class OperatorGui extends JFrame {
		private Pannello p;
		public OperatorGui(int numeroTank) {
			
			setSize(600,400);
			setTitle("Interfaccia operatore");
			//setLayout(new BorderLayout());
			p=new Pannello(numeroTank);
			this.add(p);
		}
}
