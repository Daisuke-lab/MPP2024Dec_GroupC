package prob2B;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Order order = new Order(1);

	        // Create multiple ShoppingCarts for the Customer
	        OrderLine orderline1 = new OrderLine(order);
	        OrderLine orderline2 = new OrderLine(order);

	        // Access and display the relationship
	        System.out.println(order);
	        for (OrderLine line : order.getOrderLines()) {
	            System.out.println(line);
	        }

	}

}
