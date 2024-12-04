package prob5;

public abstract class Duck implements FlyBehavior,QuackBehavior {
	public void swim() {
		System.out.println("swimming");
	};
	public void display() {
		System.out.println("displaying");
	}
	public  void fly() {
		System.out.println("fly with wings");
	} 
		
	
	
	
}
