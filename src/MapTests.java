import java.util.*;

public class MapTests {
	public static void main(String[] args) {
		if (false) { // merge; BiFunction<V, V, V>
			Map<String, Integer> x = new HashMap<>(Map.of("x", 1, "y", 2, "z", 3, "w", 4));
			System.out.println(x);
			x.merge("k", 50, (a, b) -> a % 2 == 0 ? b * 100 : b); // addition
			System.out.println(x);
			x.merge("k", 50, (a, b) -> a % 2 == 0 ? b * 1000 : b); // update of k only
			System.out.println(x);
			x.merge("y", 20, (a, b) -> a % 2 == 0 ? b * 100 : b); // update of y onlsy
			System.out.println(x);
			x.merge("k", 50, (a, b) -> null); // deletion
			System.out.println(x);
		}
		if (!false) { // compute; BiFunction<K, V, V>
			Map<String, Integer> x = new HashMap<>(Map.of("x", 1, "y", 2, "z", 3, "w", 4));
			System.out.println(x);
			x.compute("k", (a, b) -> b == null ? 1000 : b); // add k=1000 if not found
			System.out.println(x);
			x.compute("w", (a, b) -> b != null ? 2000 : b); // update w if found
			System.out.println(x);
		}
		if (false) { // replace
			Map<String, Integer> x = new HashMap<>(Map.of("x", 1, "y", 2, "z", 3));
			System.out.println(x);
			x.replace("x", Integer.valueOf("10"));
			x.replace("y", 20);
			x.replace("k", 40);
			System.out.println(x);
		}
		if (false) { // replaceAll; BiFunction<K, V, V>
			Map<String, Integer> x = new HashMap<>(Map.of("x", 1, "y", 2, "z", 3));
			System.out.println(x);
			x.replaceAll((a, b) -> a == "z" ? b + 1000 : b + 2000);
			System.out.println(x);
		}
	}
}	