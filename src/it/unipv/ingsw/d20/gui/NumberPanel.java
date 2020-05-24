package it.unipv.ingsw.d20.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NumberPanel extends JPanel {
	public NumberPanel() {
		JButton zero=new JButton("0");
		JButton uno=new JButton("1");
		JButton due=new JButton("2");
		JButton tre=new JButton("3");
		JButton quat=new JButton("4");
		JButton cinq=new JButton("5");
		JButton sei=new JButton("6");
		JButton sett=new JButton ("7"); 
		JButton otto=new JButton("8");
		JButton nove=new JButton("9");
		JButton ok=new JButton("Ok");
		JButton canc=new JButton("Canc");
		this.setLayout(new GridLayout(4,3,2,2));
		
		add(uno);
		add(due);
		add(tre);
		add(quat);
		add(cinq);
		add(sei);
		add(sett);
		add(otto);
		add(nove);
		add(canc);
		add(zero);
		add(ok);
	}
}
