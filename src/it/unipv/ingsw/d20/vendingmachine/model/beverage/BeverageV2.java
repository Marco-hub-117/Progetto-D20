package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class BeverageV2 extends Thread {
	
	private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 175;
	private static final int BAR_WIDTH = 400, BAR_HEIGHT = 80;
    
    JFrame window;
    
    private JProgressBar progressBar;
    private int progress = 0;
	
	@Override
	public void run() {
		window = new JFrame("Erogazione in corso..."); 
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        Container container = window.getContentPane();
        JPanel panel = new JPanel(); 
        container.add(panel);
        
        progressBar = new JProgressBar(); 
  
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setFont(progressBar.getFont().deriveFont(Font.BOLD, 20));
        progressBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
        
        panel.add(new JLabel("                                                                                                                 "));
        panel.add(progressBar); 

        window.setLocationRelativeTo(null);
        window.setVisible(true); 
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  
        fill(); 
	}
	
	private void fill() { 
        try { 
            while (progress <= 100) { 
            	if (progress == 100) {
            		progressBar.setString("Erogazione completata!");
            		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            	}
            	
                progressBar.setValue(progress + 5); 
  
                Thread.sleep(125);
                
                if (Math.random() < 0.10);
                else 
                 progress += 5; 
            } 
        } 
        catch (Exception e) { 
        } 
    } 

}
