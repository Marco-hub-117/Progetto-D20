package it.unipv.ingsw.d20.vendingmachine.commandline;

import it.unipv.ingsw.d20.vendingmachine.commandline.command.*;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Classe che permette di processare e mandare in esecuzione i programmi
 * digitati dal terminale.
 *
 */
public class CommandProcessor {
	
	private CommandExecutor cmdExecutor;

	public CommandProcessor(VendingMachine vm) {
		cmdExecutor = new CommandExecutor(vm);
	}
	
	/**
	 * Riceve la riga di comando con gli argomenti e manda in esecuzione
	 * il relativo comando.
	 * @param commandln la riga di comando 
	 * @return result il risultato del comando
	 */
	public String processCommand(String commandln) {
		String[] commandSplit = commandln.split(" ");
	    String command = commandSplit[0].trim();
	    String cmdArgs = null;
	    if (commandln.length() > command.length() + 1)
	    	cmdArgs = commandln.substring(command.length() + 1);
	    
	    String result;
	    if (command.equalsIgnoreCase(Commands.HELP)) {
	    	result = cmdExecutor.executeCommand(new HelpCommand(), cmdArgs);
		} else if (command.equalsIgnoreCase(Commands.ADD_COIN)) {
	    	result = cmdExecutor.executeCommand(new AddCoinCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.STOP)) {
	    	result = cmdExecutor.executeCommand(new StopCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.INSERT_CODE)) {
	    	result = cmdExecutor.executeCommand(new InsertCodeCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.GET_CATALOG)) {
	    	result = cmdExecutor.executeCommand(new GetCatalogCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.DISPENSE_REST)) {
	    	result = cmdExecutor.executeCommand(new DispenseRestCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.GET_CREDIT)) {
	    	result = cmdExecutor.executeCommand(new GetCreditCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.INSERT_KEY)) {
	    	result = cmdExecutor.executeCommand(new InsertKeyCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.EJECT_KEY)) {
	    	result = cmdExecutor.executeCommand(new EjectKeyCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.REFILL_TANK)) {
	    	result = cmdExecutor.executeCommand(new RefillTankCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.WITHDRAW_CASH)) {
	    	result = cmdExecutor.executeCommand(new WithdrawCashCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.ENTER_OPERATOR_MODE)) {
	    	result = cmdExecutor.executeCommand(new EnterOperatorModeCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase(Commands.EXIT_OPERATOR_MODE)) {
	    	result = cmdExecutor.executeCommand(new ExitOperatorModeCommand(), cmdArgs);
	    } else {
	    	result = "Comando non trovato";
	    }
	    
	    return result;
	}
	
}
