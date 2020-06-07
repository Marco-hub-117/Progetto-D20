package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem;

import it.unipv.ingsw.d20.persistence.PersistenceFacade;
import it.unipv.ingsw.d20.persistence.local.VendingLocalIO;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InsufficientCashForRestException;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidPaymentException;

public class Test {

	public static void main(String[] args) {
		PersistenceFacade pf = PersistenceFacade.getInstance();
		VendingLocalIO v = pf.getVendingLocalIO();
		CashContainer cc = v.getCashContainerFromLocal();
		System.out.println(cc.getTotalAmount());

		
		try {
			cc.addCoin(0.50);
		} catch (InvalidPaymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			cc.dispenseRest(5);
			cc.dispenseRest(5);
			cc.dispenseRest(5);
		} catch (InsufficientCashForRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cc.getTotalAmount());	


	}

}
