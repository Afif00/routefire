package exchange;

import java.util.ArrayList;
import java.util.Iterator;

import order.ExchangeOrder;
import order.OrderMessage;
import order.OrderSide;

public class Exchange {

	private static ArrayList<ExchangeOrder> orderBook = new ArrayList<ExchangeOrder>();

	public void ingestOrder(OrderMessage orderMessage) {

		// decorate the order message so that it can be ingested by the exchange
		long threadId = Thread.currentThread().getId(); // the thread ID will be used as the trader ID
		long timeReceived = System.currentTimeMillis();
		ExchangeOrder exchangeOrder = new ExchangeOrder(orderMessage.getPrice(), orderMessage.getSide(), timeReceived,
				threadId);

		// atomic block for matching the order
		synchronized (this) {

			Boolean orderMatched = false;


			for (ExchangeOrder currentBookOrder : orderBook) {
				if (exchangeOrder.getTraderId() != currentBookOrder.getTraderId()
						&& ((exchangeOrder.getSide().equals(OrderSide.BUY)
								&& currentBookOrder.getSide().equals(OrderSide.SELL)
								&& exchangeOrder.getPrice() >= currentBookOrder.getPrice())
								|| (exchangeOrder.getSide().equals(OrderSide.SELL)
										&& currentBookOrder.getSide().equals(OrderSide.BUY)
										&& exchangeOrder.getPrice() <= currentBookOrder.getPrice()))) {
					System.out.println("Order match\n" + exchangeOrder + "\n" + currentBookOrder + "\n");
					orderBook.remove(currentBookOrder);
					orderMatched = true;
					break;
				}
			}

			if (!orderMatched) {
				orderBook.add(exchangeOrder);
				orderBook.sort(null);
			}
		}

	}

	/**
	 * To validate assignment has been coded to spec, I wrote this method that'll
	 * validate no order matches are present in the order book ie no sell should
	 * have a potential buyer & no buy should have a potential seller. If there are
	 * matching orders in the book, they'll be printed given more time, i'd move it
	 * to a testing suite and not have testing code in the solution
	 */
	public void verifyBookContentsAreValid() {

		Iterator<ExchangeOrder> orderBookIterator = orderBook.iterator();
		while (orderBookIterator.hasNext()) {

			Iterator<ExchangeOrder> innerOrderBookIterator = orderBook.iterator();
			ExchangeOrder firstComparisonElem = orderBookIterator.next();
			ExchangeOrder secondComparisonElem = innerOrderBookIterator.next();
			if (firstComparisonElem.getTraderId() != secondComparisonElem.getTraderId()
					&& (firstComparisonElem.getSide().equals(OrderSide.BUY)
							&& secondComparisonElem.getSide().equals(OrderSide.SELL)
							&& firstComparisonElem.getPrice() >= secondComparisonElem.getPrice()
							|| (firstComparisonElem.getSide().equals(OrderSide.SELL)
									&& secondComparisonElem.getSide().equals(OrderSide.BUY)
									&& firstComparisonElem.getPrice() <= secondComparisonElem.getPrice())))
				System.out.println(firstComparisonElem);
		}
	}
}