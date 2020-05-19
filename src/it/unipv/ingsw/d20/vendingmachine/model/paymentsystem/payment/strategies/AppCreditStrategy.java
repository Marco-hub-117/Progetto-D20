package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class AppCreditStrategy extends AbstractCreditStrategy {

	@Override
	public String serialize(Object creditInfo) throws InvalidPaymentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkValidity(String serial) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getAmount(String serial) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return ("App");
	}

}
