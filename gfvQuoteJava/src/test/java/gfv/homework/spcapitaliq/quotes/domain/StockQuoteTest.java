package gfv.homework.spcapitaliq.quotes.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class StockQuoteTest {

	@Test
	public void testConstructor() {
		StockQuote q = new StockQuote("MHP",new BigDecimal(4.0),new BigDecimal(3.0));
		assertEquals(q.getSymbol(),"MHP");
		assertEquals(q.getBid(), new BigDecimal(3.0).setScale(4));
		assertEquals(q.getAsk(), new BigDecimal(4.0).setScale(4));
		assertEquals(q.getQuote(), new BigDecimal(3.5).setScale(4));
	}
	@Test
	public void testNull() {
		StockQuote q = new StockQuote(null,null,null);
		assertNull(q.getSymbol());
		assertNull(q.getBid());
		assertNull(q.getAsk());
		assertNull(q.getQuote());
	}
}
