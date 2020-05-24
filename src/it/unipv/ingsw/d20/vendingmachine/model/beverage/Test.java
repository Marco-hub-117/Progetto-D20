package it.unipv.ingsw.d20.vendingmachine.model.beverage;
//hhh
import it.unipv.ingsw.d20.vendingmachine.model.beverage.exceptions.DeliveryFailedException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.InvalidPaymentException;

public class Test {

	public static void main(String[] args) {
		BeverageCatalog catalogo=new BeverageCatalog();
		BeverageDescription b1=new BeverageDescription("A","caffè espresso", 0.5);
		b1.addIngredient(Ingredients.COFFEE);
		b1.addIngredient(Ingredients.WATER);
		BeverageDescription b2=new BeverageDescription("B","caffè macchiato", 0.7);
		b2.addIngredient(Ingredients.COFFEE);
		b2.addIngredient(Ingredients.WATER);
		b2.addIngredient(Ingredients.MILK);
		
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
