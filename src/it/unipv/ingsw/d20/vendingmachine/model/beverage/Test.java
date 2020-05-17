package it.unipv.ingsw.d20.vendingmachine.model.beverage;

import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

public class Test {

	public static void main(String[] args) {
		BeverageCatalog catalogo=new BeverageCatalog();
		BeverageDescription b1=new BeverageDescription("caffè espresso", 0.5);
		b1.addIngredient(Ingredients.COFFEE, 0.2);
		b1.addIngredient(Ingredients.WATER, 0.5);
		BeverageDescription b2=new BeverageDescription("caffè macchiato", 0.7);
		b2.addIngredient(Ingredients.COFFEE, 0.2);
		b2.addIngredient(Ingredients.WATER, 0.8);
		b2.addIngredient(Ingredients.MILK, 0.3);
		
		catalogo.addBeverageDescription(b1);
		catalogo.addBeverageDescription(b2);
		System.out.println(catalogo.toString());
		
		Sale sale;
		try {
			sale = new Sale(b2, 10);
			System.out.println(sale.toString());
		} catch (InvalidPaymentException e) {
			e.printStackTrace();
		} catch (DeliveryFailedException e) {
			e.printStackTrace();
		}

		
		

	}

}
