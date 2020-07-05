package it.unipv.ingsw.d20.util.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.unipv.ingsw.d20.util.persistence.beveragecatalog.*;
import it.unipv.ingsw.d20.util.persistence.beveragedescription.*;
import it.unipv.ingsw.d20.util.persistence.ingredientrecipe.*;
import it.unipv.ingsw.d20.util.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.util.persistence.operator.IOperatorDao;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorPOJO;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorRdbDao;
import it.unipv.ingsw.d20.util.persistence.paymentKey.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyPOJO;
import it.unipv.ingsw.d20.util.persistence.paymentKey.KeyRdbDao;
import it.unipv.ingsw.d20.util.persistence.sale.*;
import it.unipv.ingsw.d20.util.persistence.vending.*;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.BeverageCatalog;
import it.unipv.ingsw.d20.vendingmachine.model.beverage.Ingredients;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.CashContainer;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.Sale;
import it.unipv.ingsw.d20.vendingmachine.model.tanks.Tank;

/**
 * Classe che raccoglie tutti i metodi delle classi DAO e LocalIO, in modo da avere un unico punto di accesso allo
 * strato di persistenza.
 * Implementata come un singleton.
 */
public class PersistenceFacade {
	
	private static PersistenceFacade instance;
	
	private final RdbOperations operations;
	private final IVendingDao vendingMachine;
	private final ISaleDao sale;
	private final IBvCatalogDao beverageCatalog;
	private final IBeverageDescriptionDao beverageDescription;
	private final IIngredientRecipeDao ingredientRecipe;
	private final IKeyDao key;
	private final IOperatorDao operator;
	private final VendingLocalIO localMachine;
	
	private PersistenceFacade() {
		operations=new RdbOperations();
		vendingMachine = new VendingRdbDao(operations);
		sale = new SaleRdbDao(operations);
		beverageCatalog = new BvCatalogRdbDao(operations);
		beverageDescription = new BeverageDescriptionRdbDao(operations);
		ingredientRecipe = new IngredientRecipeRdbDao(operations);
		key = new KeyRdbDao(operations);
		operator = new OperatorRdbDao(operations);
		localMachine = new VendingLocalIO();
	}
	
	public static synchronized PersistenceFacade getInstance() {
		
		if(instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}

	
	//Metodi di BeverageCatalog
	public ArrayList<BvCatalogPOJO> getBeverageCatalogByTypeRdb(int type){
		return beverageCatalog.getBeverageCatalogByType(type);
	}
	public BeverageCatalog getRdbBeverageCatalogRdb(int type) {
		return beverageCatalog.getBeverageCatalog(type);
	}
	
	//Metodi di BeverageDescription
	public double getPriceByBevNameRdb(String bevName) {
		return beverageDescription.getPriceByBevName(bevName);
	}
	
	public void addBeverageDescriptionRdb(BeverageDescriptionPOJO bv) {
		beverageDescription.addBeverageDescription(bv);
	}
	public void addBeverageDescriptionRdb(BeverageDescriptionPOJO bv,ArrayList<IngredientRecipePOJO> ingr) {
		beverageDescription.addBeverageDescription(bv, ingr);
	}
	public BeverageDescriptionPOJO getBeverageDescriptionByBevNameRdb(String bevName) {
		return beverageDescription.getBeverageDescriptionByBevName(bevName);
	}
	public ArrayList<BeverageDescriptionPOJO> getAllBeverageDescriptionsRdb (){
		return beverageDescription.getAllBeverageDescriptions();
	}
	
	//Metodi di IngredientRecipe
	public void addIngredientRecipeRdb(ArrayList<IngredientRecipePOJO> ingr) {
		ingredientRecipe.addIngredientRecipe(ingr);
	}
	public void addIngredientRecipeRdb(IngredientRecipePOJO ingr) {
		ingredientRecipe.addIngredientRecipe(ingr);
	}
	public ArrayList<IngredientRecipePOJO> getAllIngredientRecipeByIdRecipeRdb(String idRecipe){
		return ingredientRecipe.getAllIngredientRecipeByIdRecipe(idRecipe);
	}
	public boolean updateIngredientRecipeRdb(String idRecipe, String ingredientName, double quantity) {
		return ingredientRecipe.updateIngredientRecipe(idRecipe, ingredientName, quantity);
	}
	
	//Metodi di Operator
	public ArrayList<OperatorPOJO> getAllOperatorsRdb(){
		return operator.getAllOperators();
	}
	public OperatorPOJO getOperatorRdb(String code) {
		return operator.getOperator(code);
	}
	public void addOperatorRdb(String code, String name, String password, String type) {
		operator.addOperator(code, name, password, type);
	}
	
	//Metodi di Key
	public ArrayList<KeyPOJO> getAllKeysRdb(){
		return key.getAllKeys();
	}
	public KeyPOJO getKeyRdb(String serialCode) {
		return key.getKey(serialCode);
	}
	public void addKeyRdb(int serialCode, double credit) {
		key.addKey(serialCode, credit);
	}
	public boolean updateCreditRdb(String serialCode, double credit) {
		return key.updateCredit(serialCode, credit);
	}
	public double getCreditRdb(String serialCode) {
		return key.getCredit(serialCode);
	}
	public void deactivateKeyRdb(String serialCode){
		key.deactivateKey(serialCode);
	}
	
	//Metodi di Sale
	public void addSaleRdb(SalePOJO saleEntry) throws SQLException{
		sale.addSale(saleEntry);
	}
	public SalePOJO getSaleByKeyRdb(String idVending, String date) {
		return sale.getSaleByKey(idVending, date);
	}
	public ArrayList<SalePOJO> getAllSalesByIdVendingRdb (String idVending){
		return sale.getAllSalesByIdVending(idVending);
	}
	
	//Metodi di Vending (DB)
	public void addVendingRdb(VendingPOJO vendingEntry) {
		vendingMachine.addVending(vendingEntry);
	}
	public ArrayList<VendingPOJO> getAllVendingsRdb(){
		return vendingMachine.getAllVendings();
	}
	public VendingPOJO getVendingRdb(String idVending) {
		return vendingMachine.getVending(idVending);
	}
	
	//Metodi di Vending (local)
	public BeverageCatalog getCatalogFromLocal() {
		return localMachine.getCatalogFromLocal();
	}
	public void saveCatalogIntoLocal (BeverageCatalog bvCatalog) {
		localMachine.saveCatalogIntoLocal(bvCatalog);
	}
	public HashMap<Ingredients,Tank> getTanksFromLocal() {
		return localMachine.getTanksFromLocal();
	}
	public void saveTankIntoLocal(HashMap<Ingredients,Tank> tankList) {
		localMachine.saveTankIntoLocal(tankList);
	}
	public String getVendingIDFromLocal() {
		return localMachine.getVendingIDFromLocal();
	}
	public void saveVendingIDIntoLocal(String IDNumber) {
		localMachine.saveVendingIDIntoLocal(IDNumber);
	}
	public String getVendingTypeFromLocal() {
		return localMachine.getVendingTypeFromLocal();
	}
	public CashContainer getCashContainerFromLocal() {
		return localMachine.getCashContainerFromLocal();
	}
	public void saveCashContainerIntoLocal(CashContainer cashContainer) {
		localMachine.saveCashContainerIntoLocal(cashContainer);
	}
	public void saveSaleIntoLocal(Sale sale) {
		localMachine.saveSaleIntoLocal(sale);
	}
	public List<String> getSaleListFromLocal(){
		return localMachine.getSaleListFromLocal();
	}
	public void emptyLocalSale() {
		localMachine.emptyLocalSale();
	}
}
