package guitest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CodePanel extends JPanel {
	
	private JLabel display;
	private UserButton[] codeButtons = new UserButton[12];
	
	public CodePanel() {
		setLayout(new BorderLayout());	
		
		JPanel displayPanel = new JPanel(); displayPanel.setLayout(new BorderLayout()); displayPanel.setBackground(Color.BLACK);
		display = new JLabel(" E0,00"); 
		display.setBackground(Color.BLACK); display.setForeground(Color.WHITE); display.setHorizontalAlignment(SwingConstants.RIGHT);
		displayPanel.add(display, BorderLayout.CENTER);
		
		String filename = "images/DS-DIGIB.TTF";
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		
		display.setFont(font); display.setFont(display.getFont().deriveFont(Font.BOLD, 50));
		
		
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new GridLayout(4, 3));
		
		initializeButtons();

		for (int i = 0; i < codeButtons.length; i++) {
			numberPanel.add(codeButtons[i]);
		}
		
		
		add(displayPanel, BorderLayout.NORTH); add(numberPanel, BorderLayout.CENTER);
	}
	
	private void initializeButtons() {
		codeButtons[0] = new UserButton("1", 1);
		codeButtons[1] = new UserButton("2", 2);
		codeButtons[2] = new UserButton("3", 3);
		codeButtons[3] = new UserButton("4", 4);
		codeButtons[4] = new UserButton("5", 5);
		codeButtons[5] = new UserButton("6", 5);		
		codeButtons[6] = new UserButton("7", 7);
		codeButtons[7] = new UserButton("8", 8);
		codeButtons[8] = new UserButton("9", 9);
		codeButtons[9] = new UserButton("Canc", -1);
		codeButtons[10] = new UserButton("0", 0);
		codeButtons[11] = new UserButton("Ok", 10);		
	}
	
	public UserButton[] getCodeButtons() {
		return codeButtons;
	}

	public String getDisplay() {
		return display.getText().substring(1);
	}

	public void setDisplay(String creditToString) {
		display.setText(" " + creditToString);
	}

}
