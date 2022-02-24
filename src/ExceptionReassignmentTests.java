import java.io.*;

public class ExceptionReassignmentTests {
	public static void main(String[] args) {
		try {
			throw new IOException("brah");
		//} catch (IOException | IllegalStateException e) {
		//	e = new FileNotFoundException(); // Multi-catch cannot be reassigned; effectively final
		//	e.printStackTrace();
		} catch (IOException e) {
			e = new FileNotFoundException("fuck off mate"); // Single exception can be reassigned
			e.printStackTrace();
		}
	}
}