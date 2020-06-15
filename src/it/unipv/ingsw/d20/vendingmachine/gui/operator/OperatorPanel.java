package it.unipv.ingsw.d20.vendingmachine.gui.operator;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OperatorPanel extends JPanel {
	private JLabel[] nome_serbatoio;
	private JLabel[] livello_serbatoio;
	private OperatorButton[] riempimento_serbatoio;
	
	public OperatorPanel(int numeroTank) {
		nome_serbatoio=new JLabel [numeroTank];
		livello_serbatoio=new JLabel [numeroTank];
		riempimento_serbatoio=new OperatorButton[numeroTank];
		this.setLayout(new GridLayout(numeroTank,3));
		for(int i=0; i<numeroTank;i++) {
			nome_serbatoio[i]=new JLabel(""+i);
			livello_serbatoio[i]=new JLabel(""+i);
			riempimento_serbatoio[i]=new OperatorButton(i,"Riempi", "");
			this.add(nome_serbatoio[i]);
			this.add(livello_serbatoio[i]);
			this.add(riempimento_serbatoio[i]);
		}
	}
	public void setElements(String nome,String livello, int pos) {
		nome_serbatoio[pos].setText(nome);
		livello_serbatoio[pos].setText(livello);
		riempimento_serbatoio[pos].setIdTank(nome);
	}
	
	public OperatorButton[] getPulsanti() {
		return riempimento_serbatoio;
	}
}
