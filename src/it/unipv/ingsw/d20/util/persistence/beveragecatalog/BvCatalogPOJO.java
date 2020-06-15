package it.unipv.ingsw.d20.util.persistence.beveragecatalog;

public class BvCatalogPOJO {
	
	private String idBevDesc;
	private int type;
	
	public BvCatalogPOJO(String idBevDesc, int type) {

		this.idBevDesc = idBevDesc;
		this.type = type;
	}

	public String getIdBevDesc() {
		return idBevDesc;
	}

	public void setIdBevDesc(String idBevDesc) {
		this.idBevDesc = idBevDesc;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
