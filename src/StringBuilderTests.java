public class StringBuilderTests {
	public static void main(String[] args) {
		if (false) { // insert (n)
			System.out.println(new StringBuilder("123").append("456").insert(3, " ")); // 123 456
		}
		if (false) { // delete
			System.out.println(new StringBuilder("123").append("456").insert(3, " ").delete(2, 3).delete(3, 4)); // 12 56
		}
		if (false) { // deleteCharAt
			System.out.println(new StringBuilder("123").append("456").insert(3, " ").deleteCharAt(3)); // 123456
		}
		if (false) { // reverse
			System.out.println(new StringBuilder("123").append("456").insert(3, " ").reverse()); // 654321
		}
		if (false) { // capacity+ensureCapacity
			StringBuilder x = new StringBuilder();
			x.ensureCapacity(3);
			System.out.println(x.append(123).append(" ").append(456));
		}
		if (false) { // append (n)
			StringBuilder x = new StringBuilder();
			System.out.println(x.append(123).append("fuck you", 5, 8));
		}
		if (false) { // replace
			StringBuilder x = new StringBuilder();
			System.out.println(x.append(123).replace(1, 3, "fuck you"));
		}
		if (false) { // substring (2)
			System.out.println(new StringBuilder().append("123 456").substring(4, 7));
			System.out.println(new StringBuilder().append("123 456").substring(4));
		}
		if (false) { // subSequence
			System.out.println(new StringBuilder().append("123 456").subSequence(4, 7));
		}
		if (!false) { // trimToSize
			var x = new StringBuilder(1000);
			System.out.println(x.capacity());
			System.out.println(x.length());
			x.append(123).append(" ").append(456);
			System.out.println(x);
			System.out.println(x.capacity());
			System.out.println(x.length());
			x.trimToSize();
			System.out.println(x.capacity());
			System.out.println(x.length());
			
		}
	}
}