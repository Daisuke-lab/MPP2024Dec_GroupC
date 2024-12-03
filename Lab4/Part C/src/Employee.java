public abstract class Employee {
    private String empId;
    private String name;

    public Employee(String empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    public String print() { return "Name: " + this.name + ", Id: " + this.empId; }
    
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
