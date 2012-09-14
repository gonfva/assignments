package gfv.homework.spcapitaliq.quotes.domain;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Check proxy settings in private method 
 * 
 * @author GFV
 *
 */
public class QuoteQueryServiceYahooImplTest {

	@Test
	public void testCorrect() {
		QuoteQueryServiceYahooImpl service=new QuoteQueryServiceYahooImpl();
		Proxy p=getValidProxy();
		service.setProxy(p);
		service.setTimeout(1000);
		service.setApiService("http://finance.yahoo.com/d/quotes.csv?s=%PLACEHOLDER%&f=sb2b3");
		StockQuote q = service.get("MHP");
		assertNotNull(q);
		assertEquals(q.getSymbol(), "MHP");
		assertNotNull( q.getAsk());
        assertNotNull( q.getBid());
        assertNotNull( q.getQuote());
	}

	@Test(expected=QueryException.class)
	public void testBadUrl() {
		QuoteQueryServiceYahooImpl service=new QuoteQueryServiceYahooImpl();
		Proxy p=getValidProxy();
		service.setProxy(p);
		service.setTimeout(1000);
		service.setApiService("http://www.google.com/?q=%PLACEHOLDER%");
		service.get("MHP");
	}
	@Test(expected=BadQuoteException.class)
	public void testBadQuote() {
		QuoteQueryServiceYahooImpl service=new QuoteQueryServiceYahooImpl();
		Proxy p=getValidProxy();
		service.setProxy(p);
		service.setTimeout(1000);
		service.setApiService("http://finance.yahoo.com/d/quotes.csv?s=%PLACEHOLDER%&f=sb2b3");
		service.get("NO-QUOTE");
	}
	
	@Test(expected=QueryException.class)
	public void testTimeOut() {
		QuoteQueryServiceYahooImpl service=new QuoteQueryServiceYahooImpl();
		Proxy p=getValidProxy();
		service.setProxy(p);
		//One second is very little
		service.setTimeout(1);
		service.setApiService("http://finance.yahoo.com/d/quotes.csv?s=%PLACEHOLDER%&f=sb2b3");
		service.get("MHP");
	}
	
	private Proxy getValidProxy() {
		Proxy p = new Proxy();
//		p.setActive(true);
//		p.setHost("proxy");
//		p.setPort("port");
//		p.setUser("user");
//		p.setPassword("password");
//		return p;
		p.setActive(false);
		return p;
	}
	



}
