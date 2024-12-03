package prob4;
public class HourlyEmployee extends Employee {
    private double hourlyWage;
    private double hoursPerWeek;

    public HourlyEmployee(String empId, double hourlyWage, double hoursPerWeek) {
        super(empId);
        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public double calcGrossPay(int month, int yr) {
//        int totalMonths = month + (yr * 12);

        double oneMonthPay = (hourlyWage * hoursPerWeek * 4);
//        double totalPay = oneMonthPay * (double) totalMonths;

        return oneMonthPay;
    }
}
