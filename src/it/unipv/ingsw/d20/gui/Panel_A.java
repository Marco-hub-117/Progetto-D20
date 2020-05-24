package it.unipv.ingsw.d20.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Panel_A extends JPanel{ 
	public Panel_A(){
		JTextField display=new JTextField("display");
		NumberPanel p=new NumberPanel();
		JButton operator=new JButton("operator");
		this.setLayout(new BorderLayout());
		add(display, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		add(operator, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEmptyBorder(0,10,100,10)); 
   

	}
	
}
