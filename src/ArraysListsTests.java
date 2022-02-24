import java.util.*;

public class ArraysListsTests {
	public static void main(String[] args) {
		if (false) { // List.of
			List<Integer> x = new ArrayList<>(List.of(1, 2, 3));
			List<Integer> y = List.of(1, 2, 3);
			System.out.println((x == y) + " " + x.equals(y));
			System.out.println(x + " " + y);
			x.set(1, 2000);
			System.out.println(x + " " + y);
		}
		if (false) { // List.copyOf
			List<Integer> x = new ArrayList<>(List.of(1, 2, 3));
			List<Integer> y = List.copyOf(x);
			System.out.println((x == y) + " " + x.equals(y));
			System.out.println(x + " " + y);
			x.set(1, 2000);
			System.out.println(x + " " + y);
		}
		if (false) { // List.retainAll
			List<Integer> x = new ArrayList<>(List.of(20, 30));
			List<Integer> y = new ArrayList<>(List.of(1, 20, 30, 2, 3));
			y.retainAll(x);
			System.out.println(y);
		}
		if (false) { // List.subList
			List<Integer> x = new ArrayList<>(List.of(1, 20, 30, 2, 3));
			List<Integer> y = x.subList(1, 3);
			System.out.println(y);
		}
		if (false) { // Array init
			int[] x1, y1; // array+array
			int x2[], y2; // array+variable
			x1 = new int[]{1, 2, 3};
			y1 = new int[]{4, 5, 6};
			x2 = new int[]{1, 2, 3};
			y2 = 4;
		}
		if (false) { // Arrays.asList
			Integer[] x = {1, 2, 3};
			List<Integer> y = Arrays.asList(x);
			System.out.println(Arrays.toString(x) + " " + y);
			y.set(0, 10); // set affects both array and the list
			System.out.println(Arrays.toString(x) + " " + y);
			x[1] = 200; // changing the array affects the list
			System.out.println(Arrays.toString(x) + " " + y);
		}
		if (false) { // Arrays.compare, Arrays.mismatch
			int[] x1 = {1, 2, 3, 4}; 
			int[] y1 = {};
			System.out.println(Arrays.compare(x1, y1));
			System.out.println(Arrays.mismatch(x1, y1));
			
			int[] x2 = {1, 2, 3}; 
			int[] y2 = {4, 5, 6, 7};
			System.out.println(Arrays.compare(x2, y2));
			System.out.println(Arrays.mismatch(x2, y2));
		}
		if (false) { // Arrays.sort, Arrays.binarySearch
			String[] x = {"a", "A", "AA", "aa", "aaa", "", "B", "bB", "Bb", "b", "1", "101", "010", "9", "-91", "99", "0"};

			// steps:
			// empty, numbers, numbers+prefix, uppercase, uppercase+prefix, lowercase, lowercase+prefix
			 
			Arrays.sort(x);
			System.out.println(Arrays.toString(x));
			System.out.println(Arrays.binarySearch(x, "1")); // found: index 4
			System.out.println(Arrays.binarySearch(x, "10")); // not found: -6
		}
		if (!false) { // Collections.sort, Collections.binarySeach
			List<String> x = new ArrayList<>(List.of("A", "a", "AA", "b", "bA", "cB"));
			Collections.sort(x, (a, b) -> a.compareTo(b));
			System.out.println(x); // A, AA, a, b, bA, cB, 
			System.out.println(Collections.binarySearch(x, "b")); // 3
			System.out.println(Collections.binarySearch(x, "c")); // -5

			List<String> y = new ArrayList<>();
			Collections.sort(y, (a, b) -> a.compareTo(b));
			System.out.println(y);
			System.out.println(Collections.binarySearch(y, "X")); // -1
		}
	}
}