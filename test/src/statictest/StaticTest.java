package statictest;

public class StaticTest {
	static int value = 33;
	public static void main(String[] args) {
		new StaticTest().printValue();
	}
	private void printValue(){
		int value = 3;
		System.out.println(this.value);
	}
}
