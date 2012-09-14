package gfv.homework.spcapitaliq.quotes.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:spring-test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
public class TraderServiceImplTest {
	@Autowired
	StockRepository repo;
	@Autowired
	QuoteQueryService query;
	@Autowired
	TraderService trader;
	/*
	 * A1-1 “Type in a yahoo ticker symbol and within one second see the current 
	 * price.  The current price should be approximately equal to that available
	 * from a Google search”
	 * 
	 */
	@Test
	public void trader1() {
		assertNull(repo.get("YHOO"));
		StockQuote q = trader.getQuote("YHOO");
		assertNotNull(q);
	}
	/*
	 * A1-2 “Type in an invalid ticker symbol and within one second see an 
	 * error message”
	 */
	@Test(expected=RuntimeException.class)
	public void trader2() {
		trader.getQuote("NO-QUOTE");
	}
	/*
	 * A2-1 “From an empty data store, use the stock system to view FB and then RHT 
	 * noting their current value.  Then query the data store to ensure those values
	 * have been recorded.” 
	 */
	@Test 
	@Transactional
	public void risk1() {
		assertTrue(repo.isEmpty());
		StockQuote q_fb = trader.getQuote("FB");
		StockQuote q_rht = trader.getQuote("RHT");
		assertEquals(repo.get("FB"), q_fb);
		assertEquals(repo.get("RHT"), q_rht);
	}
	
	

	/*
	 * A2-2 “Wait till the Facebook price has changed (a quarter of an hour during NY 
	 * trading hours 2:30PM to 9:00PM UK time should be sufficient) and then requery 
	 * FB noting the new price.  Then requery the data store to ensure that the value 
	 * had been updated for FB and the RHT value is unchanged”.
	 */
	@Test 
	@Transactional
	public void risk2() {
		assertTrue(repo.isEmpty());
		StockQuote q_fb = trader.getQuote("FB");
		assertEquals(repo.get("FB"), q_fb);
	}

}
