import java.util.*;
import java.util.stream.*;
import java.util.concurrent.atomic.*;

public class AtomicIntegerTests {
	private static AtomicInteger counter = new AtomicInteger();
	
	public static void main(String[] args) {
		if (false) { // Parallel stream
			var y = Stream.iterate(1, o -> o <= 100, o -> o + 1)
					.parallel()
					.map(o -> counter.incrementAndGet())
					.collect(Collectors.summarizingInt(o -> o));
			System.out.println(y.getCount() + " " + y.getMin() + " " + y.getMax());	
		}
		if (false) { // Add and get (pre-increment; ++i)
			var x = new AtomicIntegerTests();
			System.out.println(AtomicIntegerTests.counter.addAndGet(10));
		}
		if (!false) { // Get and set (post-increment; i++)
			var x = new AtomicIntegerTests();
			System.out.println(AtomicIntegerTests.counter.getAndSet(100));
		}
	}
}