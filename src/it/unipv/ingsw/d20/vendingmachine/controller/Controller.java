package it.unipv.ingsw.d20.vendingmachine.controller;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.gui.customer.Pulsante;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;


public class Controller {
	private VendingMachine m;
	private CustomerGui gui;
	private OperatorGui opgui;
		
	public Controller(VendingMachine m, CustomerGui gui) {
		this.gui=gui;
		this.m=m;
		setText();
		addListenerA();
		addListenerB();
		addListenerOp();
	}
	public Controller(VendingMachine m, OperatorGui gui) {
		this.m=m;
		this.opgui=gui;
		setTankText();
		addListenerTank();
	}
	
	private void setText() {
		gui.setText(m.getCatalog().toString());
	}
	private void addListenerA() {
		for(Pulsante puls:gui.getLA()) {
			puls.addActionListener(new ListenerA(puls.getValue(), m, gui)); 
		}
	}
	private void addListenerB() {
		for(Pulsante puls:gui.getLB()) {
			puls.addActionListener(new ListenerB(puls.getValue(), m, gui));
		}
	}
	private void addListenerKey() {
		gui.getIn().addActionListener(new ListenerKey(true,m, gui));
		gui.getOut().addActionListener(new ListenerKey(false,m, gui));
	}
	private void addListenerOp() {
		gui.getOperator().addActionListener(new ListenerOperator(m, gui));
	}
	
	private void setTankText() {
		HashMap<String,Double> tankLevels=new HashMap<String, Double>();
		tankLevels=m.getTanksLevels();
		System.out.println(tankLevels);
		int k=0;
		for(Map.Entry<String, Double> i : tankLevels.entrySet()) {
			System.out.println("settt");
			opgui.setElements(i.getKey(),i.getValue()+"",k);
			k++;
		}
	}
	private void addListenerTank() {
		Pulsante[] puls=new Pulsante[m.nTank()];
		puls=opgui.getPulsanti();
		for(int i=0; i<m.nTank(); i++) {
			puls[i].addActionListener(new ListenerTank(puls[i].getPos(), m, opgui));
		}
		
	}

}


