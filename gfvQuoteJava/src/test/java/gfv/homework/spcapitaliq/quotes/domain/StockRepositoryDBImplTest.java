package gfv.homework.spcapitaliq.quotes.domain;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:spring-test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
public class StockRepositoryDBImplTest extends StockRepositoryAbstractTest{
	@Test
	public void testEmptyStore(){
		assertTrue(repo.isEmpty());
		assertEquals(0,repo.getSize());
	}



}
