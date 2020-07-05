package it.unipv.ingsw.d20.vendingmachine.commandline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * JFrame che contiene una JTextArea che permette di simulare
 * il comportamento di un terminale.
 *
 */
@SuppressWarnings("serial")
public class Terminal extends JFrame {
	
	private static final int WIDTH = 700, HEIGHT = 600;
	
	private JTextArea commandLine;
	
	public Terminal() {
		setTitle("Progetto D");
		setSize(WIDTH, HEIGHT);
		
		Container container = getContentPane();
		JPanel panel = new JPanel();
		container.add(panel);
		panel.setLayout(new BorderLayout());
		
		commandLine = new JTextArea();
		setInitString();
		setPrompt();
		commandLine.setFont(commandLine.getFont().deriveFont(Font.PLAIN, 18));
		commandLine.setForeground(Color.GREEN);
		commandLine.setBackground(Color.BLACK);
		commandLine.setLineWrap(true);
		commandLine.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(commandLine);
		scrollPane.setBorder(null);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Restituisce l'ultima linea dell'area di testo.
	 * @return command il comando inserito
	 */
	public String getCommand() {
		String[] lines = commandLine.getText().split("\n");
		return lines[commandLine.getLineCount() - 2];
	}
	
	/**
	 * Va a capo nell'area di testo.
	 */
	public void newLine() {
		commandLine.append("\n");
	}
	
	/**
	 * Inserisce il carattere '$' nell'area di testo.
	 */
	public void setPrompt() {
		commandLine.append("$ ");
	}
	
	/**
	 * Stampa nell'area di testo la stringa passatagli come parametro.
	 * @param string testo da stampare
	 */
	public void print(String string) {
		commandLine.append(string);
	}
	
	private void setInitString() {
		commandLine.append("Utilizzare il comando 'help' per informazioni\n");
	}
	
	public JTextArea getCommandLine() {
		return commandLine;
	}

}
