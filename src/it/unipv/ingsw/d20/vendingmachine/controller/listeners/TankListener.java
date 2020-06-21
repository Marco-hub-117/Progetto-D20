package it.unipv.ingsw.d20.vendingmachine.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorButton;
import it.unipv.ingsw.d20.vendingmachine.view.operator.OperatorGui;

public class TankListener implements ActionListener {
	private VendingMachine vm;
	private OperatorGui gui;
	private int pos;
	
	public TankListener(int pos, VendingMachine vm, OperatorGui gui) {
		this.pos = pos;
		this.vm = vm;
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		OperatorButton p = gui.getButtons()[pos];
		vm.refillTanks(p.getTankId()); 
		
		//aggiorno la grafica
		HashMap<Ingredients,Double> tankLevels = new HashMap<Ingredients, Double>();
		tankLevels = vm.getTanksLevels();
		
		int count = 0;
		for(Map.Entry<Ingredients, Double> entry : tankLevels.entrySet()) {
			gui.setElements(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()), count);
			count++;
		}
	}

}
