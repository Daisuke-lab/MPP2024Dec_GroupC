package lesson5.labs.prob4;

import java.time.LocalDate;

public class CustOrderFactory {

    public static Customer createCustomer(String name) {
        return new Customer(name);
    }

    public static Order addOrder(Customer customer, LocalDate orderDate) {
        if(customer == null) throw new NullPointerException("Null customer");
        Order order= new Order(orderDate);
        customer.addOrder(order);
        return order;
    }

    public static void addItem(Order order, String itemName) {
        if(order == null) throw new NullPointerException("Null Order");
        Item item = new Item(itemName);
        order.addItem(item);
    }
}
