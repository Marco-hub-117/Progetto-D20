package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies.KeyCreditStrategy;

//fittizio per spiegare il mio modo di gestire i pagamenti
public class GUIController {
	VendingMachine vending;
	ICreditStrategy strategy;

	public GUIController(VendingMachine vending) {
		super();
		this.vending = vending;
	}

	{//metodoListener1: 
	// è stato schiacciato il bottone "inserisci chiavetta"-il listener apposito chiama
	strategy=CreditStrategyFactory.getStrategy("KEY"); //passo alla modalità di gestione del credito per la chiavetta
	
	//con la chiavetta è stato inserito anche il suo codice corrispondente
	String code="123"; //in modo da dire che ce l'ho
	
	double currentAmount=vending.elaborateCredit(strategy, code);
	//verrà mostrato a video
	}
	
	{//metodoListener2:
	//il cliente inserirà il codice della bevanda e premerà OK-il listener prenderà il valore e chiamerà..
	String codeBev="A1";
	vending.insertCode(codeBev, strategy);
	//al suo interno, insertCode chiama startTransaction passsandogli currentAmount e codeBev
	//verrà creata una Sale che farà il controllo che il credito sia sufficiente (altrimenti lancia un'eccezione
	//e l'utente viene notificato)
	//sale conclusa con successo, restituisce un valore double (il credito non usato)
	//startTransaction non ha sollevato eccezioni e ritorna questo valore, 
	//insert code chiama stategy.completeSale(unusedCredit) e pone currentAmount= al valore ritornato da questa funz.
	}
	
	{//metodoListener3:
	//il cliente schiaccia il bottone "togli chiavetta"-il listener apposito chiama
	vending.setCurrentAmount(0);
		
	}
	
	
	{//metodoListener4:
	//altra situazione: il cliente non inserisce la chiavetta, ma una moneta-il listener apposito chiama
	strategy=CreditStrategyFactory.getStrategy("CASH");
	
	//a seconda del bottone schiacciato so di che valore è la moneta
	double coin=0.5;
	
	double currentAmount=vending.elaborateCredit(strategy, coin);
	
	//se viene inserita un'altra moneta-- rieseguite queste 3 righe
		
	//quando il cliente avrà i soldi necessari schiaccerà OK e partirà il metodoListener2
	}
	
	
	{ //altra situazione: il cliente inserisce la chiavetta e poi delle monete per aumentare il suo credito:
	 //è esattamente come eseguire metodoListener1 e poi metodoListener4: in metodoListener1 verrà settato a true
	//un boolean in modo che quando il cliente schiaccia OK, al metodoListener2 venga passata come strategy KeyCredit, 
	//invece che CashCredit, come accadrebbe normalmente.
	//quando la chiavetta sarà tolta (metodoListener3), questo bool tornerà false;
	}
	
	
}
