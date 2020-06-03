package it.unipv.ingsw.d20.vendingmachine.gui.operator;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class OperatorGui extends JFrame {
		Pannello p;
		public OperatorGui() {
			
			setSize(600,400);
			setTitle("Interfaccia operatore");
			setLayout(new BorderLayout());
			p=new Pannello();
			this.add(p);
		}
}
