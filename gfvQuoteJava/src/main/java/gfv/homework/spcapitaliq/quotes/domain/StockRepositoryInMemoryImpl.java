package gfv.homework.spcapitaliq.quotes.domain;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;



/**
 * Implementation of StockRepository interface in memory.
 * 
 * We use a ConcurrentHashMap to allow concurrent access with minimum access
 * 
 * @author GFV
 *
 */
public class StockRepositoryInMemoryImpl implements StockRepository {
	Logger log=Logger.getLogger(StockRepositoryInMemoryImpl.class);
	private ConcurrentHashMap<String,StockQuote> stocks = new ConcurrentHashMap<String,StockQuote>();
	
	@Override
	public void store(StockQuote quote) {
		log.info("Persisting quote "+quote.toString());
		stocks.put(quote.getSymbol(), new StockQuote(quote));
	}

	@Override
	public StockQuote get(String symbol) {
		log.info("Getting quote "+symbol);
		return new StockQuote(stocks.get(symbol));
	}

	@Override
	public boolean isEmpty() {
		if (stocks.isEmpty()) return true;
		return false;
	}

	@Override
	public int getSize() {
		return stocks.size();
	}


}
