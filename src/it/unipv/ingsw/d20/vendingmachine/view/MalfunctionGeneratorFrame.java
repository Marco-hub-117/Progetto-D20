package it.unipv.ingsw.d20.vendingmachine.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simula un malfunzionamento del distributore automatico tramite un pulsante
 * che termina il programma inaspettatamente.
 *
 */
@SuppressWarnings("serial")
public class MalfunctionGeneratorFrame extends JFrame {
	
	private static final int WIDTH = 200, HEIGHT = 150;
	
	public MalfunctionGeneratorFrame(String IDNumber) {
		setTitle("Abort " + IDNumber);
		setSize(WIDTH, HEIGHT);
		
		Container container = getContentPane();
		JPanel panel = new JPanel();
		container.add(panel);
		panel.setLayout(new BorderLayout());
		
		JButton abortBtn = new JButton("Abort");
		abortBtn.setFont(abortBtn.getFont().deriveFont(Font.BOLD, 42));
		abortBtn.addActionListener(al -> System.exit(-1));
		panel.add(abortBtn, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
