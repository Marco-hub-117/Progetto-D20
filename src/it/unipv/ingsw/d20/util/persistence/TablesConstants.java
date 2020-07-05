package it.unipv.ingsw.d20.util.persistence;

/**
 * Contiene stringhe statiche con i nomi delle table del DB e i relativi attributi.
 *
 */
public class TablesConstants {
	public static String vendingTable = "Vending";
	public static String idVendingAttr = "idVending";
    public static String locationAttr= "location";
    public static String typeAttr= "type";
    
    public static String saleTable = "Sale";
	public static String idVendingSaleAttr = "idVending";
    public static String idBeverageSaleAttr= "idBeverage";
    public static String dateAttr= "date";
    
    public static String beverageCatalogTable = "BvCatalog";
	public static String idBvDescAttr = "idBvDesc";
    public static String typeCatAttr= "type";
    
    public static String beverageDescriptionTable = "BeverageDescription";
	public static String bevNameAttr = "bevName";
    public static String priceAttr= "price";
    public static String idRecipeDescAttr= "idRecipe";
    
    public static String ingredientRecipeTable = "IngredientRecipe";
    public static String idRecipeAttr = "idRecipe";
    public static String ingredientNameAttr= "ingredientName";
    public static String quantityAttr= "quantity";
			
	public static String paymentKeyTable = "PaymentKey";
	public static String codeAttr = "code";
    public static String creditAttr= "credit";
	
	public static String operatorTable = "Operator";
	public static String cfAttr = "cf";
    public static String nameAttr= "name";
    public static String passwordAttr= "password";
    public static String typeOpAttr = "type";

}
