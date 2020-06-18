package it.unipv.ingsw.d20.company.webapp;

import java.util.List;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.BeverageDescriptionPOJO;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.IBeverageDescriptionDao;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IIngredientRecipeDao;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.IngredientRecipePOJO;
import it.unipv.ingsw.d20.util.persistence.key.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.key.KeyPOJO;
import it.unipv.ingsw.d20.util.persistence.operator.IOperatorDao;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorPOJO;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

public class WebAppController {
	PersistenceFacade facade;
	IVendingDao vendingsManager;
	IOperatorDao operatorsManager;
	IKeyDao keysManager;
	IBeverageDescriptionDao beveragesManager;
	IIngredientRecipeDao ingredientsManager;
	
	public WebAppController() {
		facade= PersistenceFacade.getInstance();
		vendingsManager=facade.getVendingDao();
		operatorsManager=facade.getOperatorDao();
		keysManager=facade.getKeyDao();
		beveragesManager=facade.getBeverageDescriptionDao();	
	}
		
	//IN FASE DI ELABORAZIONE
	
	public OperatorPOJO getLoggedOperator() {
		/*Operator operator=Operators.getMy(req.getParameter("username"));
		loggedOperator=operator.checkLogIn(req.getParameter("username"), req.getParameter("inputPassword"));
		return loggedOperator;*/
		return null;
	}
	
	public Object doNothing() {
		return null;
	}

	public List<VendingPOJO> getAllVendingMachines() {
		return vendingsManager.getAllVendings();
	}
	
	public VendingPOJO getVendingMachine(String id) {
		return vendingsManager.getVending(id);
	}
	//presa una vending POJO, dovrei poter prelevare location, type, status, amount, tanksLevels, tanksTemps
	
	//public void setTankTemps(String id, Double[] temps);
	
	
	public List<OperatorPOJO> getAllOperators() {
		return operatorsManager.getAllOperators();
	}
	
	public OperatorPOJO getOperator(String code) {
		return operatorsManager.getOperator(code);
	}
	
	public void addOperator(String code, String name, String username, String password, String type) {
		operatorsManager.addOperator(code, name, username, password, type);
	}
	
	public List<KeyPOJO> getAllKeys() {
		return keysManager.getAllKeys();
	}
	
	public KeyPOJO getKey(String serialCode) {
		return keysManager.getKey(serialCode);
	}
	
	public void addKey(String serialCode, double credit) {
		keysManager.addKey(serialCode, credit);
	}
	
	//magari toglierlo se non necessario (cioè prendo il credito dirett. dal pojo)
	public double getKeyCredit(String serialCode) {
		return keysManager.getCredit(serialCode);
	}
	
	public void deactivateKey(String serialCode) {
		keysManager.deactivateKey(serialCode);
	}
	
	public List<BeverageDescriptionPOJO> getAllBeverageDescriptions() {
		return beveragesManager.getAllBeverageDescriptions();
	}
	public BeverageDescriptionPOJO getBeverageDescription(String beverageName) {
		return beveragesManager.getBeverageDescriptionByBevName(beverageName);
	}
	public List<IngredientRecipePOJO> getIngredients(String idRecipe) {
		return ingredientsManager.getAllIngredientRecipeByIdRecipe(idRecipe);
	}
	public void updateIngredients (String idRecipe, String ingredientName, double quantity) {
		ingredientsManager.updateIngredientRecipe(idRecipe, ingredientName, quantity);
	}
}
