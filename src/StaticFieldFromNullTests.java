public class StaticFieldFromNullTests {
	static int id = 1;
		
	static StaticFieldFromNullTests  getId() {
		return null;
	}

	@SuppressWarnings("static")
	public static void main(String[] args) {
		System.out.println(getId().id);
	}
}