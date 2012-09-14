package gfv.homework.spcapitaliq.quotes.domain;

/**
 * Describes the actions need to obtain the quote for the symbol
 * @author GFV
 *
 */
public interface QuoteQueryService {
	/**
	 * Queries a external service to get a quote from a stock
	 * @param symbol name of the stock
	 * @return @link Stockquote with bid,ask, and mean
	 */
	StockQuote get(String symbol);
}
