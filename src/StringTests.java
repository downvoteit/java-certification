import java.nio.charset.*;
import java.util.*;

public class StringTests {
	// equals, equalsIgnoreCase
	public static void main(String[] args) {
		if (false) { // length
			System.out.println("123 456".length());
		}
		if (false) { // concat
			System.out.println("123".concat(" 456"));
		}
		if (false) { // contains
			System.out.println("123".contains("2"));
		}
		if (false) { // charAt
			System.out.println("123".charAt(2));
			try {
				System.out.println("123".charAt(-1));
			} catch (IndexOutOfBoundsException e) {
				System.out.println("IOFBE ignored");
			}
		}
		if (false) { // indexOf (4)
			System.out.println("123 456".indexOf("3"));
			System.out.println("123 456".indexOf("2", 3));
			System.out.println("123 456".indexOf(' '));
			System.out.println("123 456".indexOf(' ', 1));
		}
		if (false) { // trim
			System.out.println("'" + "   123 456   ".trim() + "'");
		}
		if (false) { // strip
			System.out.println("'" + "   123 456  ".strip() + "'");
		}
		if (false) { // stripLeading
			System.out.println("'" + "   123 456   ".stripLeading() + "'");
		}
		if (false) { // stringTrailing
			System.out.println("'" + "   123 456   ".stripTrailing() + "'");
		}
		if (false) { // valueOf (n)
			System.out.println(String.valueOf(123+456));
		} 
		if (false) { // substring (2)
			System.out.println("123 456".substring(0, 3));
			System.out.println("123 456".substring(4));
		}
		if (false) { // subSequence
			System.out.println("123 456".subSequence(0, 2));
		}
		if (false) { // repeat
			System.out.println("123 ".repeat(3));
		}
		if (false) { // format (2)
			System.out.println(String.format("%s %s", "123", "456"));
		}
		if (false) { // getBytes (4)
			System.out.println("жепа".getBytes().length);
			System.out.println("жепа".getBytes(StandardCharsets.UTF_8).length);
		}
		if (false) { // hashcode
			System.out.println("123 456".hashCode());
			System.out.println("123 456".hashCode());
		}
		if (false) { // contentEquals (2)
			var x = new StringBuilder("123").append(" ").append(456);
			System.out.println("123 456".contentEquals(x));
		}
		if (false) { // split (2)
			var x = "123 456 789";
			var y = x.split(" ");
			var z = x.split(" ", 2);
			System.out.println(Arrays.toString(y));
			System.out.println(Arrays.toString(z));
		}
		if (false) { // toCharArray
			var x = "123 456 789";
			var y = x.toCharArray();
			String z = new String(y);
			System.out.println(Arrays.toString(y));
			System.out.println(z);
		}
		if (false) { // join (2) and replaceAll (1)
			var x = List.of("-a-", "-b-", "-c-");
			System.out.println(String.join(" ", "123", "456"));
			System.out.println(String.join("-", "123", "456"));
			System.out.println(String.join(" ", x));
			System.out.println(String.join(" ", x).replaceAll("-", " "));
		}
		if (false) { // lines (legal line terminators \n,\r\n) and chars
			var x = "abc\ndefr\nhij";
			x.lines().forEach(System.out::println);
			x.chars().forEach(o -> System.out.print((char) o + " "));
		}
		if (false) { // toLowerCase (2) and toUpperCase (2)
			var x = "abc def";
			var y = "aBc dEf";
			var z = Locale.GERMAN;
			System.out.println(x.toUpperCase());
			System.out.println(x.toUpperCase(z));
			System.out.println(y.toLowerCase());
			System.out.println(y.toLowerCase(z));
		}
		if (false) { // isBlank
			try {
				System.out.println(((String) null).isBlank()); 
			} catch (NullPointerException e) {
				System.out.println("NPE ignored");
			}
			System.out.println("".isBlank());
			System.out.println(" ".isBlank());
			System.out.println("        ".isBlank());
		}
		if (false) { // isEmpty (true if length()==0)
			System.out.println("".isEmpty());
			System.out.println(" ".isEmpty());
			System.out.println("        ".isEmpty());
		}
		if (false) { // intern+equals+equalsIgnoreCase
			String x = "abc";
			String y = "a".concat("b").concat("c");
			String z = "AbC";

			System.out.println((x == y) + " " + (x.equals(y)));

			y = y.intern(); // added y to String pool

			System.out.println((x == y) + " " + (x.equals(y)));
			System.out.println(x.equals(z) + " " + x.equalsIgnoreCase(z));
		}
		if (!false) { // Concatenation rules
			System.out.println(1 + 2 + "a"); // 3a
			System.out.println(1 + "a" + 2); // 1a2
			System.out.println("a" + 1 + 2); // a12

			// steps:
			// if both are number and use + = numeric addition
			// if one is number and other String = concatenation
			// evaluation from left to right => 1 + 2 + "a" will always give 3a
		}
	}
}