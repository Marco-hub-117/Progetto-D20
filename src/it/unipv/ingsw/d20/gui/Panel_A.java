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
	
	
	public Panel_A(){
		display=new JTextField(10);
		
		p=new NumberPanel();
		this.setLayout(new BorderLayout());
		add(display, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
	
	 }
	public List<Pulsante> getL() {
		return p.getL();
	}
	public void setDisplay(String text) {
		display.setText(text);
	}
	public String getDisplay() {
		return display.getText();
	}
	
}
