package gfv.homework.spcapitaliq.quotes.domain;


/**
 * Exception launched by @link QuoteQueryServiceYahooImpl if quote does
 * not exists
 * 
 * 
 * @author GFV
 *
 */
@SuppressWarnings("serial")
public class BadQuoteException extends RuntimeException {
	public BadQuoteException(String message, Exception e) {
		super(message, e);
	}

}
