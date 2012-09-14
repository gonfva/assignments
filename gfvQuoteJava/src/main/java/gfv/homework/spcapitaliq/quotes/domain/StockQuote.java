package gfv.homework.spcapitaliq.quotes.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Standard stock quote. We use BigDecimal with numbers.
 * We also need to set scale to avoid rounding effects.
 * 
 * We use the same class for Hibernate persistence, so it have annotations.
 * 
 * @author GFV
 *
 */
@Entity
@Table(name="quotes")
public class StockQuote implements Serializable {
	private static final BigDecimal TWO = new BigDecimal(2.0);

	@Id
	@Column(name = "symbol")
	String symbol;
	@Column(name = "bid", precision = 19, scale = 4)
	private BigDecimal bid;
	@Column(name = "ask", precision = 19, scale = 4)
	private BigDecimal ask;  
	/**
	 * Default constructor. Needed for Hibernate persistence
	 */
	public StockQuote() {

	}
	/**
	 * Constructor with parameters. 
	 * 
	 * @param symbol Name of the stock
	 * @param ask Ask price
	 * @param bid Bid price
	 */
	public StockQuote(String symbol, BigDecimal ask, BigDecimal bid){
		if (symbol==null || ask==null ||bid==null) return;
		this.symbol=symbol;
		//We need to set the scale to avoid rounding effects when storing in DB
		this.bid=bid.setScale(4);
		this.ask=ask.setScale(4);
	}
	/**
	 * Copy constructor. Needed for in memory persistence
	 * @param q Stock quote to create from
	 */
	public StockQuote(StockQuote q){
		if (q.getSymbol()==null || q.getAsk()==null ||q.getBid()==null) return;
		this.symbol=q.getSymbol();
		this.bid=q.getBid().setScale(4);
		this.ask=q.getAsk().setScale(4);
	}
	/**
	 * Getter for the quote. The quote is calculated as the arithmetic mean
	 * of ask and bid
	 * @return the calculated quote as mean of ask and bid
	 */
	public BigDecimal getQuote() {
		if (ask==null) return null;
		if (bid==null) return null;
		BigDecimal mean = ask.add(bid).divide(TWO);
		return mean;
	}  


	public BigDecimal getAsk() {

		return ask;
	}  

	public BigDecimal getBid() {

		return bid;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	} 
	/**
	 * hashcode and equals hand by hand
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
		result = 31 * result + (bid!=null ? bid.hashCode():0);
		result = 31 * result + (ask!=null ? ask.hashCode():0);
		return result;
	}
	/**
	 * Redefine the equals() to allow comparasions
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof StockQuote)) return false;
		StockQuote q = (StockQuote) o;
		if ((symbol == null) ? (q.symbol != null) : !symbol.equals(q.symbol)) return false;
		if ((bid == null) ? (q.bid != null) : !bid.equals(q.bid)) return false;
		if ((ask == null) ? (q.ask != null) : !ask.equals(q.ask)) return false;
		return true;
	}
	/**
	 * Redefine the toString() method to get better logging info
	 */
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Quote [");
		sb.append("symbol=");
		sb.append(symbol);
		sb.append(", ask=");
		sb.append(ask);
		sb.append(", bid=");
		sb.append(bid);
		sb.append(", quote=");
		sb.append(getQuote());
		sb.append("]");
		return sb.toString();
	}

}
