package prob2A;

public class Main {

	public static void main(String[] args) {
		Student student = new Student("Ganaa");
        GradeReport grade = student.getGradeReport();

       
        System.out.println("Student Name: " + student.getName());
        System.out.println("Student GradeReport: " + grade);
        System.out.println("GradeReport's Student: " + grade.getStudent().getName());

        //change name
        student.setName("Bold");
        System.out.println("Updated Student Name: " + student.getName());
        System.out.println("GradeReport's Student: " + grade.getStudent().getName());
	}

}
