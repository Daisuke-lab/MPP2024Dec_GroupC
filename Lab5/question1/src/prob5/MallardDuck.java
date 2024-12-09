package lab4q1;

public class MallardDuck  extends Duck {
    public MallardDuck() {
        super(new FlyWithWings(), new Quack(), "MallardDuck");
    }

    @Override
    public void display() {
        //System.out.println("Mallard duck");
        System.out.println("\tDisplaying");
    }
}