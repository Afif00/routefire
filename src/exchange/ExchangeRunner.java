package exchange;

import trader.Trader;

public class ExchangeRunner {
	/**
	 * commented out code is used for verification
	 * given more time, i'd move it to a testing suite and not have testing code in the solution
	 */
	public static void main(String[] args) {
		Exchange exchange = new Exchange();
	    Integer numberOfTraders = 3;
	    Trader[] traderArray = new Trader[numberOfTraders];
	    for (int x = 0; x < numberOfTraders; x++) {
	      Trader trader = new Trader(exchange);
	      trader.start();
	      traderArray[x] = trader;
	    }
	    for (Trader trader : traderArray) {
	    	try {
				trader.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    exchange.verifyBookContentsAreValid();
	}
}
