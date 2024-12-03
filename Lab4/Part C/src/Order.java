import java.time.YearMonth;
import java.util.*;
public class Order {
	private int orderNo;
	private YearMonth orderDate;
	private int orderAmount;
	private Commissioned commissioned;
	
	public Order(int orderNo, YearMonth orderDate, int orderAmount, Commissioned commissioned) {
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
		this.commissioned = commissioned;
	}

	public int getOrderAmount() {
		return this.orderAmount;
	}
	public YearMonth getOrderDate() {
		return this.orderDate;
	}
}
