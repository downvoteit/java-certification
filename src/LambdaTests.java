import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LambdaTests {
	public static void main(String[] args) {
		var x = List.of("c", "d", "e", "a", "b");
		var y = List.of(10, 213, 14, 1);
		if (false) { // Plain lambda expression
			List<String> list = new ArrayList<>(x);
			System.out.println(list);

			Collections.sort(list, (a, b) -> b.compareTo(a));

			System.out.println(list);
		}
		if (!false) { // Anonymous class explicit method call (micking Comparator's compare(T, T) with a method reference)
			List<Integer> list = new ArrayList<>(y);
			System.out.println(list);

			ComparatorMimic z = new ComparatorMimic () {
				public int doSort(Integer x, Integer y) {
					return x.compareTo(y);
				}
			};

			Collections.sort(list, z::doSort);
			System.out.println(list);
		}
		if (false) { // Instance custom sort (micking Comparator's compare(T, T) with a method reference)
			List<String> list = new ArrayList<>(x);
			System.out.println(list);

			Collections.sort(list, new InstanceCustomSort()::doSort);

			System.out.println(list);
		}
		if (false) { // Static custom sort (micking Comparator's compare(T, T) with a method reference)
			List<String> list1 = new ArrayList<>(x);
			List<Integer> list2 = new ArrayList<>(y);
			System.out.println(list1);
			System.out.println(list1);

			Collections.sort(list1, StaticCustomSort::doSort);
			Collections.sort(list2, StaticCustomSort::doSort);

			System.out.println(list2);
			System.out.println(list2);
		}
	}
}

interface ComparatorMimic {
	int doSort(Integer x, Integer y);
}

class InstanceCustomSort {
	public int doSort(String x, String y) {
		return x.compareTo(y);
	}
}

class StaticCustomSort {
	public static int doSort(String x, String y) {
		return x.compareTo(y);
	}

	public static int doSort(Integer x, Integer y) {
		return x.compareTo(y);
	}
}
