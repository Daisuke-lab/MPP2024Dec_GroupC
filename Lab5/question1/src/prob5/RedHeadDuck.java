package lab4q1;

public class RedheadDuck extends Duck {
    public RedheadDuck() {
        super(new CannotFly(), new Squeak(), "RedheadDuck");
    }

    @Override
    public void display() {
        //System.out.println("I'm a RedheadDuck");
        System.out.println("\tDisplaying");
    }
}
