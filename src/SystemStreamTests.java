import java.io.*;

public class SystemStreamTests {
	public static void main(String[] args) {
		if (false) { // System.in, hangs until an input has been received
			try (var x = new BufferedReader(new InputStreamReader(System.in))) {
				System.out.println("Enter something:");
				String y = x.readLine();
				System.out.println("You've entered: " + y);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Bye");
		}
		if (false) { // System.out
			try (var x = System.out) { x.println("I am alive but stupid"); }
			System.out.println("I am lost");
		}
		if (!false) { // System.err
			try (var x = System.out) { x.println("I am also alive but stupid"); }
			System.out.println("I am also lost");
		}
	}
}