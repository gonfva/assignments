package gfv.homework.spcapitaliq.quotes.domain;

/**
 * Service interface describing the calls required by the trader
 * 
 * @author GFV
 *
 */
public interface TraderService {
	/**
	 * Do whatever needs to get a quote for the symbol recieved
	 * @param symbol name of the stock to get
	 * @return a stock quote 
	 * @throws RuntimeException if there is any problem.
	 */
	StockQuote getQuote(String symbol);
}
