package it.unipv.ingsw.d20.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.d20.gui.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class ListenerB implements ActionListener{
	private int value;	
	private VendingMachine m;
	private CustomerGui gui;
	
	public ListenerB(int value, VendingMachine m, CustomerGui gui) {
		this.value=value;
		this.m=m;
		this.gui=gui;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s;
		double p;
		switch(this.value) {
		case(1):
			s="";
			s=s+gui.getAmount();
			p=0.0;
			if(!s.isEmpty())	p=Double.parseDouble(s);
			p=p+1;
			gui.setAmount(""+p);
		break;
		case(2):
			s="";
			s=s+gui.getAmount();
			p=0.0;
			if(!s.isEmpty())	p=Double.parseDouble(s);
			p=p+2;
			gui.setAmount(""+p);
		break;
		case(5):
			s="";
			s=s+gui.getAmount();
			p=0.0;
			if(!s.isEmpty())	p=Double.parseDouble(s);
			p=p+0.05;
			gui.setAmount(""+p);
		break;
		case(10):
			s="";
			s=s+gui.getAmount();
			p=0.0;
			if(!s.isEmpty())	p=Double.parseDouble(s);
			p=p+0.10;
			gui.setAmount(""+p);
		break;
		case(20):
			s="";
			s=s+gui.getAmount();
			p=0.0;
			if(!s.isEmpty())	p=Double.parseDouble(s);
			p=p+0.20;
			gui.setAmount(""+p);
		break;
		case(50):
			s="";
			s=s+gui.getAmount();
			p=0.0;
			if(!s.isEmpty())	p=Double.parseDouble(s);
			p=p+0.50;
			gui.setAmount(""+p);
		break;
		}
	}
	
}
