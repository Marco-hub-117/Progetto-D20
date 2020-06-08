package it.unipv.ingsw.d20.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingsw.d20.persistence.beveragecatalog.BvCatalogPOJO;
import it.unipv.ingsw.d20.persistence.beveragecatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.persistence.beveragedescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.persistence.beveragedescription.IBeverageDescriptionDao;
import it.unipv.ingsw.d20.persistence.ingredientrecipe.IingredientRecipeDao;
import it.unipv.ingsw.d20.persistence.ingredientrecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.persistence.sale.ISaleDao;
import it.unipv.ingsw.d20.persistence.sale.SalePOJO;
import it.unipv.ingsw.d20.persistence.vending.IVendingDao;

public class test {

	public static void main(String[] args) {
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		
		IVendingDao a = pf.getVendingDao();
		System.out.println(a.getVendingStatusById("id3"));
		
		IingredientRecipeDao IrDao = pf.getIngredientRecipeDao();
		ArrayList<IngredientRecipePOJO> result = new ArrayList<>();
		result.addAll(IrDao.getAllIngredientRecipeByIdRecipe("idRecipe1"));
		for (IngredientRecipePOJO irp : result) {
			System.out.println(irp);
		}
		
		
		/*
		ISaleDao b = pf.getSaleDao();
		SalePOJO prova = new SalePOJO("id3","Caffè","2020-06-07 18:47:00");
		b.addSale(prova);
		System.out.println(prova.toString());
		SalePOJO s = b.getSaleByKey("id3", prova.getDate()); 
		System.out.println(s.toString());
		ArrayList<SalePOJO> alsp = new ArrayList<>();
		alsp = b.getAllSaleByIdVending("id3");
		for(SalePOJO s2 : alsp) {
			System.out.println("Al: "+s2.toString());
		}
		

		//b.addSale(prova);
		/*
		ArrayList<SalePOJO> all = b.getAllSaleByIdVending("id3","20-03-06");
		for(SalePOJO sp : all) {
			System.out.println(sp.getIdBeverage()+" "+sp.getIdVending()+" "+sp.getDate());
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
		/*
		BeverageDescriptionPOJO bv = new BeverageDescriptionPOJO("fanta", 2, "pincoPalla");
		d.addBeverageDescription(bv);
		
		ArrayList<IngredientRecipePOJO> divina = new ArrayList<>();
		for (int i=0;i<2;i++) {
			IngredientRecipePOJO z3 = new IngredientRecipePOJO("pincoPalla2", "latte", i);
			divina.add(z3);
		}
		
		BeverageDescriptionPOJO birra = new BeverageDescriptionPOJO("Peroni", 2, "pincoPalla2");
		d.addBeverageDescription(birra, divina);
		*/
	}

}
