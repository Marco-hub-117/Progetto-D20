package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorButton;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;

/**
 * Listener dei tasti che permettono di riempire i diversi tank.
 *
 */
public class TankListener implements ActionListener {
	
	private VendingMachine vm;
	private OperatorGui gui;
	private int position;
	
	/**
	 * Istanzia il valore del tasto annesso, la vending machine e la gui.
	 * @param posizione del tasto nell'array dei tasti
	 * @param vm istanza di vending machine
	 * @param operatorGui gui dell'operatore
	 */
	public TankListener(int position, VendingMachine vm, OperatorGui gui) {
		this.position = position;
		this.vm = vm;
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		OperatorButton opButton = gui.getButtons()[position];
		vm.refillTanks(opButton.getTankId()); //riempie il tank relativo al bottone
		
		//aggiorno la grafica
		HashMap<Ingredients,Double> tankLevels = new HashMap<Ingredients, Double>();
		tankLevels = vm.getTanksLevels();
		int count = 0;
		String key = "";
		String value = "";
		for(Map.Entry<Ingredients, Double> entry : tankLevels.entrySet()) {
			key = String.valueOf(entry.getKey());
			value = String.valueOf(entry.getValue());
			gui.setElements(key, value, count);
			count++;
		}
	}
	
}