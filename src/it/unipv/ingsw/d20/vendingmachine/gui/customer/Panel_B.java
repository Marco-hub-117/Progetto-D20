package it.unipv.ingsw.d20.vendingmachine.gui.customer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel_B extends JPanel{ 
	private JTextField price;
	private JTextField amount;
	private JLabel l1;
	private JLabel l2;
	private CoinPanel p;
	
	public Panel_B(){
		price=new JTextField(10);
		amount=new JTextField(10);
		BufferedImage myPicture;
		
		p=new CoinPanel();
		this.setLayout(new BorderLayout());
		JPanel pan=new JPanel();
		pan.setLayout(new GridLayout(2,2));

		add(pan, BorderLayout.NORTH);
		add(p, BorderLayout.SOUTH);
		l1=new JLabel("Prezzo bevanda");
		l2=new JLabel("Credito");
		pan.add(l1);
		pan.add(price);
		pan.add(l2);
		pan.add(amount);
		try {
			myPicture=ImageIO.read(new File("images/img_tazza.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			add(picLabel,BorderLayout.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

		
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
