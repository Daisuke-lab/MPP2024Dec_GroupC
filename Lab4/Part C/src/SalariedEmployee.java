public class SalariedEmployee extends  Employee {
    private double salary;

    public SalariedEmployee(String empId, String name, double salary) {
        super(empId, name);
        this.salary = salary;
    }

    @Override
    public double calcGrossPay(int month, int yr) {
//        int totalMonths = month + (yr * 12);

//        return salary * (double) totalMonths;
    	return salary;
    }
}
