package it.unipv.ingsw.d20.model.beverage;


import it.unipv.ingsw.d20.model.beverage.BeverageCatalog;


public class Test {

	public static void main(String[] args) {
		BeverageCatalog b=new BeverageCatalog();
		b.addBeverageDescription(new BeverageDescription("CAFFE", 2000000.98));
		b.addBeverageDescription(new BeverageDescription("LATTE", 345.34));
		System.out.println(b.getBeverageDesc("LATTE"));
		System.out.println(b.getBeverageDesc("CICCIO"));

	}

}
