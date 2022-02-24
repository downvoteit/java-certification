import java.util.stream.*;

public class CharSequenceTests {
	public static void main(String[] args) {
		CharSequence x = "abc def";
		IntStream y = x.codePoints();
		y.peek(o -> System.out.print(o + " "))
			.forEach(o -> System.out.print((char) o + "\n"));
	}
}