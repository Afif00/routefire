package trader;

import java.util.concurrent.ThreadLocalRandom;

import exchange.Exchange;
import order.OrderMessage;
import order.OrderSide;

public class Trader extends Thread {

	private Exchange usedExchange;
	private static final int numberOfOrders = 2;

	public Trader(Exchange usedExchange) {
		super();
		this.usedExchange = usedExchange;
	}

	public void run() {
		for (int x = 0; x <= numberOfOrders; x++) {
			// simulating trades happening at different times
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// sending an order to the exchange
			int orderValue = ThreadLocalRandom.current().nextInt(1, 10);
			OrderSide orderSide = OrderSide.randomOrderType();
			this.usedExchange.ingestOrder(new OrderMessage(orderValue, orderSide));
		}
	}
}