package it.unipv.ingsw.d20.model.paymentsystem.payment.exceptions;

/**
 * @author Luigi Zaccaria Del Pio
 *
 */

/*In vista di estensioni di una classe Payment generica. I motivi di fallimento del pagamento
 * possono essere diversi: la Payment lancerà InvalidPayment ma nello specifico quest'ultima sarà
 * istanza di un'eccezione più specifica, come questa. Sale prenderà decisioni diverse a seconda
 * del tipo effettivo dell'eccezione che cattura provando a fare il pagamento.
 */
@SuppressWarnings("serial")
public class InsufficientCreditException extends InvalidPaymentException {

	public InsufficientCreditException() {
		super();
	}
	
}
