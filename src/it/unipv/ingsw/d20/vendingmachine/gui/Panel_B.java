package it.unipv.ingsw.d20.vendingmachine.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_B extends JPanel{ 
	private JTextField price;
	private JTextField amount;
	private CoinPanel p;
	public Panel_B(){
		price=new JTextField(10);
		amount=new JTextField(10);
		p=new CoinPanel();
		this.setLayout(new BorderLayout());
		JPanel pan=new JPanel();
		pan.add(price);
		pan.add(amount);
		add(pan, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
	}
	public List<Pulsante> getL() {
		return p.getL();
	}
	public void setPrice(String text) {
		price.setText(text);
	}
	public void setAmount(String text) {
		amount.setText(text);
	}
	public String getAmount() {
		return amount.getText();
	}
	public String getPrice() {
		return price.getText();
	}
}
