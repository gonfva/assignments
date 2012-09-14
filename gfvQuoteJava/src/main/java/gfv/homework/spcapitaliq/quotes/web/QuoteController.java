package gfv.homework.spcapitaliq.quotes.web;

import gfv.homework.spcapitaliq.quotes.domain.StockQuote;
import gfv.homework.spcapitaliq.quotes.domain.TraderService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/trader")
public class QuoteController {
	@Autowired
	TraderService traderService;
	
	
	private Logger log=Logger.getLogger(QuoteController.class);
	/**
	 * Initial screen. GET method
	 * @return The view
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String getInitialMessage() {
    	log.debug("GET petition");
        return "quote";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getResults(Model model, @RequestParam("quote") String symbol) {
    	log.debug("POST petition with symbol"+symbol);
    	try{
    		StockQuote q = traderService.getQuote(symbol);
    		model.addAttribute("stock",q);
    	}catch (RuntimeException e){
    		model.addAttribute("error", e.getMessage());
    	}
		return "quote";
   	
    }
}