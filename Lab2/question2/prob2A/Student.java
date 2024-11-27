package prob2A;
public class Student {
	private String name;
	private GradeReport gradeReport;
	
    // Constructor to create a Customer with a ShoppingCart
    public Student(String name) {
        this.gradeReport = new GradeReport(this);
        this.name = name;
    }
    public void setGradeReport(GradeReport gradeReport) {
        this.gradeReport = gradeReport;
    }
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public GradeReport getGradeReport() {
		return gradeReport;
	}
}
