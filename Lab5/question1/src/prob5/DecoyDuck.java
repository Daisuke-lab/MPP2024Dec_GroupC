package lab4q1;

public class DecoyDuck extends Duck {
    public DecoyDuck() {
        super(new CannotFly(), new Squeak(), "DecoyDuck");
    }

    @Override
    public void display() {
        //System.out.println("DecoyDuck:");
        System.out.println("\tDisplaying");
    }
}