package it.unipv.ingsw.d20.util.persistence;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.operator.IOperatorDao;
import it.unipv.ingsw.d20.util.persistence.operator.OperatorPOJO;
import it.unipv.ingsw.d20.util.persistence.sale.ISaleDao;
import it.unipv.ingsw.d20.util.persistence.sale.SalePOJO;
import it.unipv.ingsw.d20.util.persistence.vending.IVendingDao;
import it.unipv.ingsw.d20.util.persistence.vending.VendingPOJO;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

public class test2 {

	public static void main(String[] args) {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		
		IVendingDao a = pf.getVendingDao();
		//System.out.println(a.getVendingStatusById("id3"));
		
		//VendingPOJO vProva = new VendingPOJO("id10",VendingMachineStatus.ONLINE,"Pavia","A",100,"90-80-70-100","10-20-20-5");
		
		//a.addVending(vProva);
		
		System.out.println(a.getVending("id10"));
		
		//a.updateVendingStatus("id10", VendingMachineStatus.OFF);
		//a.updateVendingAmount("id10", 1000);
		//a.updateVendingTankLevel("id10", "80-80-60-20");
		
		System.out.println(a.getVending("id10"));
		
		IOperatorDao iod = pf.getOperatorDao();
		//OperatorPOJO marco = new OperatorPOJO("AAABBB","Marco","password","schiavo","MC123");
		//iod.addOperator("CCCDDD","Francesco","password","schiavo","FC123");
		System.out.println(iod.getOperator("AAABBB"));
		for(OperatorPOJO p : iod.getAllOperators()) {
			System.out.println(p);
		}
		


	}

}
