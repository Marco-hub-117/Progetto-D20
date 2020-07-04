package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import java.io.IOException;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachineStatus;
import it.unipv.ingsw.d20.vendingmachine.model.net.VendingMachineClient;

public class GetCatalogCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'stop'");
			
			vm.setStatus(VendingMachineStatus.OFF);
			VendingMachineClient vmc = new VendingMachineClient();
			vmc.connectToServer(vm.getInfo());
			
			System.exit(0);
		} catch (CommandFormatException e) {
			return e.getMessage();
		} catch (IOException e) {
			System.exit(-1);
		}
		
		return null;
	}

}
