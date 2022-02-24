import java.util.*;
import java.util.stream.*;

public class MathRandomAndRandomTests {
	public static void main(String[] args) {
		if (false) { // Math.random
			System.out.println(Math.random());
		}
		if (false) { // Random.ints (4)
			//new Random().ints(3).limit(10).forEach(System.out::println);
			//new Random().ints(3, 0, 10).forEach(System.out::println);
			new Random().ints(0, 10).limit(10).forEach(System.out::println);
		}
		if (false) { // Random.nextInt (2)
			System.out.println(new Random().nextInt());
			System.out.println(new Random().nextInt(10));
		}
		if (!false) { // Random.nextBoolean
			System.out.println(new Random().nextBoolean());
		}
	}
}