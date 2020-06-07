package it.unipv.ingsw.d20.vendingmachine.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Tank;

public class testCatalogo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VendingMachine v = new VendingMachine("id1");
		v.getCatalogFromLocal();
		v.saveCatalogIntoLocal(); 
		v.getTanksFromLocal();
		v.saveTankIntoLocal();

	}

}
