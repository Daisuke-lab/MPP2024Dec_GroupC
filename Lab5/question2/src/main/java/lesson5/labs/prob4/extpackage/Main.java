package lesson5.labs.prob4.extpackage;

import java.time.LocalDate;

import lesson5.labs.prob4.CustOrderFactory;
import lesson5.labs.prob4.Customer;
import lesson5.labs.prob4.Order;

public class Main {
	public static void main(String[] args) {
		Customer cust = CustOrderFactory.createCustomer("Bob");
		Order order = CustOrderFactory.addOrder(cust, LocalDate.now());
		CustOrderFactory.addItem(order, "Shirt");
		CustOrderFactory.addItem(order, "Laptop");

		order = CustOrderFactory.addOrder(cust, LocalDate.now());
		CustOrderFactory.addItem(order, "Pants");
		CustOrderFactory.addItem(order, "Knife set");

		System.out.println(cust.getOrders());
	}
}

		
