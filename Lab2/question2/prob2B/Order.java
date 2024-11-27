package prob2B;
import java.util.*;
public class Order {
	private int orderNum;
	private List<OrderLine> orderLines;
	
	public Order(int orderNum) {
	        this.orderNum = orderNum;
	        this.orderLines = new ArrayList<>(); // Initialize the list
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public List<OrderLine>getOrderLines() {
		return orderLines;
	}
	public void addOrderLines(OrderLine orderLine) {
		 orderLines.add(orderLine);
	}
	@Override
    public String toString() {
        return "OrderNum is " + orderNum + " with size:  " + orderLines.size() + ".";
    }
}
