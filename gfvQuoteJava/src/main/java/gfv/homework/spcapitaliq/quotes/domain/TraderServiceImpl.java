package gfv.homework.spcapitaliq.quotes.domain;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Standard implementation of the @link TraderService
 * 
 * @author GFV
 *
 */
@Service
public class TraderServiceImpl implements TraderService{
	private Logger log=Logger.getLogger(TraderServiceImpl.class);
	@Autowired
	private StockRepository repo;

	@Autowired
	private QuoteQueryService queryService;


	public StockQuote getQuote(String symbol)
	{
		StockQuote q;
		q = queryApi(symbol);
		persistQuote(q);
		return q;
	}


	public void persistQuote(StockQuote q) {
		try{
			repo.store(q);
		}catch(Exception e){
			log.error("Problems saving data."+q.toString(), e);
			throw new RuntimeException( "There was a problem saving the data");
		}
	}


	public StockQuote queryApi(String symbol) {
		StockQuote q;
		try{
			q=queryService.get(symbol);
			if (q==null) {
				log.error("We've got null but no exception???"+symbol);
				throw new RuntimeException("There has been a problem querying");
			}
		}catch(BadQuoteException e)	{
			log.error("Bad quote "+symbol);
			throw new RuntimeException("The stock you're looking for doesn't exist");
		}catch(QueryException e){
			log.error("Bad query "+symbol);
			throw new RuntimeException("There has been a problem querying");
		}
		return q;
	}
}
