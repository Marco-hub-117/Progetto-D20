package it.unipv.ingsw.d20.vendingmachine.gui;

import javax.swing.JFrame;

import it.unipv.ingsw.d20.vendingmachine.controller.Controller;
import it.unipv.ingsw.d20.vendingmachine.gui.customer.CustomerGui;
import it.unipv.ingsw.d20.vendingmachine.gui.operator.OperatorGui;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageDescription;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;

public class TestGui {

	public static void main(String[] args) {
		VendingMachine m=new VendingMachine("macchinetta", 543.76);
		BeverageCatalog bvcat=m.getCatalog();
		BeverageDescription b1=new BeverageDescription("A","caffè espresso", 0.5);
		BeverageDescription b2=new BeverageDescription("B","caffè macchiato", 0.7);
		b1.addIngredient(Ingredients.COFFEE);
		b1.addIngredient(Ingredients.WATER);
		b2.addIngredient(Ingredients.COFFEE);
		b2.addIngredient(Ingredients.WATER);
		b2.addIngredient(Ingredients.MILK);
		bvcat.addBeverageDescription(b1);
		bvcat.addBeverageDescription(b2);
		
		CustomerGui gui= new CustomerGui();
		Controller c=new Controller (m,gui);

		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}

}
