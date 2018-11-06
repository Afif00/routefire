package order;

public class ExchangeOrder implements Comparable<ExchangeOrder> {
	private Integer price;
	private OrderSide side;
	private long timeReceived;
	private long traderId;
	
	
	public OrderSide getSide() {
		return side;
	}

	public void setSide(OrderSide side) {
		this.side = side;
	}

	public long getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(long timeReceived) {
		this.timeReceived = timeReceived;
	}

	public long getTraderId() {
		return traderId;
	}

	public void setTraderId(long traderId) {
		this.traderId = traderId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public OrderSide getType() {
		return side;
	}

	public void setType(OrderSide type) {
		this.side = type;
	}

	public ExchangeOrder(Integer price, OrderSide side, long timeReceived, long traderId) {
		super();
		this.price = price;
		this.side = side;
		this.timeReceived = timeReceived;
		this.traderId = traderId;
	}

	@Override
	public int compareTo(ExchangeOrder that) {
		return Long.compare(this.getTimeReceived(), that.getTimeReceived());
	}

	@Override
	public String toString() {
		// only the last 4 digits of timeReceived printed for readability
		return "Price " + this.price + ", Side " + this.side + ", Time Received " + (this.timeReceived % 10000) + ", Trader " + this.traderId;
		
	}
}
