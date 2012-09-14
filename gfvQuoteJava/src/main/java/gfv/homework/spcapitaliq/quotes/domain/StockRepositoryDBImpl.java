package gfv.homework.spcapitaliq.quotes.domain;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of StockRepository interface using Hibernate persistence,
 * and Spring managed transactions
 * 
 * @author GFV
 *
 */
@Component
@Transactional
public class StockRepositoryDBImpl implements StockRepository {
	Logger log=Logger.getLogger(StockRepository.class);
	@Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public StockQuote get(String symbol) {
		log.debug("Retrieving entity "+symbol);
    	Session s = sessionFactory.getCurrentSession();
    	Criteria crit = s.createCriteria(StockQuote.class);
    	crit.add(Restrictions.eq("symbol", symbol));
        return (StockQuote) crit.uniqueResult();
    }
	@Override
	public void store(StockQuote quote) {
		log.debug("Persisting entity "+quote.toString());

		sessionFactory.getCurrentSession().merge(quote);
		sessionFactory.getCurrentSession().flush();
	}
	@Override
	public boolean isEmpty() {
		if (getSize()>=1)return false;
		return true;
	}
	@Override
	public int getSize() {
		return (Integer) sessionFactory.getCurrentSession()
					.createCriteria(StockQuote.class)
					.setProjection(Projections.rowCount())
					.uniqueResult();
	}


}
