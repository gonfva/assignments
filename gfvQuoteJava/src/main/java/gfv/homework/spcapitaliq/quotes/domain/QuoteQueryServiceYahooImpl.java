package gfv.homework.spcapitaliq.quotes.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * Implementation of @link QuoteQueryService using Yahoo provided API
 * 
 * @author GFV
 *
 */
public class QuoteQueryServiceYahooImpl implements QuoteQueryService {
	private Logger log=Logger.getLogger(QuoteQueryServiceYahooImpl.class);
	private final static String PLACEHOLDER="%PLACEHOLDER%";
	@Override
	public StockQuote get(String symbol) {
		log.debug("Trying to get symbol "+ symbol);
		URL url = getURL(symbol);
		HttpURLConnection con = getConnection(url);
		String results = executeQueryService(con);
		StockQuote quote=parseResults(results);
		if (con!=null) con.disconnect();
		log.debug("We got a quote=>"+ quote.toString());
		return quote;
		
	}

	private StockQuote parseResults(String results) {
		String[] values = results.split(",");
		if (values.length!=3) throw new QueryException("Service didn't return quote. Probably BadURL",null);
		StockQuote quote;
		try{
			String symbol=values[0].trim();
			if (symbol.length()<2) throw new QueryException("Service didn't return quote",null);
			String parsedSymbol = symbol.substring(1, symbol.length()-1);
			BigDecimal bid=new BigDecimal(values[1]);
			BigDecimal ask =new BigDecimal(values[2]);
			quote=new StockQuote(parsedSymbol, bid, ask);
		}catch (NumberFormatException ne){
			log.error("Error parsing results "+results);
			throw new BadQuoteException("Problems parsing results obtanied from the server", ne);
		}
		return quote;
	}

	private String executeQueryService(HttpURLConnection con) {
		String line=null;
		InputStream is=null;
		BufferedReader reader=null;
		try {
			is = con.getInputStream();
	        reader = new BufferedReader( new InputStreamReader(is) );
	        line = reader.readLine();
		} catch (SocketTimeoutException e){
			log.error("Timeout expired");
			throw new QueryException("Timeout expired",e);
		} catch (IOException e) {
			log.error("Error executing query against "+con.getURL());
	    	throw new QueryException("Problems obtaining values from remote server", e);
		} finally  {
            if ( reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                }
            }
            if ( is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }

        return line;
	}

	private URL getURL(String symbol) {
		String cad = apiService.replace(PLACEHOLDER, symbol);
		try {
			return new URL(cad);
		} catch (MalformedURLException e) {
			log.error("Wrong URL "+symbol);
			throw new QueryException("Unable to parse URL", e);
		}
	}

	private HttpURLConnection getConnection(URL url){
		proxy.use();
		try{
	        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setConnectTimeout(timeout);
	        connection.setReadTimeout(timeout);
	        //connection.connect();

			return connection;
		} catch (SocketTimeoutException e){
			log.error("Timeout expired");
			throw new QueryException("Timeout expired",e);
		} catch (IOException e) {
			log.error("Some error connecting");
			throw new QueryException("Problems connecting to the remote server", e);
		}

	}


	Proxy proxy;
	int timeout;
	String apiService;
	
	public void setApiService(String apiService) {
		this.apiService = apiService;
	}
	public void setTimeout(int milisecs){
		this.timeout=milisecs;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}
}
