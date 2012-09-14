Presentation
============
This project aims to present a solution to an assignment for a job selection process. 

The whole assignment was longer but basically asked to create a stock quote application 
wherein the user can request the price of a stock through its ticker symbol 
and get its current price.  

In addition to displaying the quote (average of bid and ask prices) the application 
should store the latest quote on a data store.

I used bigdecimals and number(19,4)

Comments
========

1.-I wasn't sure what was expected. Plain console app seemed too easy
for 2-3 hours. Finally I decided to do a web app with transactions and
persistence. It has been fun and a great exercise to me (I use
container managed transaction at work). But it hasn't been 2-3 hours
in any way (mostly because of the persistence/transactions).
2.-There is a file with configurable properties(including the proxy
details) in src/main/resources/spring.properties
3.-There are some automated test in src/test/java. They have their own
context for some of the tests. However in
QuoteQueryServiceYahooImplTest there should be needed to review proxy
settings
4..-The code should be easy to deploy to a Tomcat instance. It has a
H2 in memory database (with tables created/dropped). Once deployed,
either http://localhost:8080/quotes/ or
http://localhost:8080/quotes/trader should do the trick.
5.-The assignment seems to say that there should give answers in a
defined amount of time (1 second). I interpreted this as a timeout for
the API connection (but there is no DB timeout ).

No warranties.

2012-09-07 Gonzalo Fernandez-Victorio (gonfva@gmail.com)


