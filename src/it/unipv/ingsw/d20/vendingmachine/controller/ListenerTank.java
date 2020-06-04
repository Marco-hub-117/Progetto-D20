package it.unipv.ingsw.d20.vendingmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.Pulsante;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.exceptions.RefillMachineException;

public class ListenerTank implements ActionListener{
	private VendingMachine m;
	private OperatorGui gui;
	private int pos;
	
	public ListenerTank(int pos, VendingMachine m, OperatorGui gui) {
		this.pos=pos;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Pulsante p=gui.getPulsanti()[pos];
		try {
			m.setTankLevel(p.getIdTank());
		} catch (RefillMachineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//aggiornare solo serbatoio corretto e non tutti i serbatoi
		HashMap<String,Double> tankLevels=new HashMap<String, Double>();
		tankLevels=m.getTanksLevels();
		System.out.println(tankLevels);
		int k=0;
		for(Map.Entry<String, Double> i : tankLevels.entrySet()) {
			System.out.println("settt");
			gui.setElements(i.getKey(),i.getValue()+"",k);
			k++;
		}
	}

}
