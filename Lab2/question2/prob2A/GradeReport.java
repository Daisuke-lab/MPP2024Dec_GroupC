package prob2A;

public class GradeReport {
	 private Student student; // Reference to the owning Customer

	    // Constructor to establish the relationship
	    GradeReport(Student student) {
	    	 this.student = student;
	         student.setGradeReport(this);
	    }

	    public Student getStudent() {
	        return student;
	    }

	    public void setStudent(Student student) {
	        this.student = student;
	    }
}
