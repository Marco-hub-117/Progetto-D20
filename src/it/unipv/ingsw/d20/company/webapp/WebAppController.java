package it.unipv.ingsw.d20.company.webapp;

import java.util.List;

import it.unipv.ingsw.d20.util.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.util.persistence.key.IKeyDao;
import it.unipv.ingsw.d20.util.persistence.operator.IOperatorDao;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorPOJO;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;

public class WebAppController {
	PersistenceFacade facade;
	IVendingDao vendingsManager;
	IOperatorDao operatorsManager;
	IKeyDao keysManager;
	
	public WebAppController() {
		facade= PersistenceFacade.getInstance();
		vendingsManager=facade.getVendingDao();
		operatorsManager=facade.getOperatorDao();
		keysManager=facade.getKeyDao();
	}
		
	//IN FASE DI ELABORAZIONE
	

	public List<VendingPOJO> getAllVendingMachines() {
		return vendingsManager.getAllVending();
	}
	
	public VendingPOJO getVendingMachine(String id) {
		return vendingsManager.getVending(id);
	}
	
	//public String getVendingMachineInfo(String id) {}; //location, type, status
	//public Double[] getCurrentAmount(String id);
	//public Double[] getTankLevels(String id);
	//public Double[] getTankTemps(String id);
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
	
	
	public void getAllKeys() {
		keysManager.getAllKeys();
	}
	
	public void addKey(String serialCode, double credit) {
		keysManager.addKey(serialCode, credit);
	}
	
	public void getKeyCredit(String serialCode) {
		keysManager.getCredit(serialCode);
	}
	
	//public void deactivateKey(String serialCode) {}; //rimuove da db
	
	
	//public void getAllBeverageDescriptions() {};
	//public void setBeverageDescription() {};
	
	

}
