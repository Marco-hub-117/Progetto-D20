package it.unipv.ingsw.d20.util.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingsw.d20.util.persistence.beveragecatalog.BvCatalogPOJO;
import it.unipv.ingsw.d20.util.persistence.beveragecatalog.IBvCatalogDao;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.IBeverageDescriptionDao;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IIngredientRecipeDao;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.util.persistence.sale.ISaleDao;
import it.unipv.ingsw.d20.util.persistence.sale.SalePOJO;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;

public class test {

	public static void main(String[] args) {
		
		PersistenceFacade pf = PersistenceFacade.getInstance();
		
		IVendingDao a = pf.getVendingDao();
		System.out.println(a.getVendingStatusById("id3"));
		a.updateVendingStatus("id1", VendingMachineStatus.OFF);
		
		IIngredientRecipeDao IrDao = pf.getIngredientRecipeDao();
		ArrayList<IngredientRecipePOJO> result = new ArrayList<>();
		result.addAll(IrDao.getAllIngredientRecipeByIdRecipe("idRecipe1"));
		for (IngredientRecipePOJO irp : result) {
			System.out.println(irp);
		}
		
		IBeverageDescriptionDao IbvDao = pf.getBeverageDescriptionDao();
		BeverageDescriptionPOJO bvpojo = IbvDao.getBeverageDescriptionByBevName("Caffè latte");
		System.out.println(bvpojo);
		
		IBvCatalogDao ibvcat = pf.getBvCatalogDao();
		BeverageCatalog catalogo = ibvcat.getBeverageCatalog(2);
		System.out.println(catalogo);
		
		VendingLocalIO local = pf.getVendingLocalIO();
		//  local.saveCatalogIntoLocal(catalogo); // da fare solo quando necessario aggiornare catalogo locale.
		
		BeverageCatalog catalogo2 = local.getCatalogFromLocal();
		System.out.println(catalogo2);
		
		
		
		
		
		
		/*
		ISaleDao b = pf.getSaleDao();
		SalePOJO prova = new SalePOJO("id3","Caffï¿½","2020-06-07 18:47:00");
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
