package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies;

import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.exceptions.*;

public class AppPaymentStrategy extends AbstractPaymentStrategy {

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
	public double elaboratePayment(double price, Object creditInfo)
			throws InsufficientCreditException, InvalidPaymentException {
				return 0;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return ("App");
	}

}
