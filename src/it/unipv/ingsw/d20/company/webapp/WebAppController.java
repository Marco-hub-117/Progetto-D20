package it.unipv.ingsw.d20.company.webapp;

import java.util.LinkedList;
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
	private boolean limited; //E' true quando l'operatore loggato è un operatore e non un operatore remoto
	private Map<String, VendingMachineInfo> infoList;
	private enum OperatorType{
		Operator, RemoteOperator;
	}
	
	
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
		OperatorPOJO operator=lookForOperator(username);
		
		if (operator.getCode().equals(username) && operator.getPassword().equals(password)) {
			loggedOperator=getOperator(username);
		}
		else {
			throw new InvalidPasswordException();
		}
		
		checkLimitation();
		
	}
	
	private OperatorPOJO lookForOperator(String username) throws InvalidUserException {
		OperatorPOJO operator;
		if (getOperator(username)==null) {
			throw new InvalidUserException();
		}
		else {
			operator=getOperator(username);
		}
		return operator;
	}
	
	private void checkLimitation() {
		if (loggedOperator.getType().equals(OperatorType.Operator.toString())){
			setLimited(true);
		}
		else {
			setLimited(false);
		}
	}
	
	private void setLimited(boolean bool) {
		this.limited=bool;
	}
	
	public boolean isLimited() {
		return limited;
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
	
	public List<String> getIngredientsNames(String idRecipe) {
		List<String> ingredientsNames=new LinkedList<>();
		List<IngredientRecipePOJO> recipe= ingredientsManager.getAllIngredientRecipeByIdRecipe(idRecipe);
		
		for (IngredientRecipePOJO entry: recipe) {
			ingredientsNames.add(entry.getIngredientName());
		}
		
		if (ingredientsNames.size()<IngredientRecipePOJO.maxIngredients) {
			int ingredientNumber= ingredientsNames.size();
			int i;
			for (i=0; i<(IngredientRecipePOJO.maxIngredients-ingredientNumber); i++) {
				ingredientsNames.add("None");
			}
		}
		return ingredientsNames;
	}
	
	public List<Double> getIngredientsQuantities(String idRecipe) {
		List<Double> ingredientsQuantities=new LinkedList<>();
		List<IngredientRecipePOJO> recipe= ingredientsManager.getAllIngredientRecipeByIdRecipe(idRecipe);
		
		for (IngredientRecipePOJO entry: recipe) {
			ingredientsQuantities.add(entry.getQuantity());
		}
		
		if (ingredientsQuantities.size()<IngredientRecipePOJO.maxIngredients) {
			int ingredientNumber= ingredientsQuantities.size();
		
			int i;
			for (i=0; i<(IngredientRecipePOJO.maxIngredients-ingredientNumber); i++) {
				ingredientsQuantities.add(0.0);
			}
		}
		return ingredientsQuantities;
	}
	
}
