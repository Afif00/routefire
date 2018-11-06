package order;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum OrderSide {
	BUY, SELL;
	private static final List<OrderSide> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static OrderSide randomOrderType() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
