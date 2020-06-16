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
	private VendingMachine m;
	private OperatorGui gui;
	private int pos;
	
	public TankListener(int pos, VendingMachine m, OperatorGui gui) {
		this.pos=pos;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		OperatorButton p = gui.getPulsanti()[pos];
		m.setTankLevel(p.getIdTank()); 
		
		//aggiorno la grafica
		HashMap<Ingredients,Double> tankLevels=new HashMap<Ingredients, Double>();
		tankLevels=m.getTanksLevels();
		int k=0;
		for(Map.Entry<Ingredients, Double> i : tankLevels.entrySet()) {
			gui.setElements(String.valueOf(i.getKey()),i.getValue()+"",k);
			k++;
		}
	}

}
