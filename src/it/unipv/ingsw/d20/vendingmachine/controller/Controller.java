package it.unipv.ingsw.d20.vendingmachine.controller;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.CodeListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.CoinListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.KeyListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.TankListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.ToCustomerListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.ToOperatorListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.WindowClosingListener;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.RefillMachineException;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.WithdrawAmountException;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerButton;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorButton;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;

/**
 * Classe che si occupa di gestire la connessione tra il modello della
 * vending machine e la GUI.
 *
 */
public class Controller {
	private VendingMachine vm;
	private CustomerGui userGui;
	private OperatorGui opGui;
	
	/**
	 * Costruttore usato per l'interfaccia grafica del cliente
	 * @param m il modello rappresentato dalla classe VendingMachine
	 * @param gui la GUI
	 */
	public Controller(VendingMachine m, CustomerGui gui) {
		this.userGui = gui;
		this.vm = m;
		setCatalog();
		addCodeListener();
		addCashListener();
		addKeyListener();
		addToOperatorListener();
		userGui.addWindowListener(new WindowClosingListener(vm));
	}
	
	/**
	 * Costruttore usato per l'interfaccia grafica dell'operatore
	 * @param m il modello rappresentato dalla classe VendingMachine
	 * @param gui la GUI
	 */
	public Controller(VendingMachine m, OperatorGui gui) {
		this.vm = m;
		this.opGui = gui;
		setTankInfo();
		addTankListener();
		addWithdrawCashListener();
		addToCustomerListener();
		opGui.addWindowListener(new WindowClosingListener(vm));
	}

	private void setCatalog() { //imposta la TextArea per visualizzare il catalogo della vending machine
		userGui.setCatalog(vm.getCatalog().toStringGui());
	}

	private void addCodeListener() {
		for (CustomerButton button : userGui.getCodeButtons()) { //aggiunge i listener ai pulsanti per selezionare la bevanda nell'interfaccia cliente
			button.addActionListener(new CodeListener(button.getValue(), vm, userGui)); 
		}
	}

	private void addCashListener() {
		for (CustomerButton button : userGui.getCashButtons()) { //aggiunge i listener ai pulsanti per l'inserimento di monete nell'interfaccia cliente
			button.addActionListener(new CoinListener(button.getValue(), vm, userGui));
		}
	}

	private void addKeyListener() { //aggiunge i listener ai pulsanti per la chiavetta nell'interfaccia cliente
		userGui.getInsertKeyButton().addActionListener(new KeyListener(true, vm, userGui));
		userGui.getEjectKeyButton().addActionListener(new KeyListener(false, vm, userGui));
		userGui.getEjectKeyButton().setEnabled(false); //disabilito preventivamente il pulsante per estrarre una chiavetta
	}

	private void addToOperatorListener() { //aggiunge il listener al pulsante per entrare nella modalità operatore
		userGui.getOperatorButton().addActionListener(new ToOperatorListener(vm, userGui));
	}

	private void addToCustomerListener() { //aggiunge il listener al pulsante per entrare nella modalità cliente
		opGui.getExitButton().addActionListener(new ToCustomerListener(vm, opGui));
	}

	private void setTankInfo() { //imposta il nome dei tank e i livelli attuali
		HashMap<Ingredients,Double> tankLevels = new HashMap<Ingredients, Double>();
		tankLevels = vm.getTanksLevels();
		int count = 0;
		String key = "";
		String value = "";
		for(Map.Entry<Ingredients, Double> entry : tankLevels.entrySet()) {
			key = String.valueOf(entry.getKey());
			value = String.valueOf(entry.getValue());
			opGui.setElement(key, value, count);
			count++;
		}
	}

	private void addTankListener() { //aggiunge i listener ai pulsanti dell'interfaccia Operatore
		OperatorButton[] tankButton = new OperatorButton[vm.getTankNumber()];
		tankButton = opGui.getButtons();

		for(int i = 0; i < vm.getTankNumber(); i++) {
			tankButton[i].addActionListener(new TankListener(tankButton[i].getPosition(), vm, opGui));
		}
	}

	private void addWithdrawCashListener() { //c'è un bug, i soldi non vengono effettivamente prelevati
		opGui.getWithdrawCashButton().addActionListener(al -> {
			try {
				double buffAmount = vm.withdrawAmount();
				if (buffAmount > 0) 
					JOptionPane.showMessageDialog(null, "Sono stati ritirati €" + String.format("%.2f", buffAmount));
				else 
					JOptionPane.showMessageDialog(null, "Non si possono ritirare ulteriori monete.");
			} catch (WithdrawAmountException | RefillMachineException e1) {
				e1.printStackTrace();
			}
		});
	}

}


