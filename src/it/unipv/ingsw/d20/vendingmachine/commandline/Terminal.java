package it.unipv.ingsw.d20.vendingmachine.commandline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
		setPrompt();
		commandLine.setFont(commandLine.getFont().deriveFont(Font.PLAIN, 18));
		commandLine.setForeground(Color.WHITE);
		commandLine.setBackground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(commandLine);
		scrollPane.setBorder(null);
		
		panel.add(scrollPane, BorderLayout.CENTER);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public String getCommand() {
		String[] lines = commandLine.getText().split("\n");
		return lines[commandLine.getLineCount() - 2];
	}
	
	public void newLine() {
		commandLine.append("\n");
	}
	
	public void setPrompt() {
		commandLine.append("$ ");
	}
	
	public void print(String string) {
		commandLine.append(string);
	}
	
	public JTextArea getCommandLine() {
		return commandLine;
	}

}
