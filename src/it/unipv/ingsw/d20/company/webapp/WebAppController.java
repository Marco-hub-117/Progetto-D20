package it.unipv.ingsw.d20.company.webapp;

import java.util.List;
import java.util.Map;

import it.unipv.ingsw.d20.company.Company;
import it.unipv.ingsw.d20.company.VendingMachineInfo;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidPasswordException;
import it.unipv.ingsw.d20.company.webapp.exceptions.InvalidUserException;
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
	private PersistenceFacade facade;
	private IVendingDao vendingsManager;
	private IOperatorDao operatorsManager;
	private IKeyDao keysManager;
	private IBeverageDescriptionDao beveragesManager;
	private IIngredientRecipeDao ingredientsManager;
	private OperatorPOJO loggedOperator;
	private Map<String, VendingMachineInfo> infoList;
	
	public WebAppController() {
		facade= PersistenceFacade.getInstance();
		vendingsManager=facade.getVendingDao();
		operatorsManager=facade.getOperatorDao();
		keysManager=facade.getKeyDao();
		beveragesManager=facade.getBeverageDescriptionDao();	
		ingredientsManager=facade.getIngredientRecipeDao();
		infoList =Company.vendingMachineInfoList;
	}
		
	//IN FASE DI ELABORAZIONE
	
	public OperatorPOJO getLoggedOperator() {
		return loggedOperator;
	}
	
	public void setNotLogged() {
		loggedOperator=null;
	}

	public List<VendingPOJO> getAllVendingMachines() {
		return vendingsManager.getAllVendings();
	}
	
	public VendingPOJO getVendingMachine(String id) {
		return vendingsManager.getVending(id);
	}
	
	public VendingMachineInfo getVendingMachineInfo(String id) {
		return infoList.get(id);	
	}
	
	public List<OperatorPOJO> getAllOperators() {
		return operatorsManager.getAllOperators();
	}
	
	public OperatorPOJO getOperator(String code) {
		return operatorsManager.getOperator(code);
	}
	
	public void addOperator(String code, String name, String password, String type) {
		operatorsManager.addOperator(code, name, password, type);
	}
	
	public void checkOperatorLogIn (String username, String password) throws InvalidPasswordException, InvalidUserException{
		OperatorPOJO operator;
		if (getOperator(username)==null) {
			throw new InvalidUserException();
		}
		else {
			operator=getOperator(username);
		}
		
		if (operator.getCode().equals(username) && operator.getPassword().equals(password)) {
			loggedOperator=getOperator(username);
		}
		else {
			throw new InvalidPasswordException();
		}
	}
	
	public List<KeyPOJO> getAllKeys() {
		return keysManager.getAllKeys();
	}
	
	public KeyPOJO getKey(String serialCode) {
		return keysManager.getKey(serialCode);
	}
	
	public void addKey(int serialCode, double credit) {
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
