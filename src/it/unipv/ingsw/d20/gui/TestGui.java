package it.unipv.ingsw.d20.gui;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;

public class TestGui {

	public static void main(String[] args) {
		VendingMachine m=new VendingMachine("macchinetta", 543.76);
		
		CustomerGui gui= new CustomerGui();
		Controller c=new Controller (m,gui);

		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}

}
