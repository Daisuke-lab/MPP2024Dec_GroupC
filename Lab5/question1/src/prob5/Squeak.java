package lab4q1;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("\tsqueaking");
    }
}