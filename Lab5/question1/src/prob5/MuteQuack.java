package lab4q1;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        //System.out.println("cannot quack");
        System.out.println("\tDisplaying");
    }
}
