package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Classe che simula l'erogazione di una bevanda.
 * 
 */
public class Beverage extends Thread {
	
	private static final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 225;
	private static final int BAR_WIDTH = 400, BAR_HEIGHT = 80;
    
    private JFrame window;
    
    private JProgressBar progressBar;
    private int progress = 0;
    private JButton pickUpBeverageButton;
	
	@Override
	public void run() {
		window = new JFrame("Erogazione in corso..."); 
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setResizable(false);

        Container container = window.getContentPane();
        JPanel panel = new JPanel(); 
        container.add(panel);
        
        progressBar = new JProgressBar(); 
  
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setFont(progressBar.getFont().deriveFont(Font.BOLD, 20));
        progressBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
        
        JLabel emptyLabel = new JLabel("                                                              "
        		+ "                                                                                   "
        		+ "                                                                                   ");
        emptyLabel.setFont(emptyLabel.getFont().deriveFont(Font.PLAIN, 10));
        
        pickUpBeverageButton = new JButton("Ritira bevanda");
        pickUpBeverageButton.setFont(pickUpBeverageButton.getFont().deriveFont(Font.PLAIN, 20));
        pickUpBeverageButton.setFocusable(false); pickUpBeverageButton.setEnabled(false);
        
        panel.add(new JLabel("                                                                                                                 "));
        panel.add(progressBar); 
        panel.add(emptyLabel);
        panel.add(pickUpBeverageButton);

        window.setLocationRelativeTo(null);
        window.setVisible(true); 
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  
        fill(); 
	}

	private void fill() { 
        try { 
            while (progress <= 100) { 
            	if (progress == 100) {
            		window.setTitle("Erogazione completata!");
            		
            		pickUpBeverageButton.setEnabled(true);
            		pickUpBeverageButton.addActionListener(al -> window.dispose());
            		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            		
            		break;
            	}
            	
                progressBar.setValue(progress + 5); 
  
                Thread.sleep(125);
                
                if (Math.random() < 0.10); //per un andamento "a scatti" della progress bar
                else 
                 progress += 5; 
            } 
        } 
        catch (Exception e) { 
        } 
    } 

}
