package prob4;
public abstract class Employee {
    private String empId;

    public Employee(String empId) {
        this.empId = empId;
    }

    public String print() { return ""; }
    
    public Paycheck calCompensation(int month, int year) {
        double grossPay = calcGrossPay(month, year);
        return new Paycheck(
                grossPay,
                grossPay * 0.23,
                grossPay * 0.05,
                grossPay * 0.01,
                grossPay * 0.03,
                grossPay * 0.075
            );
    }

    public abstract double calcGrossPay(int month, int yr);
}
