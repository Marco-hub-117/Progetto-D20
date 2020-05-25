package it.unipv.ingsw.d20.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Panel_A extends JPanel{
	private NumberPanel p;
	private JTextField display;
	private JButton operator;
	
	public Panel_A(){
		display=new JTextField("display");
		p=new NumberPanel();
		operator=new JButton("operator");
		this.setLayout(new BorderLayout());
		add(display, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		add(operator, BorderLayout.SOUTH);
	 }
	public List<Pulsante> getL() {
		return p.getL();
	}

	
}
