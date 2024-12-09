package lab4q1;

public class CannotFly  implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("\tcannot fly");
    }
}
