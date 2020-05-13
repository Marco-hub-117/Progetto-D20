package it.unipv.ingsw.d20.model.beverage;


import it.unipv.ingsw.d20.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.model.paymentsystem.payment.exceptions.InvalidPaymentException;


public class Test {

	public static void main(String[] args) {
		BeverageCatalog catalogo=new BeverageCatalog();
		Ingredient caffe=new Ingredient("CAFFE");
		Ingredient the=new Ingredient("THE");
		Ingredient latte=new Ingredient("LATTE");
		Ingredient acqua=new Ingredient("ACQUA");
		BeverageDescription b1=new BeverageDescription("caff� espresso", 0.5);
		b1.addIngredient(caffe, 0.2);
		b1.addIngredient(acqua, 0.5);
		BeverageDescription b2=new BeverageDescription("caff� macchiato", 0.7);
		b2.addIngredient(caffe, 0.2);
		b2.addIngredient(acqua, 0.8);
		b2.addIngredient(latte, 0.3);
		
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
