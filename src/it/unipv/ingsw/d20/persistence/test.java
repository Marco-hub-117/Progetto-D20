package it.unipv.ingsw.d20.persistence;

import java.util.ArrayList;

import it.unipv.ingsw.d20.persistence.BeverageDescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.persistence.BeverageDescription.IBeverageDescriptionDao;
import it.unipv.ingsw.d20.persistence.BvCatalog.BvCatalogPOJO;
import it.unipv.ingsw.d20.persistence.BvCatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.persistence.ingredientRecipe.IingredientRecipeDao;
import it.unipv.ingsw.d20.persistence.ingredientRecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.persistence.sale.ISaleDao;
import it.unipv.ingsw.d20.persistence.sale.SalePOJO;
import it.unipv.ingsw.d20.persistence.vending.IVendingDao;

public class test {

	public static void main(String[] args) {
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		
		IVendingDao a = pf.getVendingDao();
		System.out.println(a.getAddressById("id3"));
		
		ISaleDao b = pf.getSaleDao();
		SalePOJO s = b.getSaleById("s1");
		System.out.println(s.getIdSale()+" "+s.getIdBeverage()+" "+s.getIdVending()+" "+s.getDate());
		
		SalePOJO prova = new SalePOJO("s2","id1","Caffè","20-03-06");
		//b.addSale(prova);
		
		ArrayList<SalePOJO> all = b.getAllSaleByIdVending("id1","20-03-06");
		for(SalePOJO sp : all) {
			System.out.println(sp.getIdSale()+" "+sp.getIdBeverage()+" "+sp.getIdVending()+" "+sp.getDate());
		}
		
		IBvCatalogDao c = pf.getBvCatalogDao();
		ArrayList<BvCatalogPOJO> all2 = c.getBeverageCatalogByType(2);
		for(BvCatalogPOJO bv : all2) {
			System.out.println(bv.getIdBevDesc());
		}
		
		IBeverageDescriptionDao d = pf.getBeverageDescriptionDao();
		System.out.println(d.getPriceByBevName("The"));
		
		
		IingredientRecipeDao e = pf.getIngredientRecipeDao();
		// IngredientRecipePOJO z = new IngredientRecipePOJO("pincoPalla", "water", 2);
		// e.addIngredientRecipe(z);
		
		/*
		ArrayList<IngredientRecipePOJO> all3 = new ArrayList<>();
		for (int i=0;i<3;i++) {
			IngredientRecipePOJO z3 = new IngredientRecipePOJO("pincoPalla", "water", 3+i);
			all3.add(z3);
		}
		e.addIngredientRecipe(all3); 
		*/
		BeverageDescriptionPOJO bv = new BeverageDescriptionPOJO("fanta", 2, "pincoPalla");
		d.addBeverageDescription(bv);
		
		ArrayList<IngredientRecipePOJO> divina = new ArrayList<>();
		for (int i=0;i<2;i++) {
			IngredientRecipePOJO z3 = new IngredientRecipePOJO("pincoPalla2", "latte", i);
			divina.add(z3);
		}
		
		BeverageDescriptionPOJO birra = new BeverageDescriptionPOJO("Peroni", 2, "pincoPalla2");
		d.addBeverageDescription(birra, divina);

	}

}
