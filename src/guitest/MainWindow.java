package guitest;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private static int WIDTH = 1100, HEIGHT = 675;
	
	private CatalogPanel catalogPanel;
	private KeyboardPanel keyboardPanel;
	
	public MainWindow() {
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		
		Container container = getContentPane();
		JPanel mainPanel = new JPanel();
		container.add(mainPanel);
		mainPanel.setLayout(new GridLayout(1, 2));
		
		catalogPanel = new CatalogPanel();
		keyboardPanel = new KeyboardPanel();
		
		mainPanel.add(catalogPanel); mainPanel.add(keyboardPanel);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		
		String IDNumber = v.getVendingID();
		
		VendingMachine vm = new VendingMachine(IDNumber);
		
		MainWindow gui= new MainWindow();
		Controller c=new Controller (vm,gui);

		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	public void setCatalog(String catalog) {
		catalogPanel.setCatalog(catalog);
	}
	
	public String getDisplay() {
		return keyboardPanel.getDisplay();
	}
	
	public void setDisplay(String creditToString) {
		keyboardPanel.setDisplay(creditToString);
	}
	
	public UserButton[] getCodeButtons() {
		return keyboardPanel.getCodeButtons();
	}

	public UserButton[] getCashButtons() {
		return keyboardPanel.getCashButtons();
	}

	public JButton getInsertKeyButton() {
		return keyboardPanel.getInsertKeyButton();
	}

	public JButton getEjectKeyButton() {
		return keyboardPanel.getEjectKeyButton();
	}

	public JButton getOperatorButton() {
		return catalogPanel.getOperatorButton();
	}
	
}
