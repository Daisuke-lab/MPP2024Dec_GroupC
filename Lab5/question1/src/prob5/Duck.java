package lab4q1;

public abstract class Duck {
    FlyBehaviour flyBehaviour;
    QuackBehavior quackBehavior;
    private String simpleName;

    public Duck(FlyBehaviour flyBehavior, QuackBehavior quackBehavior, String simpleName) {
        this.flyBehaviour = flyBehavior;
        this.quackBehavior = quackBehavior;
        this.simpleName = simpleName;
    }

    public void fly() {
        flyBehaviour.fly();
    }

    public void quack() {
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehaviour fb) {
        flyBehaviour = fb;
    }

    public String getSimpleName(){
        return simpleName;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }

    public void swim() {
        System.out.println("\tSwimming");
    }

    public abstract void display();
}
