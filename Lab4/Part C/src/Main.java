import java.lang.reflect.Array;
import java.time.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // HourlyEmployee
        HourlyEmployee hourlyEmployee = new HourlyEmployee("111", "William", 15, 38);
        Paycheck paycheckWilliam = hourlyEmployee.calCompensation(12, 2024);
        System.out.println("HourlyEmployee: " + hourlyEmployee.print() + ", Income: " + paycheckWilliam.print());

        // SalariedEmployee
        SalariedEmployee salariedEmployee = new SalariedEmployee("111", "Gana", 8000);
        Paycheck paycheckGana = salariedEmployee.calCompensation(12, 2024);
        System.out.println("HourlyEmployee: " + salariedEmployee.print() + ", Income: " + paycheckGana.print());


        Commissioned commissioned = new Commissioned("111", "Jack", 0.5, 6500);
        // SalariedEmployee
        List<Order> orders = Arrays.asList (
                new Order(1, YearMonth.of(2024, 12), 3, commissioned),
                new Order(2, YearMonth.of(2024, 12), 3, commissioned),
                new Order(3, YearMonth.of(2024, 12), 3, commissioned)

        );
        commissioned.setOrder(orders);
        Paycheck paycheckJack = commissioned.calCompensation(12, 2024);
        System.out.println("HourlyEmployee: " + commissioned.print() + ", Income: " + paycheckJack.print());
    }
}