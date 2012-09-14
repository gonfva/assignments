package gfv.homework.spcapitaliq.quotes.domain;

import static org.junit.Assert.*;

import org.junit.Before;


public class StockRepositoryInMemoryImplTest extends StockRepositoryAbstractTest {

	@Before
	public void start(){
		repo = new StockRepositoryInMemoryImpl();
		assertTrue(repo.isEmpty());
		assertEquals(0,repo.getSize());
	}


}
