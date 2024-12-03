import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
public class Commissioned extends Employee{
	private double commission;
	private double baseSalary;
	private List<Order> orders;
	
	@Override
	public double calcGrossPay(int month, int yr){
	      // Create a YearMonth object
        YearMonth yearMonth = YearMonth.of(yr, month);

        // Subtract one month
//        YearMonth previousMonth = yearMonth.minusMonths(1);
		
		int totalAmountOrder = 0;

		for(Order order:orders) {
			if(yearMonth.compareTo(order.getOrderDate())==0) {
				totalAmountOrder += order.getOrderAmount();
			}
		}
		
		return commission * (double)totalAmountOrder + baseSalary;
	}

	public List<Order> getOrders(){
		return this.orders;
	}
	public void setOrder(List<Order> orders) {
		this.orders.addAll(orders);
	}

	public Commissioned(String empId, String name, double commission, double baseSalary) {
		super(empId, name);
		orders = new ArrayList<>();
		this.commission=commission;
		this.baseSalary = baseSalary;
		// TODO Auto-generated constructor stub
	}

}
