package gfv.homework.spcapitaliq.quotes.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class StockRepositoryAbstractTest {
	@Autowired
	protected StockRepository repo;

	@Test 
	@Transactional
	public void storeAndGet() {
		int size = repo.getSize();
		StockQuote q = new StockQuote("APPL", new BigDecimal("25.75"), new BigDecimal("24.98") );
		repo.store(q);
		assertFalse(repo.isEmpty());
		//I don't like to pre-increment inside an expression.
		size++;
		assertEquals(size,repo.getSize());
		
		assertEquals( q, repo.get("APPL"));

	}
	@Test 
	@Transactional
	public void storeChangeCompare(){
		StockQuote q1 = new StockQuote("APPL", new BigDecimal("25.75"), new BigDecimal("24.98") );
		repo.store(q1);
		q1.setAsk(new BigDecimal("26"));
		StockQuote q2 = repo.get("APPL");
		assertNotNull(q2);
		assertFalse(q2.equals(q1));
	}
	@Test 
	@Transactional
	public void storeTwoVersions(){
		StockQuote q1 = new StockQuote("APPL", new BigDecimal("25.75"), new BigDecimal("24.98") );
		repo.store(q1);
		StockQuote q2 = new StockQuote("APPL", new BigDecimal("26"), new BigDecimal("25") );
		repo.store(q2);
		StockQuote q3 = repo.get("APPL");
		assertNotNull(q3);
		assertFalse(q1.equals(q3));
		assertEquals(q2,q3);
	}	
	
	@Test 
	@Transactional
	public void storeMultipleQuotes() {
		assertTrue(repo.isEmpty());
		assertEquals(0,repo.getSize());
		
		StockQuote quotes[] = new StockQuote[] {
				new StockQuote( "MHP", new BigDecimal("52.92"), new BigDecimal("52.90") ),
				new StockQuote( "FB", new BigDecimal("18.60"), new BigDecimal("18.25") ),
				new StockQuote( "APPL", new BigDecimal("680.58"), new BigDecimal("675.00") )
		};
		
		for (StockQuote quote: quotes) {
			repo.store(quote);
		}
		
		assertEquals( quotes.length, repo.getSize());
		assertFalse(repo.isEmpty());

		for (StockQuote quote: quotes) {
			StockQuote q = repo.get(quote.getSymbol());
			assertNotNull(q);
			assertEquals( quote, q);
		}
		
	
	}
	

}
