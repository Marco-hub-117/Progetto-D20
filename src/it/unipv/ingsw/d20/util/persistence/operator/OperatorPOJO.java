package it.unipv.ingsw.d20.util.persistence.operator;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

/**
 * Classe che serve come "contenitore" di informazioni di una table Operator del database.
 * 
 */
public class OperatorPOJO {
	
	private String code;
	private String name;
	private String username;
	private String password;
	private String type;
	
	public OperatorPOJO(String code, String name, String username, String password, String type) {
		this.code = code;
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "OperatorPOJO [code=" + code + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", type=" + type + "]";
	}
	
	
	
}
