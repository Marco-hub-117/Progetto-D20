package it.unipv.ingsw.d20.util.persistence.vending;

import java.util.ArrayList;

import it.unipv.ingsw.d20.util.persistence.RdbOperations;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;

/**
 * Implementazione dell'interfaccia IVendingDao. Implementa il DAO relativo al database relazionale.
 *
 */
public class VendingRdbDao implements IVendingDao{

	private RdbOperations op;
	
	public VendingRdbDao() {
		op = new RdbOperations();
	}
	
	public VendingMachineStatus getVendingStatusById(String id) {
		return op.getVendingStatusById(id);
	}

	@Override
	public void addVending(VendingPOJO vending) {
		op.addVending(vending);
	}

	@Override
	public ArrayList<VendingPOJO> getAllVendings() {
		return op.getAllVendings();
	}

	@Override
	public void updateVendingStatus(String idVending, VendingMachineStatus newStatus) {
		op.updateVendingStatus(idVending, newStatus);
	}

	@Override
	public VendingPOJO getVending(String idVending) {
		return op.getVending(idVending);
	}

	@Override
	public void updateVendingAmount(String idVending, double amount) {
		op.updateVendingAmount(idVending, amount);
	}

	@Override
	public void updateVendingTankLevel(String idVending, String tankLevel) {
		op.updateVendingTankLevel(idVending, tankLevel);
	}

	@Override
	public void updateVendingTankTemp(String idVending, String tankTemp) {
		// TODO Auto-generated method stub
		op.updateVendingTankTemp(idVending, tankTemp);
	}

}
