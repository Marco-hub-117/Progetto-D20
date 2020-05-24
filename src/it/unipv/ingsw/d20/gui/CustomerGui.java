package it.unipv.ingsw.d20.gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class CustomerGui extends JFrame{
	private Panel_A pa;
	private Panel_B pb;
	public CustomerGui() {
		setSize(300,400);
		setTitle("Interfaccia cliente");
		pa=new Panel_A();
		pb=new Panel_B();
		this.setLayout(new FlowLayout());
		add(pa); 
		add(pb);
	}
	
}

