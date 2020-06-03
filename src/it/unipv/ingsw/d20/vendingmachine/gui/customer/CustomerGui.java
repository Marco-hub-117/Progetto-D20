package it.unipv.ingsw.d20.vendingmachine.gui.customer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomerGui extends JFrame{
	private Panel_A pa;
	private Panel_B pb;
	private JButton operator;
	
	public CustomerGui() {
		setSize(600,400);
		setTitle("Interfaccia cliente");
		setLayout(new BorderLayout());
		pa=new Panel_A();
		pb=new Panel_B();
		operator=new JButton("operator");
		JPanel pan=new JPanel();
		pan.setLayout(new GridLayout(1,2));
		pan.add(pa);
		pan.add(pb);
		add(pan, BorderLayout.CENTER);
		add(operator, BorderLayout.SOUTH);
	}
	public List<Pulsante> getLA() {
		return pa.getL();
	}
	public List<Pulsante> getLB() {
		return pb.getL();
	}
	public void setDisplay(String text) {
		pa.setDisplay(text);
	}
	public void setAmount(String text) {
		pb.setAmount(text);
	}
	public void setPrice(String text) {
		pb.setPrice(text);
	}
	public String getDisplay() {
		return pa.getDisplay();
	}
	public String getAmount() {
		return pb.getAmount();
	}
	public String getPrice() {
		return pb.getPrice();
	}
	public String getText() {
		return pa.getText();
	}
	public void setText(String s) {
		pa.setText(s);
	}
	
}

