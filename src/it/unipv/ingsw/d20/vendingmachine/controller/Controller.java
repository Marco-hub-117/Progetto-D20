package it.unipv.ingsw.d20.vendingmachine.controller;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.vendingmachine.controller.listeners.CodeListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.CoinListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.KeyListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.TankListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.ToCustomerListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.ToOperatorListener;
import it.unipv.ingsw.d20.vendingmachine.controller.listeners.WindowClosingListener;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerButton;
import it.unipv.ingsw.d20.vendingmachine.view.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorButton;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;

public class Controller {
	private VendingMachine vm;
	private CustomerGui userGui;
	private OperatorGui opGui;
		
	public Controller(VendingMachine m, CustomerGui gui) {
		this.userGui = gui;
		this.vm = m;
		
		setCatalog();
		
		addCodeListener();
		addListenerB();
		addKeyListener();
		addListenerOp();
		
		userGui.addWindowListener(new WindowClosingListener(vm));
	}

	public Controller(VendingMachine m, OperatorGui gui) {
		this.vm = m;
		this.opGui = gui;
		
		setTankText();
		
		addListenerTank();
		addListenerCust();
		
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
	
	private void addListenerB() {
		for (CustomerButton button : userGui.getCashButtons()) { //aggiunge i listener ai pulsanti dei soldi nell'interfaccia cliente
			button.addActionListener(new CoinListener(button.getValue(), vm, userGui));
		}
	}
	
	private void addKeyListener() { //aggiunge i listener ai pulsanti per la chiavetta nell'interfaccia cliente
		userGui.getInsertKeyButton().addActionListener(new KeyListener(true, vm, userGui));
		userGui.getEjectKeyButton().addActionListener(new KeyListener(false, vm, userGui));
		userGui.getEjectKeyButton().setEnabled(false); //disabilito preventivamente il pulsante per estrarre una chiavetta
	}
	
	private void addListenerOp() { //aggiunge il listener al pulsante per entrare nella modalità operatore
		userGui.getOperatorButton().addActionListener(new ToOperatorListener(vm, userGui));
	}
	
	private void addListenerCust() { //aggiunge il listener al pulsante per entrare nella modalità cliente
		opGui.getCustomer().addActionListener(new ToCustomerListener(vm, opGui));
	}
	
	private void setTankText() { //imposta il nome dei tank e i livelli attuali
		HashMap<Ingredients,Double> tankLevels=new HashMap<Ingredients, Double>();
		tankLevels=vm.getTanksLevels();
		int k=0;
		for(Map.Entry<Ingredients, Double> i : tankLevels.entrySet()) {
			opGui.setElements(String.valueOf(i.getKey()),i.getValue()+"",k);
			k++;
		}
	}
	
	private void addListenerTank() { //aggiunge i listener ai pulsanti dell'interfaccia Operatore
		OperatorButton[] puls=new OperatorButton[vm.getTankNumber()];
		puls=opGui.getPulsanti();
		for(int i=0; i<vm.getTankNumber(); i++) {
			puls[i].addActionListener(new TankListener(puls[i].getPos(), vm, opGui));
		}
	}

}


