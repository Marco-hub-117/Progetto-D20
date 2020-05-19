package it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment;

import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.payment.strategies.KeyCreditStrategy;

//fittizio per spiegare il mio modo di gestire i pagamenti
public class GUIController {
	VendingMachine vending;
	ICreditStrategy strategy;
	Object creditInfo;

	public GUIController(VendingMachine vending) {
		super();
		this.vending = vending;
	}

	{//metodoListener1: 
	// è stato schiacciato il bottone "inserisci chiavetta"-il listener apposito chiama
	strategy=CreditStrategyFactory.getStrategy("KEY"); //passo alla modalità di gestione del credito per la chiavetta
	
	//con la chiavetta è stato inserito anche il suo codice corrispondente
	String code="123"; //in modo da dire che ce l'ho
	
	creditInfo=code;
	
	double currentAmount=vending.elaborateCredit(strategy, creditInfo);
	//verrà mostrato a video
	}
	
	{//metodoListener2:
	//il cliente inserirà il codice della bevanda e premerà OK-il listener prenderà il valore e chiamerà..
	String codeBev="A1";
	vending.insertCode(codeBev);
	//al suo interno, insertCode chiama startTransaction passsandogli currentAmount e codeBev
	//verrà creata una Sale che farà il controllo che il credito sia sufficiente (altrimenti lancia un'eccezione
	//e l'utente viene notificato)
	//sale conclusa con successo, con getChange prendo il credito non usato.
	//startTransaction non ha sollevato eccezioni e ritorna questo valore, 
	//insertCode pone currentAmount= al valore.
	}
	
	{//metodoListener3:
	//il cliente schiaccia il bottone "togli chiavetta"-il listener apposito chiama
	
	//vending.updatePaymentSystem(creditInfo, currentAmount)
		
	}
	
	
	{//metodoListener4:
	//altra situazione: il cliente non inserisce la chiavetta, ma una moneta-il listener apposito chiama
	strategy=CreditStrategyFactory.getStrategy("CASH");
	
	//a seconda del bottone schiacciato so di che valore è la moneta
	double coin=0.5;
	
	creditInfo=coin;
	
	double currentAmount=vending.elaborateCredit(strategy, creditInfo);
	
	//se viene inserita un'altra moneta-- rieseguite queste 3 righe
		
	//quando il cliente avrà i soldi necessari schiaccerà OK e partirà il metodoListener2
	}
	
	
	{ //altra situazione: il cliente inserisce la chiavetta e poi delle monete per aumentare il suo credito:
	 //è esattamente come eseguire metodoListener1 e poi metodoListener4
	}
	
	
}
