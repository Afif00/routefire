package order;

public class OrderMessage {
	private Integer price;
	private OrderSide side;
	public OrderMessage(Integer price, OrderSide side) {
		super();
		this.price = price;
		this.side = side;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public OrderSide getSide() {
		return side;
	}
	public void setSide(OrderSide side) {
		this.side = side;
	}
}
