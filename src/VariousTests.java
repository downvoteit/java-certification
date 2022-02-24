import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.lang.annotation.*;

@SuppressWarnings({"static", "fallthrough"})
public class VariousTests {
	public static void main(String[] args) {
		if (false) { // Integer caching, -128 to 127 is cached
  			Integer x1 = Integer.valueOf(127); 
  			Integer x2 = Integer.valueOf(127);
  			Integer x3 = Integer.valueOf(128); 
  			Integer x4 = Integer.valueOf(128);
			System.out.println(x1 == x2);
			System.out.println(x3 == x4);
		}
		if (false) { // Integer == Double will not compile
			Integer x1 = 1000;
			Double x2 = 1000.00;
			//System.out.println(x1 == x2);
		}
		if (false) { // Integer == double will compile
			Integer x1 = 1000;
			double x2 = 1000.00;
			System.out.println(x1 == x2);
		}
		if (false) { // Integer.parseInt supports radix
			System.out.println(Integer.parseInt("10", 2));
		}
		if (false) {
			var x = List.of(1, 2, 3);
			x.forEach((System.out::println));
		}
		if (false) { // Class static methods can be accessed by instance variable and namespace; interface static methods only by namespace
			C x = new C();
			// ((B)x).test(); // legal
			// ((A)x).test(); // illegal
			// B.test(); // legal
			x.test(); // legal
		}
		if (false) { // LinkedHashMap behave like an ordered cache store
			var x = new LinkedHashMap<String, Integer>(3);
			x.put("a", 1);
			x.put("b", 1);
			x.put("c", 1);
			System.out.println(x);
			x.remove("a");
			x.put("a", 1);
			if (x.size() >= 3) { // remove first
				x.remove(x.keySet().iterator().next());
			}
			x.put("d", 1);
			System.out.println(x);
		}
		if (false) { // Legal lambda variable reassignment
			var x = List.of(1, 2, 3);
			x.forEach(o -> { o = o * 10; System.out.println(o); });
		}
		if (false) { // null test
			Object x1 = null;
			int[] x2 = null;
			Float x3 = null;
			String x4 = null;
			System.out.println(x1 + " " + x2 + " " + x3 + " " + x4);
			try {
				x4.concat("will fail");
			} catch (NullPointerException e) {
				System.out.println("Controlled NPE, a method of a null instance has been called");
			}
		}
		if (false) { // Overload by an Exception 
			class A1 { 
				void test(String x) { 
					System.out.println(1); 
				}
			}
			class A2 extends A1 {
				// @Override
				public void test() throws Exception { // is an overload hence @Override will not compile
					System.out.println(2); 
				} 
			}
		}
		if (false) { // Map compute test
			var x = new HashMap<String, Integer>();
			x.put("a", 1);
			x.put("b", 1);
			x.put("c", 1);
			x.put("d", 1);
			System.out.println(x);
			BiFunction<String, Integer, Integer> y = (a, b) -> a.startsWith("b") ? b * 10 : b;
			x.compute("b", y);
			System.out.println(x);
		}
		if (false) { // Enum constructor test
			System.out.println("array: " + Arrays.toString(D.values()));	
			System.out.println("entry: " + D.THICC);	
			System.out.println("ordinal: " + D.THICC.ordinal());	
			System.out.println("by ordinal: " + D.values()[D.THICC.ordinal()]);
		}
		if (false) { // Adding chars
			var x = 'a' + 'b';
			var y = +'a';
			System.out.println(x);
			System.out.println(y);
			System.out.println('a' + 'b' + 'c');
		}
		if (false) { // StringBuilder
			String x0 = "we are legion";
			StringBuilder x1 = new StringBuilder(x0);
			StringBuilder x2 = new StringBuilder(x0);
			StringBuilder x3 = new StringBuilder(x0);
			System.out.println(x1.append("123") + " " + x1.length());
			System.out.println(x2.insert(2, "XXX") + " " + x2.length());
			System.out.println(x3.substring(7) + " " + x3.length());
		}
		if (false) { // Integer == Double
			Integer x1 = 1;
			Double x2 = 1.00;
			// System.out.println(x1 == x2); // does not compile
		}
		if (false) { // int == double
			int x1 = 1;
			double x2 = 1.00;
			// int will be promoted to double
			System.out.println(x1 == x2); // will compile
		}
		if (false) { // int / float = float
			int x1 = 5;
			float x2 = 2f;
			var x3 = x1 / x2;
			System.out.println(x3);
		}
		if (false) { // Interface constants can be accessed by a variable? Yes
			C x1 = new C();
			A x2 = new C();
			System.out.println(x1.id + " " + x2.id + " " + A.id);
		}
		if (false) { // Extra brackets on an MR? Legal
			var x = List.of(1, 2, 3);
			x.forEach((System.out::print));
		}
		if (false) { // Invoking an NPE
			List<Integer> x = null;
			System.out.println(x); // no NPE - methods are not being used
			try {
				System.out.println(x.add(1));
			} catch (NullPointerException e) {
				System.out.println("NPE ignored");
			}
		}
		if (false) { // SB delete
			StringBuilder x = new StringBuilder("0123456789");
			System.out.println(x.length() + " " + x.delete(2, x.length()) + " " + x.length());
		} 
		if (false) { // SB replace
			StringBuilder x = new StringBuilder("0123456789");
			System.out.println(x.replace(3, 5, "ab"));
		}
		if (false) { // SB insert
			StringBuilder x = new StringBuilder("0123456789");
			System.out.println(x.insert(3, "ab"));
		}
		if (false) { // Variables declare in one case are visible in other cases
			int x = 1;
			switch (x) {
				case 1: var y = 10;
				case 2: y = 20;
				case 3: y = 30;
				case 4: y = 40; System.out.println(x + " " + y);
			}
		}
		if (false) {
			class X { int val = 10; }
			class Y extends X { Y val = null; }
			
			Y y = new Y();
			// int k = (X) y.val; // will not compile; dot operator first, cast is last - X cannot be converted to int
			int k = ((X) y).val; // will compile
			System.out.println(k);
		}
		if (false) { // Override Enum's toString()
			var x1 = Alphabet.B;
			var x2 = Alphabet.A;
			System.out.println(Arrays.toString(Alphabet.values()));
			System.out.println(x1 + " " + Alphabet.valueOf("C"));
			System.out.println(x2.name() + " " + x2.toString() + " " + x2.ordinal());
		}
		if (false) { // Enum sort
			var x1 = List.of(Alphabet.A, Alphabet.B, Alphabet.A, Alphabet.C);
			var x2 = new ArrayList<Alphabet>(x1);
			Collections.sort(x2, null);
			System.out.println(x2);
		}
		if (false) { // Accessing ancestral method - selection depends on the actual object; cannot access First if you have only Third in memory
			class First { public int test() { return 1; } }
			class Second extends First { public int test() { return 2; } }
			class Third extends Second { public int test() { return super.test(); } }
			
			Third x = new Third();
			System.out.println(x.test());
		}
		if (false) { // while(false) {} will not compile - compile time constant
			// while (false) { System.out.println("I am unreachable"); }
		}
		if (false) { // for (int x = 0; false; x++) {} will not compile - compile time constant
			// for (int x = 0; false; x++) { System.out.println("I am unreachable"); }
			boolean y = false;
			for (int x = 0; y; x++) { System.out.println("I am unreachable"); } // will compile
		}
		if (false) { // Substring
			var x = "-0.50";
			var y = Double.parseDouble(x.substring(1, x.length() - 1));
			System.out.println(y + ", " + x.substring(1, x.length()) + ", " + x.length());
		}
		if (false) { // Path.resolveSibling
			var x = Path.of("C:\\Users\\User\\IdeaProjects\\java-certification\\resources");
			var y = Path.of("target");
			System.out.println(x);
			System.out.println(y);
			System.out.println(x.resolveSibling(y));
		}
		if (false) { // Path.resolve on absolute paths
			var x = Path.of("C:\\Users\\User\\IdeaProjects\\java-certification\\resources");
			var y = Path.of("C:\\Users\\User\\IdeaProjects\\java-certification\\target");
			System.out.println(x);
			System.out.println(y);
			System.out.println(x.resolve(y)); // will return the other	
		}
		if (false) { // Path.resolve on relative paths
			var x = Path.of("resources");
			var y = Path.of("target");
			System.out.println(x);
			System.out.println(y);
			System.out.println(x.resolve(y)); // will append
		}
		if (false) { // Path.resolve on absolute+relative paths
			var x = Path.of("C:\\Users\\User\\IdeaProjects\\java-certification\\resources");
			var y = Path.of("target");
			System.out.println(x);
			System.out.println(y);
			System.out.println(x.resolve(y)); // will append
		}
		if (false) { // Path.subpath
			var x = Path.of("C:\\Users\\User\\IdeaProjects\\java-certification\\resources");
			System.out.println(x.getNameCount());
			System.out.println(x.subpath(0, 3));
		}
		if (false) { // Read file lines with an IO stream (lines()+new BufferedReader()+new FileReader())
			try (Stream<String> x = new BufferedReader(new FileReader("files/test_file1.txt")).lines()) {
				x.forEach(System.out::println);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (false) { // Read file lines with File.lines()
			try (Stream<String> x = Files.lines(Path.of("files/test_file1.txt"))) {
				x.forEach(System.out::println);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (false) { // Read file lines with FileInputStream+InputStreamReader()
			try (var x = new InputStreamReader(new FileInputStream("files/test_file1.txt"))) {
				while (x.ready()) {
					int i = x.read();
					System.out.print((char) i);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (false) { // DataInputStream+DataOutpuStream
			String path = "files/test_file3.txt";
			try {
				try (var x = new DataOutputStream(new FileOutputStream(path))) {
					x.writeUTF(new String("тест жепа плиз игнор".getBytes(), StandardCharsets.UTF_8));
				}
				System.out.println("file size (bytes): " + Files.size(Path.of(path)));
				try (var x = new DataInputStream(new FileInputStream(path))) {
					String y = x.readUTF();
					System.out.println(y);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		if (false) { // Path resolve() and resolveSibling()
			var x = Path.of("C:\\Users\\User\\IdeaProjects\\java-certification\\files\\test_file1.txt");
			//System.out.println(x.resolveSibling("\\test_file3.txt")); // incorrect, will resolve to root
			var y = x.resolveSibling("test_file3.txt"); // correct, will resolve to another file in the same directory
			System.out.println(y + " " + Files.exists(y));
		}
		if (false) { // Path.subpath()
			var x = Path.of("C:\\Users\\User\\IdeaProjects\\java-certification\\files\\test_file1.txt");
			System.out.println(x.subpath(2, 4)); // will subpath IdeaProjects\\java-certification
			var y = Paths.get("C:\\Users", new String("жепа".getBytes(), StandardCharsets.UTF_8), x.subpath(2, 4).toString(), "porn\\hidden", "tori-black");
			System.out.println(y);
		}
		if (false) { // Collectors.joining()
			var x = List.of("f", "u", "c", "k", "o", "f", "f");
			System.out.println(x.stream().collect(Collectors.joining())); // concat
			System.out.println(x.stream().collect(Collectors.joining("_"))); // concat+delimiter
			System.out.println(x.stream().collect(Collectors.joining("_", "please--", "__mate"))); // concat+delimiter+prefix+suffix
		}
		if (false) { // Stupid toString() 
			final class Stupid {
				private final int id;
				private final String code;
				public Stupid(int id, String code) { this.id = id; this.code = code; }
				public final int getId() { return id; }
				public final String getCode() { return code; }
				@Override public String toString() { return id + " " + code; }
			}
			var x = List.of(new Stupid(1, "AAA"), new Stupid(2, "AAA"), new Stupid(3, "BBB"), new Stupid(4, "CCC"));
			var y = x.stream().collect(Collectors.groupingBy(Stupid::getCode));
			y.forEach((a, b) -> System.out.println(b)); // Map<String, List<Stupid>> signature; b is pointing to an instance of Stupid
		}
		if (false) { // Numeric promotion float with int, long, double
			float x1 = 1;
			float x2 = 1f;
			float x3 = 1.0f;
			float x4 = -1.0f;
			float x5 = -1;
			int x6 = 10;
			float x7 = x6;
			float x8 = (long)x6;
			// float x9 = (double)x6; // will not compile
			float x9 = (float)((double)x6); // will compile after down-casting to a double to a float
			System.out.println(x1);
			System.out.println(x2);
			System.out.println(x3);
			System.out.println(x4);
			System.out.println(x5);
			System.out.println(x7);
			System.out.println(x8);
			System.out.println(x9);
		}
		if (false) { // Boxing and unboxing float and long
			float x1 = 1;
			Float x2 = x1;
			// Integer x3 = x2; // will not compile
			Integer x3 = x2.intValue(); // will compile
			Float x4 = 1.2f;
			float x5 = x4;
			Double x6 = 1.31;
			// float x7 = x6; // will not compile
			float x7 = x6.floatValue();
			long x8 = 1231;
			Long x9 = x8;
			byte x10 = (byte)x8;
			// byte x11 = (Byte)x9; // will not compile
			byte x11 = x9.byteValue();
			System.out.println(x2);
			System.out.println(x3);
			System.out.println(x4);
			System.out.println(x5);
			System.out.println(x7);
			System.out.println(x8);
			System.out.println(x9);
			System.out.println(x10);
			System.out.println(x11);
		}
		if (false) { // Numeric promotion 4 rules
			byte x1 = 1;
			char x2 = 3;
			int x3 = x1 + x2;
			System.out.println(x3); // binary operands below an int (byte, short, char) converted to int

			float x4 = 3;
			char x5 = 13;
			float x6 = x4 * x5;
			System.out.println(x6); // integral operand in a binary operation with a floating operand converted to floating number

			float x7 = 1.231f;
			double x8 = 1.312311;
			double x9 = x6 / x7;
			System.out.println(x9); // if different data types one operand promoted to a bigger data type 

			float x10 = 31;
			char x11 = 234;
			long x12 = 7;
			System.out.println(x10 * x11 / x12); // After promotion all resulted data type will be reflect that of each operand (float)
		}
		if (false) { // Stupid assignment in a method call
			int x = 0, y = 0, z = 0;
			try {
				willNeverRun((x=10), iAmIntentionallyStupid(y = 20), (z = 30));
			} catch (RuntimeException e) {
				System.out.println("caught");
			}
			System.out.println(x + " " + y + " " + z); // will never be assigned

			// steps:
			// assign x
			// assign y
			// throw after assigning y
			// exit the try-catch
			// print x, y, z
		}
		if (false) { // Inteface abstract method redeclaration
			class GrandDaughter implements Daughter {
				@Override public void shit() { System.out.println("must shit less"); }
			}
			
			var x = new GrandDaughter();
			x.shit();
		}
		if (false) { // ResourceBundle locale resolution
			// throw MissingResourceException for missing bundle or wrong locale
			Locale.setDefault(Locale.GERMAN);
			var x = ResourceBundle.getBundle("Ducks", new Locale("xx", "XX"));
			x.keySet().forEach(y -> System.out.println(y + ": " + x.getString(y)));

			// steps:
			// look for specific by country_Region
			// look for general by country
			// look for specific default by country_Region
			// look for general defaull by country
			// look for default
		}
       		if (false) { // Generics test
			var x = new ArrayList<Byte>(List.of((byte) 1, (byte) 2, (byte) 3));
			var y = doSmth(x);
			System.out.println(y + " " + y.getClass());
		}
		if (false) { // Annotation @Target
			class Dummy<@TargetElementTypeTest E extends Number> {
				private final E id;
				Dummy(E id) { this.id = id; }
				public int test(Function<Integer, Integer> mutator) { return mutator.apply((Integer) id); }
			} 
			
			var x = new Dummy<Integer>(3);
			System.out.println(x.test(z -> z * 100));

			//@TargetElementTypeTest int w1 = 400;
			//@TargetElementTypeTest Integer w2 = 400;
			//@TargetElementTypeTest Object w3 = new Object();
			//@TargetElementTypeTest Object w4 = null;
		}
		if (false) { // Covariant type test - will not work for primitives
			class Butt {
				private int id = 1;
				//public int test() { return id; } 
				public Number test() { return id; } 
			}
			class Ass extends Butt {
				private int id = 2;
				//public long test() { return id; } // will not compile; primitive types are not covariant
				//public Integer test() { return id; } // will not compile; same
				public Integer test() { return id; } 
			}
			
			Butt x = new Ass();
			System.out.println(x.test() + " " + x.id);
		}
		if (false) { // Overloaded method selection
			testOverloadedSelection(1);
		
			// steps:
			// look for an exact match
			// look for a larger data type
			// look for a wrapper
			// look for a vararg
		}
		if (false) { // Modulos 
			System.out.println((2 % 3) + " " + (3 % 4));
		}
		if (false) { // Complex assignments
			int y, z;	
			var x = y = z = 1;
			x = (y = (z = 10)); // Very legal
			System.out.println(x + " " + y + " " + z);
		}
		if (false) { // char and short
			char x = 1;
			//short y = x; // will not compile
			short y = (short) x; // will compile with a cast
			System.out.println(y);
		}
		if (false) { // String.substring(0, 3)
			var x = "abcdefgh";
			System.out.println(x.substring(0, 3));
		}
		if (false) { // Custom sorting without direct/explicit Comparable/Comparator usage
			class CustomSorter {
				public int compare(String x, String y) {
					return x.compareTo(y);
				}
			}
			
			List<String> list = new ArrayList<>();
			list.add("d"); 
			list.add("ad");
			list.add("b"); 
			list.add("bd"); 
			list.add("c"); 
			list.add("a"); 
			list.add("1"); 
			list.add("a1"); 
			list.add("11"); 
			list.add("A1"); 
			list.add("A"); 
			list.add("1A"); 

			System.out.println(list);
			Collections.sort(list, new CustomSorter()::compare);
			System.out.println(list);
		}
		if (false) { // Compiling methods with the same name
			class Shit {
				public Shit() { System.out.println("it shits gold"); }
				public void doShit() { System.out.println("shat"); } // this will compile (LIFO)
				// public static void doShit() { System.out.println("shat"); } // this should fail to compile
			}

			var x = new Shit();
		}
		if (false) { // Complex reassignment
			int k = 1;
      			int[] a = { 1 };
      			k += (k = 4) * (k + 2);
      			a[0] += (a[0] = 4) * (a[0] + 2);
      			System.out.println( k + " , " + a[0]);	
		
			// steps:
			// k=1 is saved for the compound assignment += because of left-to-right evaluation being of higher precedence
			// 1 += 4 * (4 + 2)
			
		}
		if (!false) { // Collectors.joining
			String x = Stream.of("pe", "nis").collect(Collectors.joining("", ">", "<"));
			String y = Stream.of("pe", "nis").collect(Collectors.joining(""));
			String z = Stream.of("pe", "nis").collect(Collectors.joining());
			System.out.println(x);
			System.out.println(y);
			System.out.println(z);
		}
	}
	
	private static void willNeverRun(int a, int b, int c) {
	}
	
	private static int iAmIntentionallyStupid(int a) {
		throw new RuntimeException("fuck...");
	} 
	
	// breakdown:
	// 1. E must be Number or subclasses of a Number
	// 2. Method parameter must be List or subclasses of a List
	// 3. Return type must be List or superclasses of a List (including Object)
	@SuppressWarnings({"noshitman", "rawtypes", "unchecked"})
	private static <E extends Number> List<? super E> doSmth(List<E> list) {
		return new ArrayList(list);
	}

	public static void testOverloadedSelection(Character x) { System.out.println("Character"); } // Exact match
	public static void testOverloadedSelection(String x) { System.out.println("String"); } // Exact match
	public static void testOverloadedSelection(String x1, String x2) { System.out.println("String + String"); } // Exact match
	public static void testOverloadedSelection(String... x1) { System.out.println("String varargs"); } // Varargs
	// public static void testOverloadedSelection(String x1, String... x2) { System.out.println("String + String varargs"); } // Varargs; will not compile - ambiguous
	public static void testOverloadedSelection(CharSequence x) { System.out.println("CharSequence"); } // Exact match
	
	public static void testOverloadedSelection(byte x) { System.out.println("byte " + x); } // Exact match
	public static void testOverloadedSelection(char x) { System.out.println("char " + x); } // Bigger data type
	//public static void testOverloadedSelection(long x) { System.out.println("long " + x); } // Bigger data type
	//public static void testOverloadedSelection(Long x) { System.out.println("Long " + x); } // Wrapper
	public static void testOverloadedSelection(long... x) { System.out.println("long varargs " + Arrays.toString(x)); } // Varargs

	//public static void testOverloadedSelection(Object x) { System.out.println("Object"); } // General
}

interface A {
	int id = 123;
	static void test() { System.out.println("this is A"); }	
}
	
class B {
	static void test() { System.out.println("this is B"); }		
}

class C extends B implements A {
}

enum D {
	SMALL(12), MEDIUM(16), LARGE(18), THICC(22);
	private int len;
	private D(int len) {
		this.len = len;
		System.out.println("D is initialized for " + len);
	}
	@Override
	public String toString() { return String.valueOf("you chose " + this.len); }
}

enum Alphabet implements Serializable {
	A("Aa"), B("Bb"), C("Cc");
	private final String name;
	private Alphabet(String name) { this.name = name; }
	@Override public String toString() { return "X[name=" + this.name + "]"; } 
}

interface Papa {
	public default void shit() { System.out.println("a grand shitter"); } 
}

interface Daughter extends Papa {
	//public void shit();  // will compile both ways because default is allows overriding
}


@interface String2dArrayWillNotCompile {
	//String[][] codes(); // will not compile
	String[] codes(); // will compile
}

@Target({ElementType.TYPE_PARAMETER})
@interface TargetElementTypeTest {
}

