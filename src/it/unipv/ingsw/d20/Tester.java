package it.unipv.ingsw.d20;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * 
 * For testing purposes only, leave this class empty!
 *
 */
public class Tester {

	public static void main(String[] args){
		VendingMachine vm = new VendingMachine("ID000001");
		System.out.println(vm.getTotalAmount());
		vm.withdrawAmount();
		System.out.println(vm.getTotalAmount());
	
	}
}
