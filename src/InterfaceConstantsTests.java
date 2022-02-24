public class InterfaceConstantsTests extends B implements A {
	public static void main(String[] args) {
		id = 3;
		System.out.println(id);
	}
}

interface A {
	int id = 1;
}

class B implements A {
	public static int id = 2;
}