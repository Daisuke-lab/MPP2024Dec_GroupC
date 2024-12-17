
import java.util.Optional;


public class MySingletonLazy {
    private static int count = 0;
	private static MySingletonLazy instance = null;
	private MySingletonLazy() {
		count++;
	}
	public static MySingletonLazy getInstance(){
		return Optional.ofNullable(instance).orElseGet(()->createInstance());
	}

	private static MySingletonLazy createInstance() {
		instance = new MySingletonLazy();
		return instance;
	}
	public static void main(String[] args) {
		for(int i = 0; i < 10; ++i) {
			getInstance();
		}
		System.out.println(count);
	}
}