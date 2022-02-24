import java.lang.annotation.*;

@TestAnnotation
public class RetentionPolicyTests {
	public static void main(String[] args) {
		Class<RetentionPolicyTests> newClass = RetentionPolicyTests.class;

		for (Annotation annotation : newClass.getDeclaredAnnotations()) {
    			System.out.println("Retained annotation: " + annotation.toString());
		}
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
}