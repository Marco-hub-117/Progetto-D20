package guitest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private static int WIDTH = 1200, HEIGHT = 675;
	
	CustomTextArea catalog;
	
	public MainWindow() {
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		Container container = getContentPane();
		JPanel mainPanel = new JPanel();
		container.add(mainPanel);
		mainPanel.setLayout(new GridLayout(1, 2));
		
		JPanel catalogPanel = new JPanel();
		catalog = new CustomTextArea();
		catalog.setBackground(new Color(1, 1, 1, (float) 0.01));
		catalog.setFont(catalog.getFont().deriveFont(Font.BOLD, 18));
		catalog.setEditable(false);
		catalog.setText("Codice 1                    Caff√®                                   ‚Ç¨1,00\n\nCodice 2                    T√®                                         ‚Ç¨0,80\n\n"
				+ "Codice 3                    Caff√®latte                        ‚Ç¨1,20\n\n");
		catalogPanel.add(catalog);
		
		JPanel keyboardPanel = new JPanel();
		keyboardPanel.setLayout(new GridLayout(2, 1));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		
		JPanel displayPanel = new JPanel(); displayPanel.setLayout(new BorderLayout()); displayPanel.setBackground(Color.BLACK);
		JLabel display = new JLabel("DISPLAY"); //display.setFont(display.getFont().deriveFont(Font.BOLD, 35));
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
		
		JButton btn1 = new JButton("1"); numberPanel.add(btn1); btn1.setFont(btn1.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn2 = new JButton("2"); numberPanel.add(btn2); btn2.setFont(btn2.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn3 = new JButton("3"); numberPanel.add(btn3); btn3.setFont(btn3.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn4 = new JButton("4"); numberPanel.add(btn4);btn4.setFont(btn4.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn5 = new JButton("5"); numberPanel.add(btn5); btn5.setFont(btn5.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn6 = new JButton("6"); numberPanel.add(btn6);btn6.setFont(btn6.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn7 = new JButton("7"); numberPanel.add(btn7); btn7.setFont(btn7.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn8 = new JButton("8"); numberPanel.add(btn8);btn8.setFont(btn8.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn9 = new JButton("9"); numberPanel.add(btn9); btn9.setFont(btn9.getFont().deriveFont(Font.PLAIN, 22));
		JButton btnCanc = new JButton("Canc"); numberPanel.add(btnCanc);btnCanc.setFont(btnCanc.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn0 = new JButton("0"); numberPanel.add(btn0); btn0.setFont(btn0.getFont().deriveFont(Font.PLAIN, 22));
		JButton btnOk = new JButton("Ok"); numberPanel.add(btnOk);btnOk.setFont(btnOk.getFont().deriveFont(Font.PLAIN, 22));
		
		upperPanel.add(displayPanel, BorderLayout.NORTH); upperPanel.add(numberPanel, BorderLayout.CENTER);
		
		
		JPanel lowerPanel = new JPanel(); 
		lowerPanel.setLayout(new GridLayout(4, 1));
		
		JPanel restPanel = new JPanel(); restPanel.setLayout(new BorderLayout());
		JButton btnRest = new JButton("Resto"); btnRest.setFont(btnRest.getFont().deriveFont(Font.PLAIN, 25));
		restPanel.add(btnRest, BorderLayout.CENTER); restPanel.add(new JLabel(" "), BorderLayout.SOUTH);
		
		JPanel keyPanel = new JPanel(); keyPanel.setLayout(new GridLayout(1, 2));
		JButton btnInsert = new JButton("Inserisci chiavetta"); btnInsert.setFont(btnInsert.getFont().deriveFont(Font.PLAIN, 25)); keyPanel.add(btnInsert);
		JButton btnEject = new JButton("Espelli chiavetta"); btnEject.setFont(btnEject.getFont().deriveFont(Font.PLAIN, 25)); keyPanel.add(btnEject);
		
		JPanel coinPanel1 = new JPanel(); coinPanel1.setLayout(new GridLayout(1, 3));
		JButton btn2euros = new JButton("Ä2,00"); btn2euros.setFont(btn2euros.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn1euro = new JButton("Ä1,00"); btn1euro.setFont(btn1euro.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn50cents = new JButton("Ä0,50"); btn50cents.setFont(btn50cents.getFont().deriveFont(Font.PLAIN, 22));
		coinPanel1.add(btn2euros); coinPanel1.add(btn1euro); coinPanel1.add(btn50cents);
		
		JPanel coinPanel2 = new JPanel(); coinPanel2.setLayout(new GridLayout(1, 3));
		JButton btn20cents = new JButton("Ä0,20"); btn20cents.setFont(btn20cents.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn10cents = new JButton("Ä0,10"); btn10cents.setFont(btn10cents.getFont().deriveFont(Font.PLAIN, 22));
		JButton btn5cents = new JButton("Ä0,05"); btn5cents.setFont(btn5cents.getFont().deriveFont(Font.PLAIN, 22));
		coinPanel2.add(btn20cents); coinPanel2.add(btn10cents); coinPanel2.add(btn5cents);
		
		lowerPanel.add(restPanel); lowerPanel.add(keyPanel); lowerPanel.add(coinPanel1); lowerPanel.add(coinPanel2);
		
		keyboardPanel.add(upperPanel); keyboardPanel.add(lowerPanel);
		
		mainPanel.add(catalogPanel); mainPanel.add(keyboardPanel);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}*/
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingID();
		
		VendingMachine vm = new VendingMachine(IDNumber);
		
		MainWindow w = new MainWindow();
		w.catalog.setText(vm.getCatalog().toStringGui());
	}
	
}
