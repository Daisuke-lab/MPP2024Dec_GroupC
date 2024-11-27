package prob2B;

public class OrderLine {
	 private Order order; // Reference to the owning Customer

	    public OrderLine(Order order) {
	        
	        this.order = order;
	        order.addOrderLines(this); // Add this ShoppingCart to the Customer's collection
	    }

	    public Order getOwner() {
	        return order;
	    }

	    public void setOwner(Order order) {
	       
	        this.order = order;
	        order.addOrderLines(this); // Add this ShoppingCart to the new Customer
	    }
	    
	    @Override
	    public String toString() {
	        return "orderNum: " + order.getOrderNum();
	    }
}
