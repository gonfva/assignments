package gfv.homework.spcapitaliq.quotes.domain;

/**
 * Exception launched by @link QuoteQueryServiceYahooImpl if there is some
 * problem getting the quote (bad url, timeout...) * 
 * 
 * @author GFV
 *
 */
@SuppressWarnings("serial")
public class QueryException extends RuntimeException {

	public QueryException(String message, Exception e) {
		super(message, e);
	}

}
