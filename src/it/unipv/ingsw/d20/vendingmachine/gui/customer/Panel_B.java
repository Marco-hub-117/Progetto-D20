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
import javax.swing.JTextField;

public class Panel_B extends JPanel{ 
	
	private JTextField amount;
	
	private JLabel l2;
	private CoinPanel p;
	private JButton in;  //inserimento chiavetta
	private JButton out; //estrazione chiavetta
	
	public Panel_B(){
		JPanel x=new JPanel();
		amount=new JTextField(10);
		BufferedImage myPicture;
		in=new JButton("Inserisci chiavetta");
		out=new JButton("Estrai chiavetta");
		p=new CoinPanel();
		this.setLayout(new BorderLayout());
		JPanel pan=new JPanel();
		pan.setLayout(new GridLayout(2,2));

		add(pan, BorderLayout.NORTH);
		add(p, BorderLayout.SOUTH);
	
		l2=new JLabel("Credito");
		
		pan.add(l2);
		pan.add(amount);
	
		try {
			myPicture=ImageIO.read(new File("images/img_tazza.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			add(picLabel,BorderLayout.CENTER);
		} catch (IOException e) {
			
		}
		x.setLayout(new GridLayout(2,1));
		x.add(in);
		x.add(out);
		this.add(x,BorderLayout.LINE_START);
	}
	public List<Pulsante> getL() {
		return p.getL();
	}
	public void setAmount(String text) {
		amount.setText(text);
	}
	public String getAmount() {
		return amount.getText();
	}
	public JButton getIn() {
		return in;
	}
	public JButton getOut() {
		return out;
	}
}
