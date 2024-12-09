package lab4q1;

public class RubberDuck extends Duck {
    public RubberDuck() {
        super(new CannotFly(), new Squeak(), "RubberDuck");
    }

    @Override
    public void display() {
        //System.out.println("rubber duck:");
        System.out.println("\tDisplaying");
    }
}

