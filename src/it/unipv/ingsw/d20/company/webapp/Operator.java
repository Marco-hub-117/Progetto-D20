package it.unipv.ingsw.d20.company.webapp;

//DA ELIMINARE UNA VOLTA COLLEGATO CON DB

public class Operator {
	private String name;
	private String username;
	private String password;
	private String type;
	
	public Operator(String name, String username, String password, String type) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public Operator checkLogIn (String username, String password) throws InvalidPasswordException{
		if (this.username.equals(username) && this.password.equals(password))
			return this;
		else
			throw new InvalidPasswordException();
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
	
}
