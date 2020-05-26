package it.unipv.ingsw.d20.vendingmachine.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Panel_A extends JPanel{
	private NumberPanel p;
	private JTextField display;
	private JTextArea text;
	
	public Panel_A(){
		display=new JTextField(10);
	text=new JTextArea("Qui ci va il catalogo");
		p=new NumberPanel();
		this.setLayout(new BorderLayout());
		add(display, BorderLayout.NORTH);
		add(text, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
	
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
	public String getText() {
		return text.getText();
	}
	public void setText(String stringa) {
		text.setText(stringa);
	}
	
}
