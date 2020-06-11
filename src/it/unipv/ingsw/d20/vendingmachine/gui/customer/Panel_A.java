package it.unipv.ingsw.d20.vendingmachine.gui.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel_A extends JPanel{
	
	private NumberPanel p;
	private JTextField display;
	private JTextArea text;
	private JLabel l2;
	
	public Panel_A(){
		l2=new JLabel("Inserisci il codice della bevanda");
		
		display=new JTextField("000");
		display.setFont(display.getFont().deriveFont(Font.PLAIN, 18));
		display.setEditable(false);
		
		text=new JTextArea();
		text.setFont(text.getFont().deriveFont(Font.PLAIN, 18));
		text.setEditable(false);
		
		p=new NumberPanel();
		
		this.setLayout(new BorderLayout());
		
		JPanel pan=new JPanel();
		pan.setLayout(new GridLayout(2,2));
		pan.add(l2);
		pan.add(display);
		
		add(pan, BorderLayout.NORTH);
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
