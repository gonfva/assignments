package gfv.homework.spcapitaliq.quotes.domain;

/**
 * Interface to describe a basic repository with minimum
 * methods required for the assignment
 * 
 * @author GFV
 *
 */
public interface StockRepository {
	/**
	 * Persist a quote, either creating or updating the value in the repo
	 * @param quote
	 */
	void store(StockQuote quote);
	/**
	 * Queries the store to get a stock quote
	 * @param symbol Name of the stock to query
	 * @return the stock quote if it's in the store or null if not
	 */
	StockQuote get(String symbol);
	/**
	 * Checks if the repo is empty or not. Needed for testing
	 * @return true if empty
	 */
	boolean isEmpty();
	/**
	 * Queries the repo to get the size
	 * @return number of elements
	 */
	int getSize();
}
